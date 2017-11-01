package com.example.shinelon.myutils.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shinelon.myutils.R;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 上下文
     */
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

    }

}
