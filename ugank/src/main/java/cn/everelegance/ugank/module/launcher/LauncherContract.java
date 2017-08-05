package cn.everelegance.ugank.module.launcher;

import cn.everelegance.ugank.base.BasePresenter;
import cn.everelegance.ugank.base.BaseView;

/**
 * Created by Administrator on 2017/8/4.
 */

public interface LauncherContract {
    interface View extends BaseView{

        void goHomeActivity();

        void loadImg(String url);
    }

    interface Presenter extends BasePresenter{
        
    }
}
