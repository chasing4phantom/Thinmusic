package com.example.zhang.thinmusic.http;

/**回调抽象类
 * Created by zhang on 2018/4/17.
 */

public abstract class HttpCallback<T> {
    public abstract void onSuccess(T t);

    public abstract void onFail(Exception e);

    public void onFinish(){}
}
