package com.example.mygithubuser.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.SearchView;


import com.example.mygithubuser.R;
import com.example.mygithubuser.adapter.UserAdapter;
import com.example.mygithubuser.model.SearchUser;
import com.example.mygithubuser.model.UserModel;
import com.example.mygithubuser.retrofit.ApiClient;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<UserModel> dataGithub = new ArrayList<>();
    private RecyclerView rvUser;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        rvUser = findViewById(R.id.rv_user);
        rvUser.setLayoutManager(new LinearLayoutManager(this));

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) findViewById(R.id.sv_user);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.nama_user));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    showProgress(true);
                    if (query != null) {
                        getData(query);
                    } else {
                        Toast.makeText(MainActivity.this, "Please Insert Username", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }


    }
    private void showProgress(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


    private void getData(final String usernames){
        Call<SearchUser> request = ApiClient.getApiService().getSearchUser(usernames);
        request.enqueue(new Callback<SearchUser>() {
            @Override
            public void onResponse(Call<SearchUser> call, Response<SearchUser> response) {
                if(response.isSuccessful()){
                    dataGithub = response.body().getItems();

                    rvUser.setAdapter(new UserAdapter(MainActivity.this,dataGithub));
                    showProgress(false);
                }else {
                    Toast.makeText(MainActivity.this, "Request Not Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchUser> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request Failure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if(item.getItemId() == R.id.action_change_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
