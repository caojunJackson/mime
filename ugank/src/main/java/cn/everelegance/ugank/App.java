package cn.everelegance.ugank;

import android.app.Application;

import org.litepal.LitePal;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * Created by Administrator on 2017/8/3.
 */

public class App extends Application {

    private static App INSTANCE;

    public static App getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        BGASwipeBackManager.getInstance().init(this);
        INSTANCE = this;
        LitePal.initialize(this);



    }
}
