package com.example.guideapp;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;

/**
 * 创建日期：Create by cherry on 2017/5/17
 * 描述：Activity 的基类
 * 作者：@cherry
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected BaseActivity mContext;
    //页面布局的 根View
    protected View mContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        //设置不能横屏
        setStatusBar();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(getLayoutId());
        //注册监听函数
        mContext = this;
    }

    protected void setStatusBar() {

    }

    protected void initWindow() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    /**
     * 布局Id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 加载布局
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mContentView = view;
        //初始化页面
        init();
    }


    /**
     * 初始化界面
     */
    private void init() {
        ButterKnife.bind(this);
        initView();
        bindEvent();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    //绑定事件
    protected abstract void bindEvent();

    //初始化view
    protected abstract void initView();

    /**
     * 跳转页面
     *
     * @param clazz
     */
    public void skipAct(Class clazz) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        startActivity(intent);
    }

    public void skipAct(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        startActivity(intent);
    }

    public void skipAct(Class clazz, Bundle bundle, int flags) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        intent.setFlags(flags);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        //Acitvity 释放子view资源
        mContentView = null;
        mContext = null;
        super.onDestroy();
    }
}
