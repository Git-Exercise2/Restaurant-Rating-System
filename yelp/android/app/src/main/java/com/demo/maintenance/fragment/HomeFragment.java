package com.demo.maintenance.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.maintenance.R;
import com.demo.maintenance.activity.RestaurantSearchActivity;
import com.demo.maintenance.utils.ActivityManager;
import com.demo.maintenance.utils.AntiShakeUtils;

public class HomeFragment extends BaseFragment {

    private RestaurantBase restaurantBase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(ActivityManager.getTopActivity()).inflate(R.layout.fragment_home, null);

        view.findViewById(R.id.common_back_iv).setVisibility(View.GONE);
        ((TextView)view.findViewById(R.id.common_content_tv)).setText("Restaurants");

        restaurantBase = new RestaurantBase();
        restaurantBase.initView(view, getActivity(), 1);
        restaurantBase.reloadData();

        if (view.findViewById(R.id.et_search) != null) {
            view.findViewById(R.id.et_search).setOnClickListener(v -> {
                if (AntiShakeUtils.isInvalidClick(v)) {
                    return;
                }
                getActivity().startActivity(new Intent(getActivity(), RestaurantSearchActivity.class));
            });
        }

        return view;
    }

}
