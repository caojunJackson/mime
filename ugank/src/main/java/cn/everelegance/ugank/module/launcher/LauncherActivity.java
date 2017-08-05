package cn.everelegance.ugank.module.launcher;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.everelegance.ugank.R;
import cn.everelegance.ugank.module.home.HomeActivity;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/8/4.
 */

public class LauncherActivity extends AppCompatActivity implements LauncherContract.View {

    @BindView(R.id.iv_backBG)
    AppCompatImageView mIvBackBG;

    private boolean isResume;
    private LauncherContract.Presenter mPresenter = new LauncherPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
        mPresenter.subscribe();
    }

    @Override
    public void goHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out);
        finish();
    }

    @Override
    public void loadImg(String url) {
        Picasso.with(this)
                .load(url)
                .into(mIvBackBG, new Callback() {
                    @Override
                    public void onSuccess() {
                        Observable.timer(1200 , TimeUnit.MILLISECONDS)
                                .subscribe(new Action1<Long>() {
                                    @Override
                                    public void call(Long aLong) {

                                        if (isResume) {
                                            goHomeActivity();
                                        }else{
                                            finish();
                                        }
                                    }
                                });
                    }

                    @Override
                    public void onError() {
                        goHomeActivity();
                    }
                });
    }

    @Override
    protected void onResume() {
        isResume = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isResume = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

    }
}
