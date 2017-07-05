package com.mobile.app.javashop.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.mobile.app.javashop.utils.RouterUtils;
import com.yms.apt.router.TRouter;
import com.yms.aptlib.router.Router;

import com.mobile.app.javashop.Config;
import com.mobile.app.javashop.R;

/**
 * Created by LDD on 17/3/30.
 */
@Router(Config.WELCOME)
public class Spala extends AppCompatActivity {
    private LottieAnimationView animationView;
    private TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.spach);
        animationView = (LottieAnimationView) findViewById(R.id.anmition);
        textView = (TextView) findViewById(R.id.logo);
        animationView.setAnimation("cube_loader.json");
        animationView.playAnimation();
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                Animation animation1 = new AlphaAnimation(0,1);
                animation1.setDuration(1500);
                animation1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finish();
                        overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
                        RouterUtils.init(Config.HOME).go();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                textView.setAnimation(animation1);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
