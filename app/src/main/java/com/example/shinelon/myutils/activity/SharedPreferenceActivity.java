package com.example.shinelon.myutils.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.shinelon.myutils.R;
import com.example.shinelon.myutils.bean.Resume;
import com.example.shinelon.myutils.utils.SharedPreferencesUtils;
import java.util.ArrayList;
/**
 * 关于测试SharedPreferencesUtils工具类的界面
 */
public class SharedPreferenceActivity extends AppCompatActivity {
    /**
     * 测试保存对象
     */
    public static final String RESUMEKEY = "resumekey";
    /**
     * 测试保存的布尔值
     */
    public static final String BOOLEANKEY = "booleanKey";
    /**
     * 测试保存的字符串
     */
    public static final String STRINGKEY = "stringkey";
    /**
     * 测试保存的整型
     */
    public static final String INTKEY = "intKey";

    /**
     * 测试保存的Long型
     */
    public static final String LONGKEY = "longkey";
    /**
     * 上下文
     */
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        /**
         * 关于测试SharedPreferencesUtils工具类
         */
        goTestSharedPreferencesUtils();
    }
    /**
     * 关于测试SharedPreferencesUtils工具类
     */
    private void goTestSharedPreferencesUtils() {
        /**
         *测试保存布尔值
         */
        SharedPreferencesUtils.saveBoolean(mContext, BOOLEANKEY, true);//保存为true
        Log.i("testBoolean", SharedPreferencesUtils.getBoolean(mContext, BOOLEANKEY, false) + "");//获取也为true
        /**
         *测试保存的字符串
         */
        SharedPreferencesUtils.saveString(mContext, STRINGKEY, "太阳");//保存为太阳
        Log.i("testString", SharedPreferencesUtils.getString(mContext, STRINGKEY, "") + "");//获取也为太阳
        /**
         *测试保存的整型
         */
        SharedPreferencesUtils.saveInt(mContext, INTKEY, 10);//保存为10
        Log.i("testInt", SharedPreferencesUtils.getInt(mContext, INTKEY, 0) + "");//获取也为10
        /**
         *测试保存的Long
         */
        SharedPreferencesUtils.saveLong(mContext, LONGKEY, 10000L);//保存为10000
        Log.i("testLong", SharedPreferencesUtils.getLong(mContext, LONGKEY, 0L) + "");//获取也为10000
        /**
         *测试本地保存对象
         */
        savaData(createObject());
        readData();
    }
    /**
     * 创建一个测试对象
     * @return
     */
    private Resume createObject() {
        Resume resume = new Resume();
        resume.setId(1);
        resume.setName("小鹏");
        resume.setCity("广州");
        Resume.MyFood myFood = new Resume.MyFood();
        myFood.setType("水果");
        ArrayList<Resume.MyFood.FoodListBean> myFoods = new ArrayList<>();
        Resume.MyFood.FoodListBean foodListBean = new Resume.MyFood.FoodListBean();
        foodListBean.setFoodImg("图片");
        foodListBean.setFoodName("苹果");
        myFood.setFoodListBeen(myFoods);
        resume.setMyFood(myFood);
        return resume;
    }
    /**
     * 保存测试对象
     *
     * @param resume
     */
    private void savaData(Resume resume) {
        SharedPreferencesUtils.saveObject(mContext, RESUMEKEY, resume);
    }
    /**
     * 读取保存后的测试对象
     */
    private void readData() {
        Resume resume = (Resume) SharedPreferencesUtils.getObject(mContext, RESUMEKEY);
        Log.i("resume", resume.getId() + "/" + resume.getCity() + "/" + resume.getMyFood().getType());
    }
}
