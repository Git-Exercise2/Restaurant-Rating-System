package com.demo.maintenance.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.demo.maintenance.R;
import com.demo.maintenance.adapter.CommentAdapter;
import com.demo.maintenance.bean.DiscussResponse;
import com.demo.maintenance.contant.Config;
import com.demo.maintenance.contant.Global;
import com.demo.maintenance.utils.HttpManager;
import com.demo.maintenance.utils.SystemBarTintManager;
import com.hjq.toast.ToastUtils;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.HashMap;

@SuppressLint("SetTextI18n")
public class CommentListActivity extends AppCompatActivity {

    private RecyclerView rvComments;
    private CommentAdapter commentAdapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QMUIStatusBarHelper.translucent(this);
        SystemBarTintManager.setStatusBarDarkTheme(this, true);
        setContentView(R.layout.activity_comment_list);

        findViewById(R.id.common_back_iv).setVisibility(View.VISIBLE);
        findViewById(R.id.common_back_iv).setOnClickListener(v -> finish());
        ((TextView) findViewById(R.id.common_content_tv)).setText("My Comments");

        rvComments = findViewById(R.id.recycler_view);

        rvComments.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter();
        commentAdapter.commentListActivity = this;
        rvComments.setAdapter(commentAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadComments();
    }

    private void loadComments() {
        HashMap<String, String> map = new HashMap<>();
        WaitDialog.show("Loading...");
        HttpManager.get(Config.IP + "/demo/discuss/queryAllById2?userId=" + Global.userId, new HttpManager.NetworkCallBack() {
            @Override
            public void onSuccess(String successInfo) {
                DiscussResponse baseResponse = JSON.parseObject(successInfo, DiscussResponse.class);
                if (baseResponse != null) {
                    if (baseResponse.code == 0) {
                        runOnUiThread(() -> commentAdapter.submitList(baseResponse.data));
                    } else {
                        runOnUiThread(() -> ToastUtils.show(baseResponse.msg));
                    }
                } else {
                    runOnUiThread(() -> ToastUtils.show("Load Comment Failed"));
                }
            }

            @Override
            public void onError(String errorInfo) {
                runOnUiThread(() -> ToastUtils.show(errorInfo));
            }

            @Override
            public void onFinal() {
                WaitDialog.dismiss();
            }
        });
    }
}
