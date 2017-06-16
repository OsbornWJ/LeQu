package com.xujie.lequ.app;

import android.app.Application;
import android.util.Log;

import com.xujie.lequ.BuildConfig;
import com.xujie.lequ.app.exception.LocalFileHandler;
import com.xujie.lequ.util.LogUtil;
import com.xujie.lequ.util.ToastUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author wj
 * @date 2017/6/2
 * @discription
 */
public class LeQuApp extends Application {

    private static LeQuApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        //配置是否显示log
        LogUtil.isDebug = true;

        //配置时候显示toast
        ToastUtil.isShow = true;

        //配置程序异常退出处理
        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));
    }

    public static OkHttpClient defaultOkHttpClient() {
        /**
         * 简易配置 retorfit
         */
        /*OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();*/

        OkHttpClient.Builder httpClientBuider = new OkHttpClient.Builder();
        /**retorfit 配置https*/
        httpClientBuider.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        /**retorfit 配置拦截器
         * 1.配置retorfit 请求超时时间
         * 2.显示retorfit 请求url及参数
         * */
        if (httpClientBuider.interceptors() != null){
            httpClientBuider.interceptors().clear();
        }
        httpClientBuider.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                String path = request.url().encodedPath();
                Log.d("AppClient", path);
                String query = request.url().query();
                if (BuildConfig.DEBUG){
                    Log.d("AppClient", query + ">>>query");
                }
                Map<String, Object> paramsMap = null;
                if (query != null){
                    paramsMap = new HashMap<>(0);
                    String queryEntries[] = query.split("&");
                    for (int i = 0;  i < queryEntries.length; i ++){
                        paramsMap.put(queryEntries[i].split("=")[0],
                                queryEntries[i].split("=")[1]);
                    }
                }
                if (BuildConfig.DEBUG){
                    Log.d("AppClient", paramsMap + ">>>paramsMap");
                }
                return chain.proceed(request);
            }
        }).connectTimeout(6, TimeUnit.SECONDS)
        .writeTimeout(6, TimeUnit.SECONDS)
        .readTimeout(6, TimeUnit.SECONDS);

        return httpClientBuider.build();
    }

    public static LeQuApp getInstance() {
        return mInstance;
    }
}
