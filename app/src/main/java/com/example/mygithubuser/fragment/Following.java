package com.example.mygithubuser.fragment;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mygithubuser.activity.DetailActivity;
import com.example.mygithubuser.R;
import com.example.mygithubuser.adapter.FollowingAdapter;
import com.example.mygithubuser.adapter.UserAdapter;
import com.example.mygithubuser.model.FollowingModel;
import com.example.mygithubuser.model.UserModel;
import com.example.mygithubuser.retrofit.ApiClient;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Following extends Fragment {
    RecyclerView rvFollowing;
    UserModel dataUser;


    public Following() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false);
    }

    @Override
    public void onViewCreated(@NotNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DetailActivity detailActivity =(DetailActivity) getActivity();
        Bundle bundle = detailActivity.getIntent().getBundleExtra(UserAdapter.DATA_EXTRA);
        dataUser = Parcels.unwrap(bundle.getParcelable(UserAdapter.DATA_USER));

        rvFollowing = view.findViewById(R.id.rv_following);
        rvFollowing.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Call<List<FollowingModel>> request = ApiClient.getApiService().getFollowingUser(dataUser.getLogin());
        request.enqueue(new Callback<List<FollowingModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<FollowingModel>> call,@NotNull Response<List<FollowingModel>> response) {
                ArrayList<FollowingModel> listFollowing = new ArrayList<>();
                if(response.isSuccessful()){
                    if(response.body()!= null){
                        listFollowing.addAll(response.body());
                        Log.d("TAG RESULT", "onResponse" + listFollowing.size());
                        rvFollowing.setAdapter(new FollowingAdapter(getContext(),listFollowing));
                    }
                }else{
                    Toast.makeText(getContext(), "Request Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FollowingModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Request Failure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}