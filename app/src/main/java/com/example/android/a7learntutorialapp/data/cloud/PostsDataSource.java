package com.example.android.a7learntutorialapp.data.cloud;

import androidx.lifecycle.LiveData;

import com.example.android.a7learntutorialapp.data.local.PostEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsDataSource {
    @GET("7learn/getposts.php")
    Call<List<PostEntity>> getPosts();
}
