package com.example.shinelon.myutils.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Peng on 2017/10/25.
 */

public class SharedPreferencesUtils {
    private static final String TAG = SharedPreferencesUtils.class.getName();
    /**
     * 保存在手机本地的文件
     */
    private static final String FILE_NAME = "file_data";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SharedPreferencesUtils(Context mContext, String preferenceName) {
        preferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 清空数据
     *
     * @param context 上下文
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    /**
     * 保存boolean变量
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value).apply();
        editor.commit();
    }

    /**
     * 获取boolean变量的值
     *
     * @param context
     * @param key
     * @param defValue 获取不到时，给定的默认的值
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }


    /**
     * 保存字符串变量
     */
    public static void saveString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    /**
     * 获取字符串的值
     *
     * @param context
     * @param key
     * @param defValue 获取不到时，给定的默认的值
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }


    /**
     * 保存整型变量
     */
    public static void saveInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 获取整型变量的值
     *
     * @param context
     * @param key
     * @param defValue 获取不到时，给定的默认的值
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    /**
     * @param context
     * @param key
     * @param value   保存的值
     */
    public static void saveLong(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putLong(key, value).commit();
    }

    /**
     * @param context
     * @param key
     * @param value   获取不到后的默认值
     * @return
     */
    public static long getLong(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, value);
    }


    /*****************************************保存对象相关******************************************/
    /**
     * 保存序列化对象到本地（使用时记得在bean类中实现Serializable接口）
     *
     * @param context
     * @param key
     * @param object
     */
    public static void saveObject(Context context, String key, Object object) {
        SharedPreferences.Editor spEdit = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        //先将序列化结果写到byte缓存中，其实就分配一个内存空间
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(bos);
            os.writeObject(object);//将对象序列化写入byte缓存
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将序列化的数据转为16进制保存
        String bytesToHexString = bytesToHexString(bos.toByteArray());
        //保存该16进制数组
        spEdit.putString(key, bytesToHexString);
        spEdit.commit();
    }

    /**
     * desc:将数组转为16进制
     *
     * @param bArray
     * @return
     */
    public static String bytesToHexString(byte[] bArray) {
        if (bArray == null) {
            return null;
        }
        if (bArray.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 从本地反序列化获取对象
     *
     * @param context
     * @param key
     * @return
     */
    public static Object getObject(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (sp.contains(key)) {
            String string = sp.getString(key, "");
            if (TextUtils.isEmpty(string)) {
                return null;
            } else {
                //将16进制的数据转为数组，准备反序列化
                byte[] stringToBytes = StringToBytes(string);
                ByteArrayInputStream bis = new ByteArrayInputStream(stringToBytes);
                ObjectInputStream is = null;
                //返回反序列化得到的对象
                Object readObject = null;
                try {
                    is = new ObjectInputStream(bis);
                    readObject = is.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return readObject;
            }
        }
        return null;
    }

    /**
     * desc:将16进制的数据转为数组
     *
     * @param data
     * @return
     */
    public static byte[] StringToBytes(String data) {
        String hexString = data.toUpperCase().trim();
        if (hexString.length() % 2 != 0) {
            return null;
        }
        byte[] retData = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i++) {
            int int_ch;  // 两位16进制数转化后的10进制数
            char hex_char1 = hexString.charAt(i); //两位16进制数中的第一位(高位*16)
            int int_ch1;
            if (hex_char1 >= '0' && hex_char1 <= '9')
                int_ch1 = (hex_char1 - 48) * 16;   // 0 的Ascll - 48
            else if (hex_char1 >= 'A' && hex_char1 <= 'F')
                int_ch1 = (hex_char1 - 55) * 16; // A 的Ascll - 65
            else
                return null;
            i++;
            char hex_char2 = hexString.charAt(i); //两位16进制数中的第二位(低位)
            int int_ch2;
            if (hex_char2 >= '0' && hex_char2 <= '9')
                int_ch2 = (hex_char2 - 48); // 0 的Ascll - 48
            else if (hex_char2 >= 'A' && hex_char2 <= 'F')
                int_ch2 = hex_char2 - 55; // A 的Ascll - 65
            else
                return null;
            int_ch = int_ch1 + int_ch2;
            retData[i / 2] = (byte) int_ch;//将转化后的数放入Byte里
        }
        return retData;
    }

    /*****************************************保存对象相关******************************************/

}
