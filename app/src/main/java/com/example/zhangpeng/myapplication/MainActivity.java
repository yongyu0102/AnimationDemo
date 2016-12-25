package com.example.zhangpeng.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button alpha;
    private Button rotate;
    private Button scale;
    private Button translate;
    private Button translateRotateBtn;
    private Button alphaRotateBtn;
    private Button alphaJavaBtn;
    private Button rotateJavaBtn;
    private Button frameBtn;
    private Button startMainBtn;
    private Button startListActivity;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化控件
        setOnClick();//设置点击事件
    }

    /**
     * 初始化控件
     */
    public void initView() {
        alpha = (Button) findViewById(R.id.alpha);
        rotate = (Button) findViewById(R.id.rotate);
        scale = (Button) findViewById(R.id.scale);
        image = (ImageView) findViewById(R.id.image);
        translate = (Button) findViewById(R.id.translate);
        translateRotateBtn = (Button) findViewById(R.id.translate_rotate_btn);
        alphaRotateBtn = (Button) findViewById(R.id.alpha_rotate_btn);
        alphaJavaBtn = (Button) findViewById(R.id.alpha_java_btn);
        rotateJavaBtn = (Button) findViewById(R.id.rotate_java_btn);
        frameBtn = (Button) findViewById(R.id.frame_btn);
        startMainBtn = (Button) findViewById(R.id.start_main2_btn);
        startListActivity = (Button) findViewById(R.id.start_lv_activity_btn);
    }

    /**
     * 设置点击事件
     */
    public void setOnClick() {
        alpha.setOnClickListener(this);
        rotate.setOnClickListener(this);
        scale.setOnClickListener(this);
        translate.setOnClickListener(this);
        translateRotateBtn.setOnClickListener(this);
        alphaRotateBtn.setOnClickListener(this);
        alphaJavaBtn.setOnClickListener(this);
        rotateJavaBtn.setOnClickListener(this);
        startMainBtn.setOnClickListener(this);
        startListActivity.setOnClickListener(this);
    }

    /**
     * 实现点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Animation animation;
        switch (v.getId()) {
            case R.id.alpha://透明渐变动画
//                Toast.makeText(this,"点击alpha",Toast.LENGTH_SHORT).show();
                animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                image.startAnimation(animation);
//                Log.d(TAG,"点击alpha");
                break;
            case R.id.scale://缩放动画
//                Log.d(TAG,"点击scale");
                animation = AnimationUtils.loadAnimation(this, R.anim.scale);
                image.startAnimation(animation);
//                Toast.makeText(this,"点击",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rotate://旋转动画
                animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
                image.startAnimation(animation);
                break;
            case R.id.translate: //移动动画
                animation = AnimationUtils.loadAnimation(this, R.anim.translate);
                image.startAnimation(animation);
                break;
            case R.id.frame_btn://帧动画
//              image.setImageResource(R.drawable.frame_animation_list);
                //设置图片成src资源文件形式
//                image.setImageResource(R.drawable.frame_animation_list);
//                AnimationDrawable animationDrawab= (AnimationDrawable) image.getDrawable();
//                animationDrawab.start();
//                将图片设置成背景图片
                image.setBackgroundResource(R.drawable.frame_animation_list);
                AnimationDrawable frameAnimation = (AnimationDrawable) image.getBackground();
                frameAnimation.start();
                break;
            case R.id.translate_rotate_btn://先平移再旋转
                animation = AnimationUtils.loadAnimation(this, R.anim.translate);
                image.startAnimation(animation);
                //为平移动画设置监听器,在平移结束时旋转动画
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
                        image.startAnimation(animation1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case R.id.alpha_rotate_btn:
                animation = AnimationUtils.loadAnimation(this, R.anim.alpha_rotate);
                image.startAnimation(animation);
                break;
            case R.id.alpha_java_btn://java代码中设置动画
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                alphaAnimation.setDuration(2000);
                alphaAnimation.setRepeatCount(4);
                alphaAnimation.setRepeatMode(Animation.REVERSE);
                image.startAnimation(alphaAnimation);
                break;
            case R.id.rotate_java_btn:
                RotateAnimation rotateAnimation = new RotateAnimation(0.1f, 360.0f, 50.0f, 50.0f);
                rotateAnimation.setDuration(3000);
                rotateAnimation.setRepeatCount(2);
                image.startAnimation(rotateAnimation);
                break;
            case R.id.start_main2_btn:
                //启动Activity带动画
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                break;
            case R.id.start_lv_activity_btn:
                Intent intent1 = new Intent(this, ListViewActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);

        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
