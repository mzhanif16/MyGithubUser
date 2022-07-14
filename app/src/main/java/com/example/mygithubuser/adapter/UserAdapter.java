package com.example.mygithubuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygithubuser.activity.DetailActivity;
import com.example.mygithubuser.R;
import com.example.mygithubuser.model.UserModel;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ListViewHolder> {

    public static final String DATA_USER = "datauser";
    public static final String DATA_EXTRA = "dataextra";
    private List<UserModel> data = new ArrayList<>();
    private Context context;


    public UserAdapter(Context context,List<UserModel> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false);
        return new ListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ListViewHolder holder, int position) {
        Glide.with(context)
                .load(data.get(position).getAvatarUrl())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);
        holder.tvUsername.setText(data.get(position).getLogin());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DATA_USER, Parcels.wrap(data.get(position)));
                move.putExtra(DATA_EXTRA,bundle);
                context.startActivity(move);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvUsername;

        ListViewHolder(View itemView) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvUsername = itemView.findViewById(R.id.tv_item_name);
        }
    }
}
