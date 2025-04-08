package com.demo.maintenance.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class PermissionUtils {

    public static void checkPermission(String[] permission, int resultCode, Runnable runnable) {
        if (ActivityManager.getTopActivity() == null){
            return;
        }
        if (ActivityCompat.checkSelfPermission(ActivityManager.getTopActivity(), permission[0])
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ActivityManager.getTopActivity(), permission[0])) {
                Toast.makeText(ActivityManager.getTopActivity(), "请授予相关权限", Toast.LENGTH_SHORT).show();
            }else {
                ActivityCompat.requestPermissions(ActivityManager.getTopActivity(), permission, resultCode);
            }
        } else {
            runnable.run();
        }
    }

}
