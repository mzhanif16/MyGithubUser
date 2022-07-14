package com.example.mygithubuser.retrofit;

import com.example.mygithubuser.model.DetailUserModel;
import com.example.mygithubuser.model.FollowerModel;
import com.example.mygithubuser.model.FollowingModel;
import com.example.mygithubuser.model.SearchUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    Call<SearchUser> getSearchUser(
            @Query("q") String username
    );

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    Call<DetailUserModel> getDetailUser(
            @Path("username") String username
    );

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    Call<List<FollowerModel>> getFollowerUser(
            @Path("username") String username
    );

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    Call<List<FollowingModel>> getFollowingUser(
            @Path("username") String username
    );
}
