package cn.everelegance.ugank.module.home;

import android.support.v7.graphics.Palette;

import java.util.Random;

import cn.everelegance.ugank.App;
import cn.everelegance.ugank.ConfigManage;
import cn.everelegance.ugank.R;
import cn.everelegance.ugank.ThemeManage;
import cn.everelegance.ugank.entity.CategroyResult;
import cn.everelegance.ugank.network.NetWork;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/8/4.
 */

public class HomePresenter implements HomeContract.Presenter {
    HomeContract.View mView ;
    private final CompositeSubscription mSubscription;

    public HomePresenter(HomeContract.View view) {
        mView = view;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void subscribe() {

        getBanner(false);
        getCahceImg();
    }

    @Override
    public void unsubscribe() {
        mSubscription.clear();
    }

    @Override
    public void getBanner(boolean isRandom) {
        mView.startFabLoadingAnim();
        mView.unenableFab();
          Observable<CategroyResult> observable;
        if (isRandom){
            observable = NetWork.getGankApi().getRandomBeauties(1);
        }else{
            observable= NetWork.getGankApi().getCategoryDate("福利", 1, 1);
        }
        Subscription subscription =observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategroyResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showTips(e.getMessage());
                        mView.stopFabLoadingAnim();
                        mView.enableFab();
                    }

                    @Override
                    public void onNext(CategroyResult categroyResult) {
                        if(categroyResult != null && categroyResult.getResults()!= null && categroyResult.getResults().size()>0
                                && categroyResult.getResults().get(0).getUrl()!=null){
                            mView.setBanner(categroyResult.getResults().get(0).getUrl());
                        }else{
                            mView.showTips("加载妹子失败");
                        }
                    }
                });

        mSubscription.add(subscription);
    }

    @Override
    public void getRandomBanner() {
        getBanner(true);
    }

    @Override
    public void getCahceImg() {
        if(!ConfigManage.INSTANCE.isShowLauncherImg()){
            return;
        }
        if(ConfigManage.INSTANCE.isProbabilityShowLauncherImag()){
             if( new Random().nextInt(100) < 50){
                 saveCacheImg("");
                return;
             }
        }
        Subscription subscribe = NetWork.getGankApi().getRandomBeauties(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategroyResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CategroyResult categroyResult) {
                        if (categroyResult != null &&
                                categroyResult.getResults() != null &&
                                categroyResult.getResults().size() > 0 &&
                                categroyResult.getResults().get(0).getUrl() != null) {
                            mView.cacheImg(categroyResult.getResults().get(0).getUrl());
                        }
                    }
                });

        mSubscription.add(subscribe);
    }

    @Override
    public void saveCacheImg(String url) {
        ConfigManage.INSTANCE.setBannerUrl(url);
    }


    @Override
    public void setThemeColor(Palette palette) {
        if(palette != null){
            int defaultColor = App.getInstance().getResources().getColor(R.color.colorPrimary);
            int darkVibrantColor = palette.getDarkVibrantColor(defaultColor);
            ThemeManage.INSTANCE.setColorPrimary(darkVibrantColor);
            mView.setAppbarColor(darkVibrantColor);
            mView.setFabColor(darkVibrantColor);
            mView.stopFabLoadingAnim();
            mView.enableFab();
        }
    }
}
