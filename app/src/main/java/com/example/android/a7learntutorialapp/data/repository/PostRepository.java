package com.example.android.a7learntutorialapp.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android.a7learntutorialapp.data.cloud.ApiService;
import com.example.android.a7learntutorialapp.data.cloud.PostsDataSource;
import com.example.android.a7learntutorialapp.data.cloud.RetrofitGenerator;
import com.example.android.a7learntutorialapp.data.local.PostDao;
import com.example.android.a7learntutorialapp.data.local.PostEntity;
import com.example.android.a7learntutorialapp.data.local.PostRoomDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private PostDao mPostDao;

    public PostRepository(Application application) {
        PostRoomDatabase db = PostRoomDatabase.getDatabase(application);
        mPostDao = db.postDao();
    }

    public LiveData<List<PostEntity>> getPostsDb() {
        return mPostDao.getPosts();
    }

    public void addPostsDb(List<PostEntity> posts) {
        new InsertPostsTask(mPostDao).execute(posts.toArray(new PostEntity[posts.size()]));
    }

    public void getPostsApi(final OnPostsReceived onPostsReceived) {

        PostsDataSource postsDataSource = RetrofitGenerator.getPostsDataSource();
        Call<List<PostEntity>> posts = postsDataSource.getPosts();
        posts.enqueue(new Callback<List<PostEntity>>() {
            @Override
            public void onResponse(Call<List<PostEntity>> call, Response<List<PostEntity>> response) {
                if (response.isSuccessful()) {
                    onPostsReceived.onReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PostEntity>> call, Throwable t) {

            }
        });
    }

    public interface OnPostsReceived {
        void onReceived(List<PostEntity> posts);
    }
}
