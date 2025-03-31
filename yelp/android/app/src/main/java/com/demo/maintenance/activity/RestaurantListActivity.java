package com.demo.maintenance.activity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.maintenance.MyApplication;
import com.demo.maintenance.R;
import com.demo.maintenance.bean.PdfBean;
import com.demo.maintenance.contant.Global;
import com.demo.maintenance.database.PdfBeanDao;
import com.demo.maintenance.fragment.RestaurantBase;
import com.demo.maintenance.utils.AntiShakeUtils;
import com.demo.maintenance.utils.GetFilePathFromUri;
import com.demo.maintenance.utils.PermissionUtils;
import com.demo.maintenance.utils.SystemBarTintManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hjq.toast.ToastUtils;
import com.kongzue.dialogx.dialogs.MessageDialog;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {

    private RestaurantBase restaurantBase;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QMUIStatusBarHelper.translucent(this);
        SystemBarTintManager.setStatusBarDarkTheme(this, true);
        setContentView(R.layout.activity_restaurant_list);

        findViewById(R.id.common_back_iv).setVisibility(View.VISIBLE);
        findViewById(R.id.common_back_iv).setOnClickListener(v -> finish());
        ((TextView) findViewById(R.id.common_content_tv)).setText("My Favourite");

        restaurantBase = new RestaurantBase();
        restaurantBase.initView(findViewById(android.R.id.content), this, 2);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (restaurantBase != null){
            restaurantBase.reloadData();
        }
    }
}
