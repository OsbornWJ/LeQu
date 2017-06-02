package com.xujie.lequ.app;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * @author wj
 * @date 2017/6/2
 * @discription  activity管理类
 */
public class ActivityManager {

    private static Stack<Activity> activityStack;

    private static ActivityManager instance;

    static {
        instance = new ActivityManager();
    }

    public static ActivityManager getInstance() {
        return instance;
    }

    /**添加指定activity到堆栈*/
    public void addActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**获取当前的activity*/
    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**结束当前的activity*/
    public void finishActivity(){
        Activity activity=activityStack.lastElement();
        finishActivity(activity);
    }

    /**结束指定的activity*/
    public void finishActivity(Activity activity){
        if (activity != null){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定Class的Activity
     */
    public void finishActivity(Class<?> cls){
        for (Activity activity : activityStack) {
            if(activity.getClass().equals(cls) ){
                finishActivity(activity);
                return;
            }
        }
    }

    /**
     * 结束全部的Activity
     */
    public void finishAllActivity(){
        for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            android.app.ActivityManager activityMgr= (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) { }
    }
}
