package com.example.shinelon.myutils.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.example.shinelon.myutils.R;
import com.example.shinelon.myutils.utils.TvUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 关于TextView相关的工具类
 */
public class TextViewUtilsActivity extends AppCompatActivity {
    @InjectView(R.id.tv_test)
    TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_utils);
        ButterKnife.inject(this);
//        tvTest.setText(TvUtils.stringToSpannable(passWd, 32, R.color.colorBlue));
        /**
         *直接调用
         */
        SpannableStringBuilder sp = TvUtils.stringToSpannable("我是")
                .append(TvUtils.stringToSpannableColor("太阳大神", 0xffff0000)
                        .append(TvUtils.stringToSpannable("玩韩信贼6", 66, 0xff2fb9c3))
                );
        tvTest.setText(sp);
        /**
         * 再次封装后调用
         */
        TvUtils.create()
                .addSsb("我是")
                .addSsbColor("太阳大神", 0xffff0000)
                .addSsb("玩韩信贼6", 64, 0xff2fb9c3)
                .showIn(tvTest);


    }
}
