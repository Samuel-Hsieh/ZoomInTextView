package com.deerlight.simplezoomintextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.deerlight.zoomintextview.ZoomInTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frame = findViewById(R.id.frame);
        ZoomInTextView d_ztv = new ZoomInTextView.Builder(this)
                .Text(R.string.hello)
                .TextColorRes(android.R.color.holo_orange_dark)
                .TextSize(R.dimen.MyTextSize)
                .TextMaxSize(30f) //30sp
                .Duration(1000) //1s
                .build();
        frame.addView(d_ztv);

        ZoomInTextView ztv = findViewById(R.id.zoomIn_tv);
        ztv.setText(R.string.app_name);
        ztv.setTextSize(20f); //20sp
        ztv.setTextMaxSize(R.dimen.MyTextMaxSize);
        ztv.setTextColorRes(R.color.colorAccent, null);
        ztv.setDuration(500);
    }
}
