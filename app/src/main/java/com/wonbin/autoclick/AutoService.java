package com.wonbin.autoclick;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.Intent;
import android.graphics.Path;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

/**
 * Created by wilburnLee on 2019/4/22.
 */
public class AutoService extends AccessibilityService {
    public static final String TIME = "time";
    public static final String TIME1= "time1";
    public static final String TIME2= "time2";
    public static final String ACTION = "action";
    public static final String SHOW = "show";
    public static final String HIDE = "hide";
    public static final String HIDE1 = "hide1";
    public static final String HIDE2 = "hide2";
    public static final String HIDE3 = "hide3";
    public static final String PLAY = "play";
    public static final String STOP = "stop";
    public static final String ADD = "add";
    public static final String ADD1 = "add1";
    public static final String ADD2 = "add2";
    public static final String MODE = "mode";
    public static final String TAP = "tap";
    public static final String SWIPE = "swipe";
    public static final String LOCO = "Loco";
    public static final String LOCO1 = "Loco1";

    private Buttonview mbutoon;
    private Buttonview2 mbutoon1;
    private Buttonview3 mbutoon2;
    private FloatingView mFloatingView;
    private int mInterval = 0;
    private int mInterval_after = 0;
    private int timenext = 0;
    private int btnshow =0;
    private int time = 0;
    private int time1 = 0;
    private int time2 = 0;
    private int time3 = 0;
    private int mX;
    private int mY;
    private int mX1;
    private int mY1;
    private int mX2;
    private int mY2;
    private String mMode;
    private Handler mHandler,mHandler1,mHandler2;
    @Override
    public void onCreate() {
        super.onCreate();
        mFloatingView = new FloatingView(this);
        mbutoon = new Buttonview(this);
        mbutoon1 = new Buttonview2(this);
        mbutoon2 = new Buttonview3(this);
//        delay_time_view = new Delay_time_view();
        HandlerThread handlerThread = new HandlerThread("auto-handler");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
        mHandler1 = new Handler(handlerThread.getLooper());
        mHandler2 = new Handler(handlerThread.getLooper());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getStringExtra(ACTION);
            if (SHOW.equals(action)) {
               mMode = intent.getStringExtra(MODE);
                mFloatingView.show();
            } else if (HIDE.equals(action)) {
                mFloatingView.hide();
                mbutoon.hide();
                mbutoon1.hide();
                mbutoon2.hide();
                mHandler.removeCallbacksAndMessages(null);
                mHandler1.removeCallbacksAndMessages(null);
                mHandler2.removeCallbacksAndMessages(null);

            } else if (HIDE1.equals(action)) {
                mbutoon.hide();
                mbutoon1.hide();
                mbutoon2.hide();
                mHandler.removeCallbacksAndMessages(null);
                mHandler1.removeCallbacksAndMessages(null);
                mHandler2.removeCallbacksAndMessages(null);

            } else if (PLAY.equals(action)) {

                if (mRunnable == null ) {

                    mRunnable = new IntervalRunnable();
                    mRunnable1 = new IntervalRunnable1();
                    mRunnable2 = new IntervalRunnable2();
                }
                if(btnshow==1) {
                    mHandler.postDelayed(mRunnable, mInterval);

                }else if(btnshow==2)
                {
                    mHandler.postDelayed(mRunnable, mInterval);
                    mHandler1.postDelayed(mRunnable1, mInterval + timenext);


                }else if(btnshow==3)
                {
                    mHandler.postDelayed(mRunnable, mInterval);
                    mHandler1.postDelayed(mRunnable1, mInterval + timenext);
                    mHandler2.postDelayed(mRunnable2, mInterval + timenext + timenext);
                }

                Toast.makeText(getBaseContext(), "Started", Toast.LENGTH_SHORT).show();

                } else if (MODE.equals(action)) {
                mX = intent.getIntExtra("x1",0);
                mY = intent.getIntExtra("y1",0);
            } else if (LOCO.equals(action)) {
                mX1 = intent.getIntExtra("x2",0);
                mY1 = intent.getIntExtra("y2",0);
            } else if (LOCO1.equals(action)) {
                mX2 = intent.getIntExtra("x3",0);
                mY2 = intent.getIntExtra("y3",0);
            } else if (STOP.equals(action)) {
                mHandler.removeCallbacksAndMessages(null);
                mHandler1.removeCallbacksAndMessages(null);
                mHandler2.removeCallbacksAndMessages(null);
                Toast.makeText(getBaseContext(), "Stop", Toast.LENGTH_SHORT).show();
            }
            else if (TIME.equals(action)) {
                mInterval = intent.getIntExtra("interval", 0) * 1000;
                mInterval_after = intent.getIntExtra("interval_after",0) * 1000;                mInterval_after = intent.getIntExtra("interval_after",0) * 1000;
                timenext = intent.getIntExtra("time_next",0) * 1000;
                btnshow = intent.getIntExtra("btn",0);
                if(btnshow ==1){
                    mbutoon.show();
                }else if(btnshow==2)
                {
                    mbutoon.show();
                    mbutoon1.show();
                }else if (btnshow ==3){
                    mbutoon.show();
                    mbutoon1.show();
                    mbutoon2.show();
                }


            }

             else if (ADD.equals(action)) {

                Intent bt1 = new Intent(this, Delay_time_view.class);
                startActivity(bt1);
            }


        }
        return super.onStartCommand(intent, flags, startId);
    }
    private void playTap2(int x2, int y2) {
        Path path = new Path();
        path.moveTo(x2, y2);
        path.lineTo(x2, y2);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(path, 10L, 10L));
        GestureDescription gestureDescription = builder.build();
        dispatchGesture(gestureDescription, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                if (mInterval== 0 && mInterval_after == 0) {
                    mHandler2.postDelayed(mRunnable2, 1000);
                } else {
                    mHandler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            mHandler2.removeCallbacksAndMessages(null);

                        }
                    }, mInterval_after);
                }
            }
            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
            }
        }, null);
    }
    private void playTap1(int x1, int y1) {
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x1, y1);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(path, 10L, 10L));
        GestureDescription gestureDescription = builder.build();
        dispatchGesture(gestureDescription, new GestureResultCallback() {
            @Override

            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                if (mInterval == 0 && mInterval_after == 0) {
                    mHandler1.postDelayed(mRunnable1, 1000);
                } else  {
                    mHandler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mHandler1.removeCallbacksAndMessages(null);

                        }
                    }, mInterval_after);
                }
            }
            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
            }
        }, null);
    }
    private void playTap(int x, int y) {
        Path path = new Path();
        path.moveTo(x, y);
        path.lineTo(x, y);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(path, 10L, 10L));
        GestureDescription gestureDescription = builder.build();
        dispatchGesture(gestureDescription, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                if (mInterval == 0 && mInterval_after == 0) {
                    mHandler.postDelayed(mRunnable, 1000);
                } else  {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mHandler.removeCallbacksAndMessages(null);

                        }
                    }, mInterval_after);
                }
            }
            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
            }
        }, null);
    }
    private void playSwipe(int fromX, int fromY, int toX, int toY) {
        Path path = new Path();
        path.moveTo(fromX, fromY);
        path.lineTo(toX, toY);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(path, 100L, 1000L));
        GestureDescription gestureDescription = builder.build();
        dispatchGesture(gestureDescription, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                mHandler.postDelayed(mRunnable, mInterval);
            }
            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
            }
        }, null);
    }
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

    }
    @Override
    public void onInterrupt() {
    }
    private IntervalRunnable mRunnable;

    private class IntervalRunnable implements Runnable {
        @Override
        public void run() {
            if (SWIPE.equals(mMode)) {
                playSwipe(mX, mY, mX, mY - 300);
            } else {
                playTap(mX, mY);
            }
        }
    }
    private IntervalRunnable1 mRunnable1;
    private class IntervalRunnable1 implements Runnable {
        @Override
        public void run() {
            if (SWIPE.equals(mMode)) {
                playSwipe(mX, mY, mX, mY - 300);
            } else {
                playTap1(mX1,mY1);
            }
        }
    }
    private IntervalRunnable2 mRunnable2;
    private class IntervalRunnable2 implements Runnable {
        @Override
        public void run() {
            if (SWIPE.equals(mMode)) {
                playSwipe(mX, mY, mX, mY - 300);
            } else {
                playTap2(mX2,mY2);
            }
        }
    }
}
