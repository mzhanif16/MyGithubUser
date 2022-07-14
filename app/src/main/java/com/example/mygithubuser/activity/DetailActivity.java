package com.example.mygithubuser.activity;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mygithubuser.R;
import com.example.mygithubuser.adapter.SectionPagerAdapter;
import com.example.mygithubuser.adapter.UserAdapter;
import com.example.mygithubuser.model.DetailUserModel;
import com.example.mygithubuser.model.UserModel;
import com.example.mygithubuser.retrofit.ApiClient;
import com.google.android.material.tabs.TabLayout;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    DetailUserModel dataDetailUser;
    UserModel dataUser;
    TextView tvUsername,tvLocation,tvEmail,tvNama;
    ImageView ivAvatar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar JUDUL = getSupportActionBar();
        JUDUL.setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getBundleExtra(UserAdapter.DATA_EXTRA);
        dataUser = Parcels.unwrap(bundle.getParcelable(UserAdapter.DATA_USER));

        ivAvatar = findViewById(R.id.img_item_photo_detail);
        tvUsername = findViewById(R.id.tv_name);
        tvLocation = findViewById(R.id.tv_location_detail);
        tvEmail = findViewById(R.id.tv_repository_detail);
        tvNama = findViewById(R.id.tv_nama1);

        final ProgressDialog progress = new ProgressDialog(DetailActivity.this);
        progress.setMessage(getString(R.string.progress));
        progress.show();

        Glide.with(DetailActivity.this)
                .load(dataUser.getAvatarUrl())
                .into(ivAvatar);
        tvUsername.setText(dataUser.getLogin());

        Call<DetailUserModel> request = ApiClient.getApiService().getDetailUser(dataUser.getLogin());
        request.enqueue(new Callback<DetailUserModel>() {
            @Override
            public void onResponse(Call<DetailUserModel> call, Response<DetailUserModel> response) {
                dataDetailUser = response.body();

                tvNama.setText(dataDetailUser.getName());
                tvLocation.setText(dataDetailUser.getLocation());
                tvEmail.setText(dataDetailUser.getEmail());
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<DetailUserModel> call, Throwable t) {

            }
        });

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(sectionPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager2);


        getSupportActionBar().setElevation(0);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}