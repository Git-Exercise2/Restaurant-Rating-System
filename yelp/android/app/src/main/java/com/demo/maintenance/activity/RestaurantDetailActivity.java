package com.demo.maintenance.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.demo.maintenance.R;
import com.demo.maintenance.adapter.CommentAdapter;
import com.demo.maintenance.bean.BaseResponse;
import com.demo.maintenance.bean.DiscussEntity;
import com.demo.maintenance.bean.DiscussResponse;
import com.demo.maintenance.bean.RestaurantBean;
import com.demo.maintenance.bean.StarResponse;
import com.demo.maintenance.bean.UserEntity;
import com.demo.maintenance.contant.Config;
import com.demo.maintenance.contant.Global;
import com.demo.maintenance.utils.AntiShakeUtils;
import com.demo.maintenance.utils.HttpManager;
import com.demo.maintenance.utils.SystemBarTintManager;
import com.hjq.toast.ToastUtils;
import com.kongzue.dialogx.dialogs.InputDialog;
import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.kongzue.dialogx.interfaces.BaseDialog;
import com.kongzue.dialogx.interfaces.OnBindView;
import com.kongzue.dialogx.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialogx.interfaces.OnInputDialogButtonClickListener;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RestaurantDetailActivity extends AppCompatActivity {

    private RecyclerView rvComments;
    private CommentAdapter commentAdapter;
    private RestaurantBean.DataDTO restaurantDetail;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QMUIStatusBarHelper.translucent(this);
        SystemBarTintManager.setStatusBarDarkTheme(this, true);
        setContentView(R.layout.activity_restaurant_detail);

        findViewById(R.id.common_back_iv).setVisibility(View.VISIBLE);
        findViewById(R.id.common_back_iv).setOnClickListener(v -> finish());
        ((TextView) findViewById(R.id.common_content_tv)).setText("Restaurant Detail");

        initViews();
        loadRestaurantData();
        setupListeners();
        loadComments();
        loadStar(-1);
    }

    private void loadStar(int clickType) {
        // 接口是/demo/star?userId=1&restaurantId=1，获取用户是否收藏了该餐厅
        if (Global.userId == null) {
            return;
        }
        String url = Config.IP + "/demo/restaurant/star?userId=" + Global.userId + "&restaurantId=" + restaurantDetail.id;
        if (clickType != -1) {
            url += "&clickType=" + (isFavorite ? 1 : 0);
        }
        HttpManager.get(url, new HttpManager.NetworkCallBack() {
            @Override
            public void onSuccess(String successInfo) {
                try {
                    StarResponse baseResponse = JSON.parseObject(successInfo, StarResponse.class);
                    if (baseResponse != null) {
                        if (baseResponse.code == 0) {
                            isFavorite = baseResponse.data == 1;
                            toggleFavorite();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initViews() {
        rvComments = findViewById(R.id.rv_comments);
        findViewById(R.id.btn_send).setOnClickListener(v -> postCommentWithRating(null));

        rvComments.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter();
        commentAdapter.restaurantDetailActivity = this;
        rvComments.setAdapter(commentAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void loadRestaurantData() {

        if (Global.restaurantDetailBean != null) {
            restaurantDetail = Global.restaurantDetailBean;

            // 更新 UI
            ((TextView) findViewById(R.id.tv_name)).setText(restaurantDetail.name);
            ((TextView) findViewById(R.id.tv_address)).setText(restaurantDetail.address);
            ((TextView) findViewById(R.id.tv_price_level)).setText(restaurantDetail.priceLevel);
            ((TextView) findViewById(R.id.tv_phone)).setText(restaurantDetail.phone);
            ((TextView) findViewById(R.id.tv_website)).setText(restaurantDetail.url);
            ((TextView) findViewById(R.id.tv_create_date)).setText("Created At " + restaurantDetail.createDate);
            try {
                ((RatingBar) findViewById(R.id.rating_bar)).setRating(Float.parseFloat(restaurantDetail.rating));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ((TextView) findViewById(R.id.tv_review_count)).setText("(" + restaurantDetail.reviewCount + " reviews)");

            // load image
            Glide.with(this).load(restaurantDetail.imageUrl).thumbnail(0.3f).into(((ImageView) findViewById(R.id.iv_restaurant)));
        }

    }

    private void loadComments() {
        if (restaurantDetail == null) {
            ToastUtils.show("Loading Comments Failed !");
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        WaitDialog.show("Loading...");
        HttpManager.get(Config.IP + "/demo/discuss/queryAllById?restaurantId=" + restaurantDetail.id, new HttpManager.NetworkCallBack() {
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
                    runOnUiThread(() -> ToastUtils.show("Comment Failed"));
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

    private void setupListeners() {
        // 收藏按钮点击
        findViewById(R.id.btn_favorite).setOnClickListener(v -> loadStar(isFavorite ? 1 : 0));
    }

    private void toggleFavorite() {
        runOnUiThread(() -> {
            ImageView btn = findViewById(R.id.btn_favorite);
            btn.setImageResource(isFavorite ? R.drawable.ic_favorite_border : R.drawable.ic_favorite);
        });
    }

    public void postComment(DiscussEntity discussEntity) {
        InputDialog.build().setTitle("Comment Dialog").setOkButton("OK", new OnInputDialogButtonClickListener<InputDialog>() {
            @Override
            public boolean onClick(InputDialog dialog, View v, String inputStr) {
                requestComment(inputStr, discussEntity, -1f);
                return false;
            }
        }).setCancelButton("CANCEL", new OnDialogButtonClickListener<MessageDialog>() {
            @Override
            public boolean onClick(MessageDialog dialog, View v) {
                return false;
            }
        }).show();
    }

    public void postCommentWithRating(DiscussEntity discussEntity) {
        MessageDialog.build()
                .setCustomView(new OnBindView<MessageDialog>(R.layout.dialog_rating) {
                    @Override
                    public void onBind(MessageDialog dialog, View v) {
                        Button btnCancel = v.findViewById(R.id.btnCancel);
                        Button btnOk = v.findViewById(R.id.btnOk);
                        EditText etComment = v.findViewById(R.id.etComment);
                        RatingBar ratingBar = v.findViewById(R.id.ratingBar);
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                requestComment(etComment.getText().toString(), discussEntity, ratingBar.getRating());
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .show();

    }

    public void postReport(DiscussEntity discussEntity) {
        InputDialog.build().setTitle("Report offending review").setOkButton("Report", (dialog, v, inputStr) -> {
            if (inputStr.isEmpty()) {
                Toast.makeText(RestaurantDetailActivity.this, "Please input report content", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (Global.userId == null) {
                Toast.makeText(RestaurantDetailActivity.this, "Please login first", Toast.LENGTH_SHORT).show();
                return false;
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("reportUserId", String.valueOf(Global.userId));
            map.put("reportUserName", String.valueOf(Global.username));
            map.put("reportContent", inputStr);
            if (discussEntity != null) {
                map.put("discussId", String.valueOf(discussEntity.id));
                map.put("attr1", discussEntity.content);
                map.put("attr2", discussEntity.userName);
            }
            WaitDialog.show("Report...");
            HttpManager.postJson(Config.IP + "/demo/report", map, new HttpManager.NetworkCallBack() {
                @Override
                public void onSuccess(String successInfo) {
                    BaseResponse baseResponse = JSON.parseObject(successInfo, BaseResponse.class);
                    if (baseResponse != null) {
                        if (baseResponse.code == 0) {
                            runOnUiThread(() -> ToastUtils.show("Report Success !"));
                        } else {
                            runOnUiThread(() -> {
                                ToastUtils.show(baseResponse.msg);
                            });
                        }
                    } else {
                        runOnUiThread(() -> {
                            ToastUtils.show("Comment Failed");
                        });
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
            return false;
        }).setCancelButton("CANCEL", new OnDialogButtonClickListener<MessageDialog>() {
            @Override
            public boolean onClick(MessageDialog dialog, View v) {
                return false;
            }
        }).show();
    }


    /**
     * reuqest api comment
     *
     * @param inputStr      /
     * @param discussEntity /
     * @return /
     */
    private void requestComment(String inputStr, DiscussEntity discussEntity, Float rating) {
        if (inputStr.isEmpty()) {
            Toast.makeText(RestaurantDetailActivity.this, "Please input comment", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Global.userId == null) {
            Toast.makeText(RestaurantDetailActivity.this, "Please login first", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", String.valueOf(Global.userId));
        map.put("restaurantId", restaurantDetail.id);
        map.put("restaurantName", restaurantDetail.name);
        map.put("isDelete", "0");
        map.put("content", inputStr);
        if (rating >= 0) {
            map.put("attr2", String.valueOf(rating));
        }
        if (discussEntity != null) {
            map.put("parentId", String.valueOf(discussEntity.id));
            map.put("parentUserName", discussEntity.userName);
            map.put("parentUserId", String.valueOf(discussEntity.id));
        }
        WaitDialog.show("Comment...");
        HttpManager.postJson(Config.IP + "/demo/discuss", map, new HttpManager.NetworkCallBack() {
            @Override
            public void onSuccess(String successInfo) {
                BaseResponse baseResponse = JSON.parseObject(successInfo, BaseResponse.class);
                if (baseResponse != null) {
                    if (baseResponse.code == 0) {
                        runOnUiThread(() -> ToastUtils.show("Comment Success"));
                        loadComments();
                    } else {
                        runOnUiThread(() -> {
                            ToastUtils.show(baseResponse.msg);
                        });
                    }
                } else {
                    runOnUiThread(() -> {
                        ToastUtils.show("Comment Failed");
                    });
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