package cn.everelegance.ugank.module.home;

import android.support.v7.graphics.Palette;

import cn.everelegance.ugank.base.BasePresenter;
import cn.everelegance.ugank.base.BaseView;

/**
 * Created by Administrator on 2017/8/4.
 */

public interface HomeContract {

    interface View extends BaseView{

        void setAppbarColor(int color);

        void setFabColor(int color);

        void startFabLoadingAnim();

        void stopFabLoadingAnim();

        void enableFab();

        void unenableFab();

        void cacheImg(String url);

        void showTips(String msg);

        void setBanner(String url);
    }

    interface Presenter extends BasePresenter{
        void getBanner(boolean isRandom);

        void getRandomBanner();

        void getCahceImg();

        void saveCacheImg(String url);

        void setThemeColor(Palette palette);
    }
}
