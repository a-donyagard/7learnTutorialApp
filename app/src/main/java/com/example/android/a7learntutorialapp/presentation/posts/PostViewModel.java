package com.example.android.a7learntutorialapp.presentation.posts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.a7learntutorialapp.data.cloud.ApiService;
import com.example.android.a7learntutorialapp.data.local.PostEntity;
import com.example.android.a7learntutorialapp.data.repository.PostRepository;

import java.util.List;

public class PostViewModel extends AndroidViewModel {
    private PostRepository mRepository;
    private LiveData<List<PostEntity>> mPosts;
    private MutableLiveData<List<PostEntity>> postsApi = new MutableLiveData<>();

    public PostViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PostRepository(application);
        mPosts = mRepository.getPostsDb();
    }

    public LiveData<List<PostEntity>> getPostsDb() {
        return mPosts;
    }

    public void addPostsDb(List<PostEntity> posts) {
        mRepository.addPostsDb(posts);
    }

    public void getPostsApi(){
        mRepository.getPostsApi(new PostRepository.OnPostsReceived() {
            @Override
            public void onReceived(List<PostEntity> posts) {
                postsApi.setValue(posts);
            }
        });
    }

    public LiveData<List<PostEntity>> getPostsApiLiveData(){
        return postsApi;
    }
}
