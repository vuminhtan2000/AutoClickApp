package com.wonbin.autoclick;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by wilburnLee on 2019/4/22.
 */
public class FloatingManager {
    private WindowManager mWindowManager;
    private static FloatingManager mInstance;
    private Context mContext;

    public static FloatingManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new FloatingManager(context);
        }
        return mInstance;
    }

    private FloatingManager(Context context) {
        mContext = context;
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);//获得WindowManager对象
    }

    /**
     * 添加悬浮窗
     *
     * @param view
     * @param params
     * @return
     */
    protected boolean addView(View view, WindowManager.LayoutParams params) {
        try {
            mWindowManager.addView(view, params);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    protected boolean addView1(View view1, WindowManager.LayoutParams params1) {
        try {
            mWindowManager.addView(view1, params1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    protected boolean addView2(View view3, WindowManager.LayoutParams params3) {
        try {
            mWindowManager.addView(view3, params3);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 移除悬浮窗
     *
     * @param view
     * @return
     */
    protected boolean removeView(View view) {
        try {
            mWindowManager.removeView(view);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新悬浮窗参数
     *
     * @param view
     * @param params
     * @return
     */
    protected boolean updateView(View view, WindowManager.LayoutParams params) {
        try {
            mWindowManager.updateViewLayout(view, params);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    protected boolean updateView1(View view1, WindowManager.LayoutParams params1) {
        try {
            mWindowManager.updateViewLayout(view1, params1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    protected boolean updateView2(View view2, WindowManager.LayoutParams params2) {
        try {
            mWindowManager.updateViewLayout(view2, params2);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    protected boolean updateView3(View view3, WindowManager.LayoutParams params3) {
        try {
            mWindowManager.updateViewLayout(view3, params3);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
