package com.example.mygithubuser.model;

import com.google.gson.annotations.SerializedName;

public class DetailUserModel {
    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("followers")
    private int followers;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("following")
    private int following;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private String location;


    public void setLogin(String login){
        this.login = login;
    }

    public String getLogin(){
        return login;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setFollowers(int followers){
        this.followers = followers;
    }

    public int getFollowers(){
        return followers;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl(){
        return avatarUrl;
    }

    public void setFollowing(int following){
        this.following = following;
    }

    public int getFollowing(){
        return following;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getLocation(){
        return location;
    }

}
