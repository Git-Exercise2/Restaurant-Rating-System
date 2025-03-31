package com.demo.maintenance.fragment;

import static com.demo.maintenance.contant.Config.IP;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.demo.maintenance.R;
import com.demo.maintenance.activity.RestaurantDetailActivity;
import com.demo.maintenance.bean.RestaurantBean;
import com.demo.maintenance.bean.StarResponse;
import com.demo.maintenance.contant.Config;
import com.demo.maintenance.contant.Global;
import com.demo.maintenance.utils.AntiShakeUtils;
import com.demo.maintenance.utils.HttpManager;
import com.hjq.toast.ToastUtils;
import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.dialogs.WaitDialog;

import java.util.ArrayList;
import java.util.List;

public class RestaurantBase {

    public  RecyclerView recycler_view;

    public RestaurantBase.ListAdapter listAdapter;

    public List<RestaurantBean.DataDTO> datasource = new ArrayList<>();

    public Activity activity;

    // 1 from fragment, 2 from activity
    public int from;

    public void initView(View view, Activity activity, int from) {

        this.activity = activity;
        this.from = from;

        listAdapter = new RestaurantBase.ListAdapter();
        recycler_view = view.findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(activity));
        recycler_view.setAdapter(listAdapter);
    }

    public void reloadData(){
        reloadData(null);
    }

    public void reloadData(String key){

        WaitDialog.show("Loading Restaurant...");

        String url = IP + "/demo/restaurant/all";
        if (this.from == 2){
            url += "?userId=" + Global.userId;
        }
        if (this.from == 3){
            url += "?key=" + key;
        }

        HttpManager.get(url, new HttpManager.NetworkCallBack() {
            @Override
            public void onSuccess(String successInfo) {
                try {
                    RestaurantBean restaurantBean = JSON.parseObject(successInfo, RestaurantBean.class);
                    if (restaurantBean != null) {
                        if (restaurantBean.code == 0) {
                            activity.runOnUiThread(() -> {
                                datasource.clear();
                                if (restaurantBean.data != null && !restaurantBean.data.isEmpty()) {
                                    datasource.addAll(restaurantBean.data);
                                }
                                listAdapter.notifyDataSetChanged();
                            });
                        }else {
                            ToastUtils.show(restaurantBean.msg);
                        }
                    } else {
                        ToastUtils.show("Request Failed");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.show("Request Failed");
                }
            }

            @Override
            public void onError(String errorInfo) {
                ToastUtils.show("Request Failed");
            }

            @Override
            public void onFinal() {
                WaitDialog.dismiss();
            }
        });

    }

    private void removeStar(RestaurantBean.DataDTO restaurantBean) {
        if (Global.userId == null) {
            return;
        }
        String url = Config.IP + "/demo/restaurant/removeStar?userId=" + Global.userId + "&restaurantId=" + restaurantBean.id;
        HttpManager.get(url, new HttpManager.NetworkCallBack() {
            @Override
            public void onSuccess(String successInfo) {
                try {
                    StarResponse baseResponse = JSON.parseObject(successInfo, StarResponse.class);
                    if (baseResponse != null && baseResponse.code == 0) {
                        activity.runOnUiThread(() -> {
                            ToastUtils.show("Remove Success");
                        });
                        reloadData();
                    }else {
                        activity.runOnUiThread(() -> {
                            ToastUtils.show("Remove Failed");
                        });
                    }
                } catch (Exception e) {
                    activity.runOnUiThread(() -> {
                        ToastUtils.show("Remove Failed");
                    });
                }
            }
        });
    }

    public class ListAdapter extends RecyclerView.Adapter<RestaurantBase.ViewHolder> {

        @NonNull
        @Override
        public RestaurantBase.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RestaurantBase.ViewHolder(LayoutInflater.from(activity)
                    .inflate(R.layout.recycler_item_home, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RestaurantBase.ViewHolder holder, int position) {

            // 将 ViewHolder 绑定数据
            RestaurantBean.DataDTO dataDTO = datasource.get(position);
            holder.tv_name.setText(dataDTO.name);
            holder.tv_address.setText(dataDTO.address);
            holder.rating_bar.setEnabled(false);
            holder.rating_bar.setRating(Float.parseFloat(dataDTO.rating));
            holder.tv_review_count.setText(dataDTO.reviewCount + " Reviews");
            holder.tv_price.setText(dataDTO.priceLevel);
            holder.tv_category.setText(dataDTO.categories);
            Glide.with(activity).load(dataDTO.imageUrl).thumbnail(0.3f).into(holder.iv_restaurant);

            holder.btn_call.setOnClickListener(v -> {
                if (AntiShakeUtils.isInvalidClick(v)){
                    return;
                }
                MessageDialog.show("Call", "Call " + datasource.get(position).phone + "?", "Call", "Cancel")
                        .setOkButton((dialog, v1) -> {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + datasource.get(position).phone));
                            activity.startActivity(intent);
                            return true;
                        });
            });
            holder.itemView.setOnClickListener(v -> {
                if (AntiShakeUtils.isInvalidClick(v)){
                    return;
                }
                Global.restaurantDetailBean = dataDTO;
                Intent intent = new Intent(activity, RestaurantDetailActivity.class);
                activity.startActivity(intent);
            });
            holder.itemView.setOnLongClickListener(v -> {
                if (from == 2) {
                    MessageDialog.show("Warning","Confirm Remove This Restaurant ?", "OK", "Cancel")
                            .setOkButton((dialog, v1) -> {
                                removeStar(dataDTO);
                                return false;
                            });
                }
                return true;
            });
        }

        @Override
        public int getItemCount() {
            return datasource.size();
        }
    }

    // 根据 R.layout.recycler_item_home 生成 ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_restaurant;
        private TextView tv_name;
        private RatingBar rating_bar;
        private TextView tv_review_count;
        private TextView tv_address;
        private TextView tv_category;
        private TextView tv_price;
        private ImageButton btn_call;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_restaurant = itemView.findViewById(R.id.iv_restaurant);
            tv_name = itemView.findViewById(R.id.tv_name);
            rating_bar = itemView.findViewById(R.id.rating_bar);
            tv_review_count = itemView.findViewById(R.id.tv_review_count);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_category = itemView.findViewById(R.id.tv_category);
            tv_price = itemView.findViewById(R.id.tv_price);
            btn_call = itemView.findViewById(R.id.btn_call);
        }
    }

}
