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
//        dành cho các cửa sổ thường được sử dụng khi người dùng đang
//        giữ màn hình áp vào mặt họ, nó sẽ lọc mạnh luồng sự kiện
//        để ngăn chặn các lần nhấn ngoài ý muốn trong tình huống này có thể
//        không mong muốn đối với một cửa sổ cụ thể, khi xảy ra sự kiện luồng
//        được phát hiện, ứng dụng sẽ nhận được sự kiện chuyển động HỦY để chỉ
//        ra điều này để các ứng dụng có thể xử lý điều này tương ứng bằng cách
//        không thực hiện hành động nào đối với sự kiện cho đến khi ngón tay được thả ra.
//        FLAG_NOT_TOUCH_MODAL
//        Cờ cửa sổ: ngay cả khi cửa sổ này có thể lấy tiêu điểm (FLAG_NOT_FOCUSABLE của nó không được đặt),
//        cho phép bất kỳ sự kiện con trỏ nào bên ngoài cửa sổ được gửi đến các cửa sổ phía sau nó.
        mParams1.width = FrameLayout.LayoutParams.WRAP_CONTENT;
        mParams1.height = FrameLayout.LayoutParams.WRAP_CONTENT;
        boolean result = mWindowManager.addView(mView1, mParams1);
        Toast.makeText(getContext(), "Hiển thị cửa sổ 1:" + result, Toast.LENGTH_LONG).show();

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

//                        - mTouchStartY;//相对于屏幕左上角的位置
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
