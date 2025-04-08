package com.demo.maintenance;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.demo.maintenance.database.DaoMaster;
import com.demo.maintenance.database.DaoSession;
import com.demo.maintenance.utils.ActivityManager;
import com.hjq.toast.ToastUtils;
import com.kongzue.dialogx.DialogX;

public class MyApplication extends Application {

    public static MyApplication application;

    public static DaoSession mSession;

    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        ActivityManager.init(this);
        ToastUtils.init(this);

        DialogX.init(this);

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "tm.db");
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mSession = daoMaster.newSession();
    }

}
