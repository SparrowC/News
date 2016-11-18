package com.kun.news.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by jiangkun on 16/9/24.
 */

public class UIUtils {

    public static final char ELLIPSIS_CHAR = '\u2026';
    public static final int LAYOUT_PARAMS_KEEP_OLD = -3;

    public static final boolean API_ET_20 = Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT;

    public static void displayToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static float sp2px(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public static float dip2Px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dipValue * scale + 0.5f;
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public final static int getScreenHeight(Context context) {
        if (context == null) {
            return 0;
        }

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        return (dm == null) ? 0 : dm.heightPixels;
    }

    public final static int getScreenWidth(Context context) {
        if (context == null) {
            return 0;
        }

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        return (dm == null) ? 0 : dm.widthPixels;
    }

    public static int getDiggBuryWidth(Context context) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenWidth = screenWidth * 1375 / 10000 + (int) (UIUtils.dip2Px(context, 20));
        return screenWidth;
    }

    public final static int getStatusBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private static boolean visibilityValid(int visiable) {
        return visiable == View.VISIBLE || visiable == View.GONE || visiable == View.INVISIBLE;
    }

    public final static void setViewVisibility(View v, int visiable) {
        if (v == null || v.getVisibility() == visiable || !visibilityValid(visiable)) {
            return;
        }
        v.setVisibility(visiable);
    }

    public final static boolean isViewVisible(View view) {
        if (view == null) {
            return false;
        }

        return view.getVisibility() == View.VISIBLE;
    }


    public static int floatToIntBig(float value) {
        return (int) (value + 0.999f);
    }

    public static class EllipsisMeasureResult {
        public String ellipsisStr;
        public int length;
    }

    public static EllipsisMeasureResult sTempEllipsisResult = new EllipsisMeasureResult();

    public static void requestOrienation(Activity activity, boolean landscape) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.setRequestedOrientation(landscape ?
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (landscape) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public static int getIndexInParent(View view) {
        if (view == null || view.getParent() == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            return ((ViewGroup) parent).indexOfChild(view);
        }
        return -1;
    }

    public static boolean clearAnimation(View view) {
        if (view == null || view.getAnimation() == null) {
            return false;
        }
        view.clearAnimation();
        return true;
    }

}
