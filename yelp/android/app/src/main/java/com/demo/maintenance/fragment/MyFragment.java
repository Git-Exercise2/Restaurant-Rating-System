package com.demo.maintenance.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.maintenance.R;
import com.demo.maintenance.activity.CommentListActivity;
import com.demo.maintenance.activity.LoginActivity;
import com.demo.maintenance.activity.RestaurantListActivity;
import com.demo.maintenance.contant.Global;
import com.demo.maintenance.utils.ActivityManager;
import com.demo.maintenance.utils.AntiShakeUtils;
import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.interfaces.OnDialogButtonClickListener;

/**
 * 我的
 */
public class MyFragment extends BaseFragment {

    private TextView user_name;
    private TextView user_role;
    private Button btn_login;

    private RelativeLayout container_2;
    private RelativeLayout container_3;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(ActivityManager.getTopActivity()).inflate(R.layout.fragment_mine, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        user_name = view.findViewById(R.id.user_name);
        user_role = view.findViewById(R.id.user_role);

        user_name.setText(Global.username);
        user_role.setText(Global.phone);

        view.findViewById(R.id.btn_login).setOnClickListener(v -> {
            if (AntiShakeUtils.isInvalidClick(v)){
                return;
            }
            MessageDialog.show("Tips", "Confirm Log Out?", "OK", "Cancel")
                    .setOkButton((OnDialogButtonClickListener) (baseDialog, v12) -> {
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                        return true;
                    })
                    .setCancelButtonClickListener((dialog, v1) -> false);

        });

        view.findViewById(R.id.container_2).setOnClickListener(v -> {
            if (AntiShakeUtils.isInvalidClick(v)){
                return;
            }
            startActivity(new Intent(getActivity(), RestaurantListActivity.class));
        });

        view.findViewById(R.id.container_3).setOnClickListener(v -> {
            if (AntiShakeUtils.isInvalidClick(v)){
                return;
            }
            startActivity(new Intent(getActivity(), CommentListActivity.class));
        });
    }

}
