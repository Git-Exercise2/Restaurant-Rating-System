package com.demo.maintenance.activity;

import static com.demo.maintenance.contant.Config.IP;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.demo.maintenance.R;
import com.demo.maintenance.bean.UserResponse;
import com.demo.maintenance.utils.HttpManager;
import com.demo.maintenance.utils.SystemBarTintManager;
import com.hjq.toast.ToastUtils;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText account_register_name;
    private EditText account_register_password;
    private EditText account_register_confirm;
    private TextView account_register_start;
    private EditText et_nick_name;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QMUIStatusBarHelper.translucent(this);
        SystemBarTintManager.setStatusBarDarkTheme(this, true);
        setContentView(R.layout.activity_login_register);

        findViewById(R.id.common_back_iv).setVisibility(View.VISIBLE);
        findViewById(R.id.common_back_iv).setOnClickListener(v -> finish());
        ((TextView) findViewById(R.id.common_content_tv)).setText("Register");

        account_register_name = findViewById(R.id.et_id);
        account_register_password = findViewById(R.id.et_name);
        account_register_confirm = findViewById(R.id.account_register_confirm);
        account_register_start = findViewById(R.id.account_register_start);
        et_nick_name = findViewById(R.id.et_nick_name);

        account_register_start.setOnClickListener(v -> {
            if (account_register_name.getText() == null){
                runOnUiThread(() -> ToastUtils.show("Please Input Account"));
                return;
            }
            if (et_nick_name.getText() == null){
                runOnUiThread(() -> ToastUtils.show("Please Input NickName"));
                return;
            }
            if (account_register_password.getText() == null){
                runOnUiThread(() -> ToastUtils.show("Please Input Password"));
                return;
            }
            if (account_register_confirm.getText() == null){
                runOnUiThread(() -> ToastUtils.show("Please Confirm Password"));
                return;
            }
            if (!TextUtils.equals(account_register_password.getText().toString(),
                    account_register_confirm.getText().toString())){
                ToastUtils.show("The two passwords are different");
                return;
            }

            WaitDialog.show("Register...");

            String userName = account_register_name.getText().toString();
            String password = account_register_password.getText().toString();
            String nickName = et_nick_name.getText().toString();

            HttpManager.get(IP + "/registerInner?username=" + userName + "&password=" + password + "&realName=" + nickName,
                    new HttpManager.NetworkCallBack() {
                @Override
                public void onSuccess(String successInfo) {
                   try{
                       UserResponse userResponse = JSON.parseObject(successInfo, UserResponse.class);
                       if (userResponse != null && userResponse.code == 0){
                           runOnUiThread(() -> {
                               runOnUiThread(() -> ToastUtils.show("Register Success"));
                               finish();
                           });
                       }else {
                           runOnUiThread(() -> ToastUtils.show("Register Failed !"));
                       }
                   }catch (Exception e){
                       e.printStackTrace();
                       runOnUiThread(() -> ToastUtils.show("Register Failed !"));
                   }
                }

                @Override
                public void onError(String errorInfo) {
                    ToastUtils.show("Register Failed !");
                }

                @Override
                public void onFinal() {
                    WaitDialog.dismiss();
                }
            });

        });

    }

}
