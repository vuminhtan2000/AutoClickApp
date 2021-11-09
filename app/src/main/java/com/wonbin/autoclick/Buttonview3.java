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

public class Buttonview3 extends FrameLayout {
    private Context mContext;
    private View mView3;
    private int mTouchStartX, mTouchStartY;
    private WindowManager.LayoutParams  mParams3;
    private FloatingManager mWindowManager;
    private String mCurState;
    public Buttonview3(Context context) {
        super(context);
        mContext = context.getApplicationContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        mView3 = mLayoutInflater.inflate(R.layout.button3, null);
        mView3.setOnTouchListener(mOnTouchListener);
        mWindowManager = FloatingManager.getInstance(mContext);

    }

    public boolean show() {
        mParams3 = new WindowManager.LayoutParams();
        mParams3.gravity = Gravity.CENTER;
        if (Build.VERSION.SDK_INT >= 26) {
            mParams3.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mParams3.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        //设置图片格式，效果为背景透明
        mParams3.format = PixelFormat.RGBA_8888;
        mParams3.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        mParams3.width = LayoutParams.WRAP_CONTENT;
        mParams3.height = LayoutParams.WRAP_CONTENT;
        boolean result = mWindowManager.addView2(mView3, mParams3);
        Toast.makeText(getContext(), "Hiển thị cửa sổ3:" + result, Toast.LENGTH_LONG).show();
        return result;
    }

    public void hide() {
        mWindowManager.removeView(mView3);

    }

    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            Intent btn3 = new Intent( getContext(),AutoService.class);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchStartX = (int) event.getRawX();
                    mTouchStartY = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mParams3.x+= (int) event.getRawX() - mTouchStartX;

                    mParams3.y += (int) event.getRawY()- mTouchStartY ;
                    mWindowManager.updateView3(mView3, mParams3);
                    int[] location2 = new int[2];
                    mView3.getLocationOnScreen(location2);
                    Log.d(VIEW_LOG_TAG,"Move: location1: " + location2[0]+"Move: location2: " +location2[1]);
                    btn3.putExtra(AutoService.ACTION, AutoService.LOCO1);
                    btn3.putExtra("x3",location2[0]+50);
                    btn3.putExtra("y3",location2[1]-1);
                    getContext().startService(btn3);
                    if (!AutoService.PLAY.equals(mCurState)) {
                        mWindowManager.updateView1(mView3, mParams3);
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
