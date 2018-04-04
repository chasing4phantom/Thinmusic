package com.example.zhang.thinmusic.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 * Created by zhang on 2018/3/19.
 */

public class ToastUtils {
    private static Context sContext;
    private static Toast sToast;

    public static void init(Context context) {sContext = context.getApplicationContext();}

    public static void show(int resId){show(sContext.getString(resId));}

    public static void show(String text){
        if(sToast == null){
            sToast = Toast.makeText(sContext,text,Toast.LENGTH_SHORT);
        }else {
            sToast.setText(text);
        }
        sToast.show();
    }
}
