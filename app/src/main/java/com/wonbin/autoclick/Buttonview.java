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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public  class Buttonview extends FrameLayout {
    private Context mContext;
    private View mView1;
    private ImageView mPlayView1;
    private int mTouchStartX, mTouchStartY;
    private WindowManager.LayoutParams mParams, mParams1;
    private FloatingManager mWindowManager;
    private String mCurState;
    public Buttonview(Context context) {
        super(context);
        mContext = context.getApplicationContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        mView1 = mLayoutInflater.inflate(R.layout.button, null);
        mView1.setOnTouchListener(mOnTouchListener);
        mWindowManager = FloatingManager.getInstance(mContext);
//        mPlayView1 = (ImageView) mView1.findViewById(R.id.play_nho);
//        mPlayView1.setOnClickListener(this);
    }



    public boolean show() {
        mParams1 = new WindowManager.LayoutParams();
        mParams1.gravity = Gravity.CENTER;
        if (Build.VERSION.SDK_INT >= 26) {
            mParams1.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mParams1.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        mParams1.format = PixelFormat.RGBA_8888;
        mParams1.flags= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
//        mParams1.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
//                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
//                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
//        FLAG_IGNORE_CHEEK_PRESSES
//        d??nh cho c??c c???a s??? th?????ng ???????c s??? d???ng khi ng?????i d??ng ??ang
//        gi??? m??n h??nh ??p v??o m???t h???, n?? s??? l???c m???nh lu???ng s??? ki???n
//        ????? ng??n ch???n c??c l???n nh???n ngo??i ?? mu???n trong t??nh hu???ng n??y c?? th???
//        kh??ng mong mu???n ?????i v???i m???t c???a s??? c??? th???, khi x???y ra s??? ki???n lu???ng
//        ???????c ph??t hi???n, ???ng d???ng s??? nh???n ???????c s??? ki???n chuy???n ?????ng H???Y ????? ch???
//        ra ??i???u n??y ????? c??c ???ng d???ng c?? th??? x??? l?? ??i???u n??y t????ng ???ng b???ng c??ch
//        kh??ng th???c hi???n h??nh ?????ng n??o ?????i v???i s??? ki???n cho ?????n khi ng??n tay ???????c th??? ra.
//        FLAG_NOT_TOUCH_MODAL
//        C??? c???a s???: ngay c??? khi c???a s??? n??y c?? th??? l???y ti??u ??i???m (FLAG_NOT_FOCUSABLE c???a n?? kh??ng ???????c ?????t),
//        cho ph??p b???t k??? s??? ki???n con tr??? n??o b??n ngo??i c???a s??? ???????c g???i ?????n c??c c???a s??? ph??a sau n??.
        mParams1.width = FrameLayout.LayoutParams.WRAP_CONTENT;
        mParams1.height = FrameLayout.LayoutParams.WRAP_CONTENT;
        boolean result = mWindowManager.addView(mView1, mParams1);
        Toast.makeText(getContext(), "Hi???n th??? c???a s??? 1:" + result, Toast.LENGTH_LONG).show();

        return result;
    }

    public void hide() {
        mWindowManager.removeView(mView1);

    }

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            Intent aaa = new Intent( getContext(),AutoService.class);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchStartX = (int) event.getRawX();
                    mTouchStartY = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mParams1.x+= (int) event.getRawX() - mTouchStartX;
                    mParams1.y += (int) event.getRawY()- mTouchStartY ;
                    mWindowManager.updateView1(mView1, mParams1);
                    int[] location = new int[2];
                    mView1.getLocationOnScreen(location);
                    Log.d(VIEW_LOG_TAG,"Move: location: " + location[0]+"Move: location1: " +location[1]);
                    aaa.putExtra(AutoService.ACTION, AutoService.MODE);
                    aaa.putExtra("x1",location[0]+50);
                    aaa.putExtra("y1",location[1]-1);

                    getContext().startService(aaa);
                    if (!AutoService.PLAY.equals(mCurState)) {

//                        - mTouchStartY;//?????????????????????????????????
                        mWindowManager.updateView1(mView1, mParams1);
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
//        Intent bt1 = new Intent(getContext(), Delay_time_view1.class);
//        switch (view.getId()) {
//            case R.id.play_nho:
//
//                getContext().startActivity(bt1);
//                break;
//        }
//    }


}
