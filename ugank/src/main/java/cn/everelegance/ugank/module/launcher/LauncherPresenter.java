package cn.everelegance.ugank.module.launcher;

/**
 * Created by Administrator on 2017/8/4.
 */

public class LauncherPresenter implements LauncherContract.Presenter {
    private LauncherContract.View mView;

    public LauncherPresenter(LauncherContract.View view) {
        mView = view;
    }

    @Override
    public void subscribe() {
        mView.loadImg("https://ws1.sinaimg.cn/large/610dc034gy1fi502l3eqjj20u00hz41j.jpg");
//        if(!ConfigManage.INSTANCE.isShowLauncherImg()){
//            mView.goHomeActivity();
//            return;
//        }
//        String bannerUrl = ConfigManage.INSTANCE.getBannerUrl();
//        if (TextUtils.isEmpty(bannerUrl)) {
//            //概率出现
//            mView.goHomeActivity();
//        }else{
//            mView.loadImg(bannerUrl);
//        }
    }

    @Override
    public void unsubscribe() {

    }
}
