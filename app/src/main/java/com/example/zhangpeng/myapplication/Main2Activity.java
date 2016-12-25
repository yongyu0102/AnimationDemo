package com.example.zhangpeng.myapplication;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart;
    private ImageView ivShow;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        initListener();
    }

    private void initView() {
        btnStart = (Button) findViewById(R.id.btn_start);
        ivShow = (ImageView) findViewById(R.id.iv_show);
        fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    private void initListener() {
        btnStart.setOnClickListener(this);
        ivShow.setOnClickListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * 将 View 沿着垂直方向移动 View 高度的距离
     *
     * @param targetView 被移动的 View
     */
    private void translateViewByObjectAnimator(View targetView) {
        //targetView 被移动的 View
        //TranslationY 目标 View 要改变的属性
        //ivShow.getHeight() 要移动的距离
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(targetView, "TranslationY", ivShow.getHeight());
        objectAnimator.start();
    }

    /**
     * 改变 View 对象的背景色
     *
     * @param targetView
     */
    private void changeViewBackGroundColor(View targetView) {
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(targetView, "backgroundColor", Color.RED, Color.BLUE);
        valueAnimator.setDuration(3000);
        //设置估值器，该处插入颜色估值器
        valueAnimator.setEvaluator(new ArgbEvaluator());
        //无限循环
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //翻转模式
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
    }

    /**
     * 启动一个动画集合
     *
     * @param targetView
     */
    private void startAnimationSet(View targetView) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(targetView, "rotationX", 0, 360),
                ObjectAnimator.ofFloat(targetView, "rotationY", 0, 360),
                ObjectAnimator.ofFloat(targetView, "rotation", 0, -90),
                ObjectAnimator.ofFloat(targetView, "translationX", 0, 90),
                ObjectAnimator.ofFloat(targetView, "translationY", 0, 90),
                ObjectAnimator.ofFloat(targetView, "scaleX", 1, 1.5f),
                ObjectAnimator.ofFloat(targetView, "scaleY", 1, 1.5f),
                ObjectAnimator.ofFloat(targetView, "alpha", 1, 0.25f, 1));
        animatorSet.setDuration(3000).start();
    }

    /**
     * 通过使用 XML 文件来实现一个属性动画
     *
     * @param targetView
     */
    private void starTAnimatorByXML(View targetView) {
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.value_animator);
        animatorSet.setTarget(targetView);
        animatorSet.start();
    }

    /**
     * 无效代码
     *
     * @param button
     */
    private void performAnimation(View button) {
        ObjectAnimator.ofInt(button, "width", 500)
                .setDuration(200)
                .start();
    }

    /**
     * 将 Button 沿着 X 轴方向放大
     *
     * @param button
     */
    private void performAnimationByWrapper(View button) {
        ViewWrapper viewWrapper = new ViewWrapper(button);
        ObjectAnimator.ofInt(viewWrapper, "width", 800)
                .setDuration(5000)
                .start();
    }


    private IntEvaluator intEvaluator = new IntEvaluator();

    private void performAnimatorByValue(final View targetView, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取当前动画进度值
                int currentValue = (int) animation.getAnimatedValue();
                //获取当前进度占整个动画比例
                int fraction = (int) animation.getAnimatedFraction();
                //直接通过估值器根据当前比例计算当前 View 的宽度,然后设置给 View
                targetView.getLayoutParams().width = intEvaluator.evaluate(fraction, start, end);
                targetView.requestLayout();
            }
        });
        valueAnimator.setDuration(500)
                .start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
//                实现平移动画
//                translateViewByObjectAnimator(ivShow);

                //实现改变背景色
//                changeViewBackGroundColor(ivShow);

                //启动一个动画集合
//                startAnimationSet(ivShow);

//                通过 XML 文件来实现一个属性动画
//                starTAnimatorByXML(ivShow);

//                将 Button 沿着 X 轴方向放大
//                performAnimationByWrapper(btnStart);
//                performAnimation(btnStart);
                performAnimatorByValue(btnStart, btnStart.getWidth(), 800);
                break;
            case R.id.iv_show:
                Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private class ViewWrapper {

        private View targetView;

        public ViewWrapper(View targetView) {
            this.targetView = targetView;
        }

        public int getWidth() {
            //注意调用此函数能得到 View 的宽度的前提是， View 的宽度是 精准测量模式，即不可以是 wrap_content
            //否则得不到正确的测量值
            return targetView.getLayoutParams().width;
        }

        public void setWidth(int width) {
            targetView.getLayoutParams().width = width;
            targetView.requestLayout();
        }
    }
}
