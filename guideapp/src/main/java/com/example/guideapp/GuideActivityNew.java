package com.example.guideapp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class GuideActivityNew extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.ll_dots)
    LinearLayout llDots;
    /**
     * 引导页的导图
     */
    private int[] imgs = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.fore};
    private List<View> views;//导图的布局集合

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide_new;
    }

    @Override
    protected void bindEvent() {
    }

    @Override
    protected void initView() {
        setGuideView();
        GuideNewAdapter adapter = new GuideNewAdapter(this, views);
        viewpager.setAdapter(adapter);
        setDotsView();
        scrollDotView(0);
        viewpager.addOnPageChangeListener(this);
        views.get(3).findViewById(R.id.iv_bg).setOnClickListener(this);
    }


    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }

    /**
     * 设置View布局
     */
    private void setGuideView() {
        views = new ArrayList();
        for (int position = 0; position < imgs.length; position++) {
            View view = View.inflate(this, R.layout.item_guide, null);
            ImageView ivBg = (ImageView) view.findViewById(R.id.iv_bg);
            ImageView ivTxt = (ImageView) view.findViewById(R.id.iv_txt);
            ImageView ivEnter = (ImageView) view.findViewById(R.id.iv_enter);
            ivBg.setImageResource(imgs[position]);
            views.add(view);
        }
        ImageView imageview = (ImageView) views.get(0).findViewById(R.id.iv_bg);
        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.enlarge);
        imageview.startAnimation(scaleAnimation);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        scrollDotView(position);
        ImageView imageview = (ImageView) views.get(position).findViewById(R.id.iv_bg);
        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.enlarge);
        imageview.startAnimation(scaleAnimation);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 动态添加点图
     */
    private void setDotsView() {
        for (int i = 0; i < views.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.dots_selector);
            llDots.addView(imageView);
        }
    }

    /**
     * 设置点图的状态切换
     *
     * @param position
     */
    private void scrollDotView(int position) {
        int childCount = llDots.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageview = (ImageView) llDots.getChildAt(i);
            if (i == position) {
                imageview.setEnabled(true);
            } else {
                imageview.setEnabled(false);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = UIUtils.getDimensResource(this, R.dimen.guide_dot_margin);
            imageview.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void onClick(View v) {
        skipMainActivity();
    }

    private void skipMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.iv_skip)
    public void click(View view) {
        skipMainActivity();
    }

}
