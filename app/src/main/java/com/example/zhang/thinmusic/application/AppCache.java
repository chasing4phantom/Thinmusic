package com.example.zhang.thinmusic.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.zhang.thinmusic.model.ListInfo;
import com.example.zhang.thinmusic.model.Music;
import com.example.zhang.thinmusic.model.NeteaseListInfo;
import com.example.zhang.thinmusic.storage.DBManager;
import com.example.zhang.thinmusic.utils.CoverLoader;
import com.example.zhang.thinmusic.utils.Preferences;
import com.example.zhang.thinmusic.utils.ScreenUtils;
import com.example.zhang.thinmusic.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2018/3/25.
 */

public class AppCache {
    public Context context;
    private final List<Music> LocalList = new ArrayList<>();
    private final List<ListInfo> Songlist = new ArrayList<>();
    private final List<NeteaseListInfo> NeteaseSongList = new ArrayList<>();
    private final List<List<NeteaseListInfo>>   NeteaseSongLists = new ArrayList<>();
    private final List<Activity> ActivityStack = new ArrayList<>();

    private AppCache(){

    }
    private static class SingletonHolder{
        private static AppCache instance = new AppCache();
    }

    public static AppCache get(){return SingletonHolder.instance;}

    public void init(Application application) {
        context = application.getApplicationContext();
        ToastUtils.init(context);
        Preferences.init(context);
        ScreenUtils.init(context);
        CoverLoader.get().init(context);
        application.registerActivityLifecycleCallbacks(new ActivityLifecycle());
    }
    public Context getContext(){return  context;}

    public List<Music> getLocalMusicList(){
        if(LocalList ==null){
        List<Music> musicList = DBManager.get().getMusicDao().loadAll();
        for(Music music:musicList)
        LocalList.add(music);}

        return LocalList;}

    public List<ListInfo> getSongList(){return Songlist;}

    public List<NeteaseListInfo>  getNeteaseSongList(){return NeteaseSongList;}

    public List<List<NeteaseListInfo>> getNeteaseSongLists(){return NeteaseSongLists;}

    public void clearStack(){
        List<Activity> activityStack = ActivityStack;
        for(int i = activityStack.size()-1;i>=0;i--){
            Activity activity = activityStack.get(i);
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activityStack.clear();
    }

    private class ActivityLifecycle implements Application.ActivityLifecycleCallbacks{
        private static final String TAG="Activity";
        @Override
        public void onActivityCreated(Activity activity, Bundle saveInstanceState){
            Log.i(TAG, "onAcitvityCreated: "+activity.getClass().getSimpleName());
            ActivityStack.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity){}

        @Override
        public void onActivityResumed(Activity activity){}

        @Override
        public void onActivityPaused(Activity activity){}

        @Override
        public void onActivityStopped(Activity activity){}

        @Override
        public void onActivitySaveInstanceState(Activity activity,Bundle outState){}

        @Override
        public void onActivityDestroyed(Activity activity){
            Log.i(TAG, "onActivityDestroyed: "+activity.getClass().getSimpleName());
            ActivityStack.remove(activity);
        }
    }
}
