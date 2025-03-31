package com.demo.maintenance.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
import com.demo.maintenance.utils.SystemBarTintManager;
import com.hjq.toast.ToastUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RestaurantSearchActivity extends AppCompatActivity {

    private RestaurantBase restaurantBase;
    private EditText et_search;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QMUIStatusBarHelper.translucent(this);
        SystemBarTintManager.setStatusBarDarkTheme(this, true);
        setContentView(R.layout.activity_restaurant_search);

        findViewById(R.id.common_back_iv).setVisibility(View.VISIBLE);
        findViewById(R.id.common_back_iv).setOnClickListener(v -> finish());
        ((TextView) findViewById(R.id.common_content_tv)).setText("Search");

        restaurantBase = new RestaurantBase();
        restaurantBase.initView(findViewById(android.R.id.content), this, 3);

        et_search = findViewById(R.id.et_search);
        et_search.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {//搜索按键action
                restaurantBase.reloadData(et_search.getText().toString());
            }
            return false;
        });
    }

}
