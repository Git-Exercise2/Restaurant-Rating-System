package com.demo.maintenance.adapter;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.maintenance.R;
import com.demo.maintenance.activity.CommentListActivity;
import com.demo.maintenance.activity.RestaurantDetailActivity;
import com.demo.maintenance.bean.DiscussEntity;
import com.demo.maintenance.contant.Global;
import com.demo.maintenance.utils.AntiShakeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<DiscussEntity> comments = new ArrayList<>();

    public RestaurantDetailActivity restaurantDetailActivity;

    public CommentListActivity commentListActivity;

    public void submitList(List<DiscussEntity> newComments) {
        comments.clear();
        comments.addAll(newComments);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_item_commemt, parent,
                false
        );
        return new CommentViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        DiscussEntity comment = comments.get(position);
        holder.tvAuthor.setText(comment.userName);
        holder.tvContent.setText(comment.content);
        holder.tvTime.setText(comment.createDate);

        if (comment.parentId != null) {
            holder.tvReplyTo.setVisibility(View.VISIBLE);
            holder.tvReplyTo.setText("Reply to @" + comment.parentUserName);
        } else {
            holder.tvReplyTo.setVisibility(View.GONE);
        }

        if (commentListActivity != null){
            holder.btn_container.setVisibility(ViewGroup.GONE);
        }
        if (restaurantDetailActivity != null){
            holder.btn_container.setVisibility(ViewGroup.VISIBLE);
        }
        if ("1".equals(comment.isDelete)){
            holder.btn_container_2.setVisibility(View.VISIBLE);
        }else {
            holder.btn_container_2.setVisibility(View.GONE);
        }

        if (Objects.equals(Global.userId, comment.userId) ){
            holder.btn_container.setVisibility(View.GONE);
        }else {
            holder.btn_container.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(comment.attr2)){
            try{
                holder.rating_bar.setRating(Float.parseFloat(comment.attr2));
            }catch (Exception e){
                e.printStackTrace();
                holder.rating_bar.setVisibility(ViewGroup.GONE);
            }
        }else {
            holder.rating_bar.setVisibility(ViewGroup.GONE);
        }

        holder.btn_reply.setOnClickListener(v -> {
            if (AntiShakeUtils.isInvalidClick(v)) {
                return;
            }
            if (restaurantDetailActivity != null){
                restaurantDetailActivity.postComment(comment);
            }
        });

        holder.btn_report.setOnClickListener(v -> {
            if (AntiShakeUtils.isInvalidClick(v)) {
                return;
            }
            if (restaurantDetailActivity != null){
                restaurantDetailActivity.postReport(comment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView tvAuthor, tvContent, tvReplyTo, tvTime;
        Button btn_reply, btn_report;
        LinearLayout btn_container;
        LinearLayout btn_container_2;
        RatingBar rating_bar;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvReplyTo = itemView.findViewById(R.id.tv_reply_hint);
            tvTime = itemView.findViewById(R.id.tv_time);
            btn_reply = itemView.findViewById(R.id.btn_reply);
            btn_report = itemView.findViewById(R.id.btn_report);
            btn_container = itemView.findViewById(R.id.btn_container);
            btn_container_2 = itemView.findViewById(R.id.btn_container_2);
            rating_bar = itemView.findViewById(R.id.rating_bar);
        }
    }
}