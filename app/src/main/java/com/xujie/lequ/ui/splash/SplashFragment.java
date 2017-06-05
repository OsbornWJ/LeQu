package com.xujie.lequ.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xujie.lequ.R;
import com.xujie.lequ.app.ActivityManager;
import com.xujie.lequ.base.BaseFragment;

import butterknife.BindView;

/**
 * @author wj
 * @date 2017/6/2
 * @discription
 */
public class SplashFragment extends BaseFragment<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.splash)
    ImageView mSplashImg;

    private ScaleAnimation scaleAnimation;

    public static SplashFragment getInstance() {
        SplashFragment splashFragment = new SplashFragment();
        return splashFragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    private void initAnim() {
        scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(2500);
        mSplashImg.startAnimation(scaleAnimation);

        //缩放动画监听
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /*startActivity(new Intent(getActivity(), HomeActivity.class));
                ActivityManager.getInstance().finishActivity();*/
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void showGirl(String girlUrl) {
        Glide.with(getActivity())
                .load(girlUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mSplashImg);
    }

    @Override
    public void showGirl() {

    }
}
