package com.demo.maintenance.utils;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjq.toast.ToastUtils;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Stack;

/**
 * Activity 管理器
 *
 * @author 56392
 */

public class ActivityManager {

    private static Application sApplication;

    public static Stack<WeakReference<Activity>> mActivityStack;

    private static long mExitTime;

    public final static String MAIN_ACTIVITY = "com.demo.maintenance.activity.MainActivity";

    private static Application.ActivityLifecycleCallbacks mCallbacks = new Application.ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (activity == null){
                return;
            }
            try{
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }catch (Exception e){
                e.printStackTrace();
            }

            Iterator<WeakReference<Activity>> iterator = mActivityStack.iterator();
            WeakReference<Activity> temp;
            try {
                while (iterator.hasNext()) {
                    temp = iterator.next();
                    if (temp == null || temp.get() != activity) {
                        continue;
                    }
                    iterator.remove();
                }
                mActivityStack.push(new WeakReference<>(activity));
            }catch (Exception e){
                e.printStackTrace();
            }
            int count = 1;
            Log.e("ActivityManager", "添加 - " + activity.toString());
            Log.e("ActivityManager", "当前Activity堆栈顺序 --------------------");
            for (WeakReference<Activity> activityWeakReference : mActivityStack){
                if (activityWeakReference == null || activityWeakReference.get() == null){
                    continue;
                }
                Log.e("ActivityManager", "【" + count + "】： " + activityWeakReference.get().toString());
                count++;
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            if (activity == null){
                return;
            }
            Iterator<WeakReference<Activity>> iterator = mActivityStack.iterator();
            WeakReference<Activity> temp;
            try {
                while (iterator.hasNext()) {
                    temp = iterator.next();
                    if (temp == null || temp.get() != activity) {
                        continue;
                    }
                    iterator.remove();

                }
            }catch (Exception e){
                e.printStackTrace();
            }
            int count = 1;
            Log.e("ActivityManager", "移除 - " + activity.toString());
            Log.e("ActivityManager", "当前Activity堆栈顺序 --------------------");
            for (WeakReference<Activity> activityWeakReference : mActivityStack){
                if (activityWeakReference == null || activityWeakReference.get() == null){
                    continue;
                }
                Log.e("ActivityManager", "【" + count + "】： " + activityWeakReference.get().toString());
                count++;
            }
        }
    };

    private ActivityManager() {
        mActivityStack = new Stack<>();
    }

    /**
     * 初始化工具类
     * @param app 应用
     */
    public static void init(@NonNull final Application app) {
        mActivityStack = new Stack<>();
        ActivityManager.sApplication = app;
        app.registerActivityLifecycleCallbacks(mCallbacks);
    }

    /**
     * 获取Application
     * @return Application
     */
    public static Application getApp() {
        if (sApplication != null) {
            return sApplication;
        }
        throw new NullPointerException("u should init first");
    }

    public static @Nullable Activity getTopActivity(){
        if (mActivityStack.isEmpty()
                || mActivityStack.peek() == null){
            return null;
        }
        return mActivityStack.peek().get();
    }

    /**
     * 退出 app
     *
     * @param exit 除去其中的一个activity
     */
    public static void removeActivity(Activity exit) {
        for (WeakReference<Activity> activityWeakReference : mActivityStack) {
            if (activityWeakReference == null
                    || activityWeakReference.get() == null){
                continue;
            }
            if (exit != activityWeakReference.get()) {
                activityWeakReference.get().finish();
            }
        }
    }

    public static void removeActivity(@NonNull Class activityCls) {
        for (WeakReference<Activity> activityWeakReference : mActivityStack) {
            if (activityWeakReference == null
                    || activityWeakReference.get() == null){
                continue;
            }
            if (activityCls.getName().equals(activityWeakReference.get().getClass().getName())) {
                activityWeakReference.get().finish();
            }
        }
    }

    /**
     * 退出整个应用
     */
    public static void exitSystem(){
        try {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.show("再按一次退出应用");
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 立即结束应用，在鸿蒙系统上并不能真正的关闭应用
     */
    public static void exitSystemNow(){
        try{
            System.exit(0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Activity 物理返回键被点击
     *
     * 1. 如果存在 TestActivity，回到 TestActivity 页面，也就是触发 finish
     * 2. 如果不存在 TestActivity，栈里面也只有它一个，直接退出应用
     *
     * @param activity /
     */
    public static void activityBackPressed(Activity activity) {
        Log.e("ActivityManager", "触发 【activityBackPressed】是 " + activity.toString());
        Activity tempActivity = null;
        boolean isActivityInStack = false;
        boolean isActivityIsMainActivity = false;
        boolean isMainActivityExist = false;
        for (WeakReference<Activity> activityWeakReference : mActivityStack) {
            if (activityWeakReference == null
                    || activityWeakReference.get() == null){
                continue;
            }
            tempActivity = activityWeakReference.get();
            // 当前 Activity 是否在栈里面
            if (tempActivity == activity){
                isActivityInStack = true;
            }
            // 如果存在 TestActivity，都是 finish
            if (tempActivity.toString().contains(MAIN_ACTIVITY)){
                isMainActivityExist = true;
            }
            // 如果当前的 Activity 就是 TestActivity
            if (activity.toString().contains(MAIN_ACTIVITY)){
                isActivityIsMainActivity = true;
                break;
            }
        }
        // 如果当前是 TestActivity，直接退出
        if (isActivityIsMainActivity){
            exitSystem();
            return;
        }
        // 如果当前 Activity 不在栈里面
        if (!isActivityInStack){
            exitSystemNow();
            return;
        }
        // 当前 Activity 不是 TestActivity
        if (!isMainActivityExist){
            if (mActivityStack.size() <= 1){
                exitSystem();
            }else {
                activity.finish();
            }
        }else {
            activity.finish();
        }
    }

}
