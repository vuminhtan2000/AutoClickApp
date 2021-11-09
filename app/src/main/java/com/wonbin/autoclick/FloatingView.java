package com.wonbin.autoclick;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by wilburnLee on 2019/4/22.
 */
public  class FloatingView extends FrameLayout implements View.OnClickListener {
    public static final String ACTION = "action";
    public static final String SHOW = "show";
    public static final String HIDE = "hide";
    public static final String PLAY = "play";
    public static final String STOP = "stop";

    public static final String MODE = "mode";
    public static final String TAP = "tap";
    public static final String SWIPE = "swipe";


    private FloatingView mFloatingView;
    private int mInterval;
    private int mX;
    private int mY;
    private String mMode;

    private Handler mHandler;

    private Context mContext;
    private View mView,mView1;
    private ImageView mPlayView;
    private ImageView mStopView;
    private ImageView mCloseView;
    private ImageView add;
    private ImageView hide;

    private int mTouchStartX, mTouchStartY;
    private WindowManager.LayoutParams mParams, mParams1;
    private FloatingManager mWindowManager;
    private String mCurState;

    public FloatingView(Context context) {
        super(context);
        mContext = context.getApplicationContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        mView = mLayoutInflater.inflate(R.layout.floating_view, null);

        mPlayView = (ImageView) mView.findViewById(R.id.play);
        mStopView = (ImageView) mView.findViewById(R.id.stop);
        mCloseView = (ImageView) mView.findViewById(R.id.close);
        add = (ImageView) mView.findViewById(R.id.add);
        hide = (ImageView) mView.findViewById(R.id.hide);

        mPlayView.setOnClickListener(this);
        mStopView.setOnClickListener(this);
        mCloseView.setOnClickListener(this);
        add.setOnClickListener(this);
        hide.setOnClickListener(this);

        mView.setOnTouchListener(mOnTouchListener);
        mWindowManager = FloatingManager.getInstance(mContext);

    }

    public void show() {
        mParams = new WindowManager.LayoutParams();
        mParams.gravity = Gravity.LEFT;

        if (Build.VERSION.SDK_INT >= 26) {
            mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        mParams.width = LayoutParams.WRAP_CONTENT;
        mParams.height =LayoutParams.WRAP_CONTENT;
        boolean result = mWindowManager.addView(mView, mParams);



        Toast.makeText(getContext(), "Hiển thị cửa sổ:" + result, Toast.LENGTH_LONG).show();


    }

    public void hide() {
        mWindowManager.removeView(mView);

    }

    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchStartX = (int) event.getRawX();
                    mTouchStartY = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!AutoService.PLAY.equals(mCurState)) {
                        mParams.x += (int) event.getRawX() - mTouchStartX;
                        mParams.y += (int) event.getRawY() - mTouchStartY;//相对于屏幕左上角的位置
                        mWindowManager.updateView(mView, mParams);
                        mTouchStartX = (int) event.getRawX();
                        mTouchStartY = (int) event.getRawY();
                    }
                    break;
                case MotionEvent.ACTION_BUTTON_PRESS:
                    if (!AutoService.PLAY.equals(mCurState)) {
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    };


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), AutoService.class);


        switch (view.getId()) {
            case R.id.play:
                mCurState = AutoService.PLAY;
             intent.putExtra(AutoService.ACTION, AutoService.PLAY);
                break;
            case R.id.stop:
                mCurState = AutoService.STOP;
                intent.putExtra(AutoService.ACTION, AutoService.STOP);
                break;
            case R.id.close:
                intent.putExtra(AutoService.ACTION, AutoService.HIDE);
                Intent appMain = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(appMain);
                break;
            case R.id.add:

                    intent.putExtra(AutoService.ACTION, AutoService.ADD);



                break;
            case R.id.hide:

                    intent.putExtra(AutoService.ACTION, AutoService.HIDE1);




                break;
        }

        getContext().startService(intent);
    }

}
