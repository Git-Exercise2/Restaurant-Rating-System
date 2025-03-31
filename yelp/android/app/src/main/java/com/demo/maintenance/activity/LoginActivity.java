package com.demo.maintenance.activity;

import static com.demo.maintenance.contant.Config.IP;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.demo.maintenance.R;
import com.demo.maintenance.bean.UserEntity;
import com.demo.maintenance.contant.Global;
import com.demo.maintenance.utils.AntiShakeUtils;
import com.demo.maintenance.utils.HttpManager;
import com.demo.maintenance.utils.SystemBarTintManager;
import com.hjq.toast.ToastUtils;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录页
 */
public class LoginActivity extends AppCompatActivity {

    private EditText et_username;

    private EditText et_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QMUIStatusBarHelper.translucent(this);
        SystemBarTintManager.setStatusBarDarkTheme(this, true);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        // 登录按钮
        findViewById(R.id.btn_login).setOnClickListener(v -> {
            if (AntiShakeUtils.isInvalidClick(v)) {
                return;
            }
            if (TextUtils.isEmpty(et_username.getText().toString())) {
                runOnUiThread(() -> ToastUtils.show("Please Input Account"));
                return;
            }
            if (TextUtils.isEmpty(et_password.getText().toString())) {
                runOnUiThread(() -> ToastUtils.show("Please Input Password"));
                return;
            }
            login();
        });

        findViewById(R.id.tv_register).setOnClickListener(v -> {
            if (AntiShakeUtils.isInvalidClick(v)) {
                return;
            }
            startActivity(new Intent(this, RegisterActivity.class));
        });

    }

    private void login() {

        WaitDialog.show("LOGIN...");

        String userName = et_username.getText().toString();
        String password = et_password.getText().toString();

        Map<String, String> param = new HashMap<>();
        param.put("username", userName);
        param.put("password", password);

        HttpManager.postJson(IP + "/loginInner", param, new HttpManager.NetworkCallBack() {
            @Override
            public void onSuccess(String successInfo) {
                try {
                    UserEntity userResponse = JSON.parseObject(successInfo, UserEntity.class);
                    if (userResponse != null && userResponse.code == 0 && userResponse.data != null) {
                        runOnUiThread(() -> {
                            Global.username = userResponse.data.username;
                            Global.userId = userResponse.data.id;
                            Global.phone = userResponse.data.mobile;
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        });
                    } else {
                        runOnUiThread(() -> ToastUtils.show("Account or password error"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() -> ToastUtils.show("Login Failed"));
                }
            }

            @Override
            public void onError(String errorInfo) {
                ToastUtils.show("Login Failed");
            }

            @Override
            public void onFinal() {
                WaitDialog.dismiss();
            }
        });

    }

    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        return super.moveTaskToBack(true);
    }
}
