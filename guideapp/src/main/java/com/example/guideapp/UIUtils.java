package com.example.guideapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * 创建日期：Create by cherry on 2017/4/21
 * 描述： 资源文件加载
 * 作者：@cherry
 */

public class UIUtils {

    public static int getColorResource(Context context, int color) {
        return context.getResources().getColor(color);
    }

    public static String getStringResource(Context context, int string) {
        return context.getResources().getString(string);
    }

    public static Drawable getDrawableResource(Context context, int drawable) {
        return context.getResources().getDrawable(drawable);
    }

    public static String[] getArrayResource(Context context, int arrays) {
        Resources res = context.getResources();
        String[] languages = res.getStringArray(arrays);
        return languages;
    }


    public static int getDimensResource(Context context, int dimens) {
        Resources resources = context.getResources();
        int dimension = resources.getDimensionPixelOffset(dimens);
        return dimension;
    }
}
