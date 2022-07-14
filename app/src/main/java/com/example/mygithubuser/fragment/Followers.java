package com.example.mygithubuser.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygithubuser.activity.DetailActivity;
import com.example.mygithubuser.R;
import com.example.mygithubuser.adapter.FollowersAdapter;
import com.example.mygithubuser.adapter.UserAdapter;
import com.example.mygithubuser.model.FollowerModel;
import com.example.mygithubuser.model.UserModel;
import com.example.mygithubuser.retrofit.ApiClient;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Followers extends Fragment {
    RecyclerView rvFollower;
    UserModel dataUser;

    public Followers(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_followers, container, false);
    }

    @Override
    public void onViewCreated(@NotNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DetailActivity detailActivity =(DetailActivity) getActivity();
        Bundle bundle = detailActivity.getIntent().getBundleExtra(UserAdapter.DATA_EXTRA);
        dataUser = Parcels.unwrap(bundle.getParcelable(UserAdapter.DATA_USER));

        rvFollower = view.findViewById(R.id.rv_follower);
        rvFollower.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Call<List<FollowerModel>> request = ApiClient.getApiService().getFollowerUser(dataUser.getLogin());
        request.enqueue(new Callback<List<FollowerModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<FollowerModel>> call,@NotNull Response<List<FollowerModel>> response) {
                ArrayList<FollowerModel> listFollower = new ArrayList<>();
                if(response.isSuccessful()){
                    if(response.body()!= null){
                        listFollower.addAll(response.body());
                        Log.d("TAG RESULT", "onResponse" + listFollower.size());
                        rvFollower.setAdapter(new FollowersAdapter(getContext(),listFollower));
                    }
                }else{
                    Toast.makeText(getContext(), "Request Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FollowerModel>> call, Throwable t) {
                Toast.makeText(getContext(),"Request Failure"+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
