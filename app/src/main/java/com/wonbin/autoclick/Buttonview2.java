package com.wonbin.autoclick;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class Buttonview2 extends FrameLayout {

    private Context mContext;
    private View mView2;
    private ImageView mPlayView;
    private ImageView mStopView;
    private ImageView mCloseView;
    private int mTouchStartX, mTouchStartY;//手指按下时坐标
    private WindowManager.LayoutParams mParams, mParams2;
    private FloatingManager mWindowManager;
    private String mCurState;
    public Buttonview2(Context context) {
        super(context);
        mContext = context.getApplicationContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        mView2 = mLayoutInflater.inflate(R.layout.button2, null);
        mView2.setOnTouchListener(mOnTouchListener);
        mWindowManager = FloatingManager.getInstance(mContext);
//        mPlayView = (ImageView) mView2.findViewById(R.id.play_nho1);
//        mPlayView.setOnClickListener(this);
    }

    public boolean show() {
        mParams2 = new WindowManager.LayoutParams();
        mParams2.gravity = Gravity.CENTER;
//        mParams2.x = 550;
//        mParams2.y =1350;
        if (Build.VERSION.SDK_INT >= 26) {
            mParams2.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mParams2.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        //设置图片格式，效果为背景透明
        mParams2.format = PixelFormat.RGBA_8888;
        mParams2.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        mParams2.width = LayoutParams.WRAP_CONTENT;
        mParams2.height = LayoutParams.WRAP_CONTENT;
        boolean result = mWindowManager.addView1(mView2, mParams2);
        Toast.makeText(getContext(), "Hiển thị cửa sổ2:" + result, Toast.LENGTH_LONG).show();
        return result;
    }

    public void hide() {
        mWindowManager.removeView(mView2);

    }

    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            Intent bbb = new Intent( getContext(),AutoService.class);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchStartX = (int) event.getRawX();
                    mTouchStartY = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mParams2.x+= (int) event.getRawX() - mTouchStartX;

                    mParams2.y += (int) event.getRawY()- mTouchStartY ;
                    mWindowManager.updateView2(mView2, mParams2);
                    int[] location1 = new int[2];
                    mView2.getLocationOnScreen(location1);
                    Log.d(VIEW_LOG_TAG,"Move: location1: " + location1[0]+"Move: location2: " +location1[1]);
                    bbb.putExtra(AutoService.ACTION, AutoService.LOCO);
                    bbb.putExtra("x2",location1[0]+50);
                    bbb.putExtra("y2",location1[1]-1);
                    getContext().startService(bbb);
                    if (!AutoService.PLAY.equals(mCurState)) {
                        mWindowManager.updateView1(mView2, mParams2);
                        mTouchStartX = (int) event.getRawX();
                        mTouchStartY = (int) event.getRawY();
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    };
//    @Override
//    public void onClick(View view) {
//        Intent cccc = new Intent(getContext(), Delay_time_view1.class);
//        switch (view.getId()) {
//            case R.id.play_nho1:
//
//                getContext().startActivity(cccc);
//                break;
//        }
//    }


}
