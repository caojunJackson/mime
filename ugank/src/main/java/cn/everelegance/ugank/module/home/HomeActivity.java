package cn.everelegance.ugank.module.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.florent37.picassopalette.PicassoPalette;
import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.everelegance.ugank.GlobalConfig;
import cn.everelegance.ugank.R;
import cn.everelegance.ugank.base.SwipeBackBaseActivity;
import cn.everelegance.ugank.base.adapter.CommViewPagerAdapter;
import cn.everelegance.ugank.module.category.CategoryFragment;
import cn.everelegance.ugank.utils.DisplayUtils;
import cn.everelegance.ugank.utils.MDTintUtil;
import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 2017/8/4.
 */

public class HomeActivity extends SwipeBackBaseActivity implements HomeContract.View {

    HomeContract.Presenter mPresenter = new HomePresenter(this);
    @BindView(R.id.iv_home_banner)
    ImageView mIvHomeBanner;
    @BindView(R.id.iv_setting)
    AppCompatImageView mIvSetting;
    @BindView(R.id.tab_home_category)
    DachshundTabLayout mTabHomeCategory;
    @BindView(R.id.tl_home_toolbar)
    Toolbar mTlHomeToolbar;
    @BindView(R.id.ll_home_search)
    LinearLayout mLlHomeSearch;
    @BindView(R.id.iv_home_collection)
    AppCompatImageView mIvHomeCollection;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.vp_home_category)
    ViewPager mVpHomeCategory;
    @BindView(R.id.fab_home_random)
    FloatingActionButton mFabHomeRandom;
    private ObjectAnimator mFabAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
        mPresenter.subscribe();

    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup.LayoutParams layoutParams = mTlHomeToolbar.getLayoutParams();
            layoutParams.height = DisplayUtils.dp2px(80, this);
            mTlHomeToolbar.setLayoutParams(layoutParams);
        } else {
            mIvSetting.setPadding(mIvSetting.getPaddingLeft(),
                    DisplayUtils.dp2px(15, this),
                    mIvSetting.getPaddingRight(),
                    mIvSetting.getPaddingBottom());
        }

        setFabDynamicState();

        String[] titles = {GlobalConfig.CATEGORY_NAME_APP,
                GlobalConfig.CATEGORY_NAME_ANDROID, GlobalConfig.CATEGORY_NAME_IOS,
                GlobalConfig.CATEGORY_NAME_FRONY_END, GlobalConfig.CATEGORY_NAME_RCOMMEND,
                GlobalConfig.CATEGORY_NAME_RESOURCE
        };

        CommViewPagerAdapter commViewPagerAdapter = new CommViewPagerAdapter
                (getSupportFragmentManager(), titles);


        commViewPagerAdapter.addFragment(CategoryFragment.newInstance(titles[0]));
        commViewPagerAdapter.addFragment(CategoryFragment.newInstance(titles[1]));
        commViewPagerAdapter.addFragment(CategoryFragment.newInstance(titles[2]));
        commViewPagerAdapter.addFragment(CategoryFragment.newInstance(titles[3]));
        commViewPagerAdapter.addFragment(CategoryFragment.newInstance(titles[4]));
        commViewPagerAdapter.addFragment(CategoryFragment.newInstance(titles[5]));

        mVpHomeCategory.setAdapter(commViewPagerAdapter);
        mTabHomeCategory.setupWithViewPager(mVpHomeCategory);

    }

    private CollapsingToolbarLayoutState state;

    @OnClick({R.id.iv_setting, R.id.iv_home_collection, R.id.fab_home_random,R.id.ll_home_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:

                break;
            case R.id.iv_home_collection:

                break;
            case R.id.fab_home_random:
                mPresenter.getRandomBanner();
                break;
            case R.id.ll_home_search:
                mPresenter.getRandomBanner();
                break;
        }
    }

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    private void setFabDynamicState() {
        mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;
                    }

                } else if (Math.abs(verticalOffset) >= mAppbar.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        mFabHomeRandom.hide();
                        state = CollapsingToolbarLayoutState.COLLAPSED;

                        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout
                                .LayoutParams) mAppbar.getLayoutParams();
                        layoutParams.height = DisplayUtils.dp2px(240, HomeActivity.this);
                        mAppbar.setLayoutParams(layoutParams);

                        isBigImage = false;
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            mFabHomeRandom.show();
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;
                    }
                }
            }
        });
    }

    @Override
    public void setAppbarColor(int color) {
        mAppbar.setBackgroundColor(color);
        mCollapsingToolbar.setContentScrimColor(color);
    }

    @Override
    public void setFabColor(int color) {
        MDTintUtil.setTint(mFabHomeRandom, color);
    }

    @Override
    public void startFabLoadingAnim() {
        mFabHomeRandom.setImageResource(R.drawable.ic_loading);
        mFabAnimator = ObjectAnimator.ofFloat(mFabHomeRandom, "rotation", 0, 360);
        mFabAnimator.setDuration(1000);
        mFabAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mFabAnimator.setInterpolator(new LinearInterpolator());
        mFabAnimator.start();
    }

    @Override
    public void stopFabLoadingAnim() {
        mFabAnimator.cancel();
        mFabHomeRandom.setImageResource(R.drawable.ic_beauty);
        mFabHomeRandom.setRotation(0);
    }

    @Override
    public void enableFab() {
        mFabHomeRandom.setEnabled(true);
    }

    @Override
    public void unenableFab() {
        mFabHomeRandom.setEnabled(false);
    }

    @Override
    public void cacheImg(final String url) {
        Picasso.with(this)
                .load(url)
                .fetch(new Callback() {
                    @Override
                    public void onSuccess() {
                        mPresenter.saveCacheImg(url);
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    @Override
    public void showTips(String msg) {
        Toasty.error(this, msg, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void setBanner(final String url) {
        Picasso.with(this)
                .load(url)
                .into(mIvHomeBanner, new Callback() {
                    @Override
                    public void onSuccess() {
                        PicassoPalette.with(url, mIvHomeBanner)
                                .intoCallBack(new PicassoPalette.CallBack() {
                                    @Override
                                    public void onPaletteLoaded(Palette palette) {
                                        mPresenter.setThemeColor(palette);
                                    }
                                });
                    }

                    @Override
                    public void onError() {
                        showTips("妹子获取失败");
                    }
                });
    }

    private boolean isBigImage;
    private boolean isUpingAnim;

    @OnClick(R.id.iv_home_banner)
    public void wantBig(View view){
        if(isUpingAnim){
            return;
        }
        startBannerAnim();
    }

    private void startBannerAnim() {
        final CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mAppbar.getLayoutParams();
        ValueAnimator valueAnimator;
        if(isBigImage){
            valueAnimator = ValueAnimator.ofInt(DisplayUtils.getScreenHeight(this), DisplayUtils.dp2px(240, this));
        }else{
            valueAnimator = ValueAnimator.ofInt(DisplayUtils.dp2px(240, this), DisplayUtils
                    .getScreenHeight(this));
        }
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                layoutParams.height = (int) animation.getAnimatedValue();
                mAppbar.setLayoutParams(layoutParams);
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isUpingAnim = false;
                isBigImage = !isBigImage;
            }
        });
        valueAnimator.start();
        isUpingAnim = true;
    }


    @Override
    public void onBackPressed() {
        if(isUpingAnim){
            return;
        }
        if(isBigImage){
            startBannerAnim();
        }else{
            super.onBackPressed();
        }
    }
}
