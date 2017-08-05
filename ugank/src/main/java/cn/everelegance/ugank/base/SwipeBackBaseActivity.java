package cn.everelegance.ugank.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import cn.everelegance.ugank.R;

/**
 * Created by Administrator on 2017/8/3.
 */

public class SwipeBackBaseActivity extends AppCompatActivity implements BGASwipeBackHelper.Delegate {

    protected BGASwipeBackHelper mBGASwipeBackHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
    }

    private void initSwipeBackFinish() {
        mBGASwipeBackHelper = new BGASwipeBackHelper(this, this);

        mBGASwipeBackHelper.setSwipeBackEnable(true);
        mBGASwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        mBGASwipeBackHelper.setIsWeChatStyle(true);
        mBGASwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        mBGASwipeBackHelper.setIsNeedShowShadow(true);
        mBGASwipeBackHelper.setIsShadowAlphaGradient(true);
        mBGASwipeBackHelper.setSwipeBackThreshold(0.6f);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    @Override
    public void onSwipeBackLayoutCancel() {

    }

    @Override
    public void onSwipeBackLayoutExecuted() {
        mBGASwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if(mBGASwipeBackHelper.isSliding()){
            return;
        }
        mBGASwipeBackHelper.backward();
    }
}
