package com.example.zhang.thinmusic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.annotation.Nullable;

import com.example.zhang.thinmusic.R;

/**
 * SharePreference工具类
 * Created by zhang on 2018/3/19.
 */

public class Preferences {
    private static final String PLAY_POSITION = "play_position";
    private static final String PlAY_MODE = "play_mode";
    private static final String PLAY_FAVOUR = "play_favour";

    private static Context sContext;

    public static void init(Context context){sContext = context.getApplicationContext();}

    public static int  getPlayPosition(){return getInt(PLAY_POSITION, 0);}

    public static void  savePlayPosition(int position){saveInt(PLAY_POSITION,position);}

    public static int getPlayMode(){return getInt(PlAY_MODE,0);}

    public static void savePlayMode(int mode){saveInt(PlAY_MODE,mode);}



    public static String getFilterSize(){
        return getString(sContext.getString(R.string.setting_key_filter_size),"0");
    }

    public static void saveFilterSize(String value){
        saveString(sContext.getString(R.string.setting_key_filter_size),value);
    }

    public static String getFilterTime(){
        return getString(sContext.getString(R.string.setting_key_filter_time),"0");
    }

    public static void saveFilterTime(String value){
        saveString(sContext.getString(R.string.setting_key_filter_time),value);
    }
    public  static  boolean getBoolean(String key, boolean defValue){
        return getPreferences().getBoolean(key,defValue);
    }

    private static void saveBoolean(String key, boolean value){
        getPreferences().edit().putBoolean(key,value).apply();
    }

    private static int getInt(String key,int defValue){
        return getPreferences().getInt(key,defValue);
    }

    private static void  saveInt(String key,int value){
        getPreferences().edit().putInt(key,value).apply();
    }

    private  static long getLong(String key,long defValue){
        return getPreferences().getLong(key, defValue);
    }

    private  static void saveLong(String key,long value){
        getPreferences().edit().putLong(key,value).apply();
    }

    private static String getString(String key, @Nullable String defValue){
        return getPreferences().getString(key,defValue);
    }

    private static  void saveString(String key,@Nullable String value){
        getPreferences().edit().putString(key,value).apply();
    }

    private static SharedPreferences getPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(sContext);
    }
}
