package com.example.mygithubuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mygithubuser.R;
import com.example.mygithubuser.model.FollowerModel;


import java.util.ArrayList;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.FollowerViewHolder> {

    private Context context;
    private ArrayList<FollowerModel> data = new ArrayList<>();


    public FollowersAdapter (Context context, ArrayList<FollowerModel> listFollower) {
        this.context = context;
        this.data = listFollower;
    }

    @NonNull
    @Override
    public FollowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.follower_items, parent, false);
        return new FollowerViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowersAdapter.FollowerViewHolder holder, int position) {
        holder.tvUsernameFollower.setText(data.get(position).getLogin());
        Glide.with(context)
                .load(data.get(position).getAvatarUrl())
                .into(holder.ivAvatarFollower);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FollowerViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsernameFollower;
        ImageView ivAvatarFollower;

        public FollowerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsernameFollower = itemView.findViewById(R.id.tv_username_follower);
            ivAvatarFollower = itemView.findViewById(R.id.iv_avatar_follower);

        }
    }
}
