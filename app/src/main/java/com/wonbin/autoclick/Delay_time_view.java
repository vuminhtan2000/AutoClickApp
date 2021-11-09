package com.wonbin.autoclick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Delay_time_view extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText timenext;

    Button btn_time;
//    ArrayList<Array_Button> array_buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_time_view);
        addControl();
        addEvent();
    }

    private void addEvent() {
        final Intent intent = new Intent(this, AutoService.class);
        btn_time.setOnClickListener(new View.OnClickListener()
            {
            @Override
            public void onClick(View view) {
                intent.putExtra(AutoService.ACTION, AutoService.TIME);
                intent.putExtra("interval", Integer.valueOf(editText1.getText().toString()));
                intent.putExtra("interval_after", Integer.valueOf(editText2.getText().toString()));
                intent.putExtra("btn", Integer.valueOf(editText3.getText().toString()));
                intent.putExtra("time_next", Integer.valueOf(timenext.getText().toString()));

                startService(intent);
                finish();
            }
        });


    }

    private void addControl() {
        editText1 = findViewById(R.id.etxt_time);
        editText2 = findViewById(R.id.etxt_time_after);
        editText3 = findViewById(R.id.btnshow);
        timenext = findViewById(R.id.timenext);
        btn_time = findViewById(R.id.btn_time);
    }
}

