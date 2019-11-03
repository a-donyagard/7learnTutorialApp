package com.example.android.a7learntutorialapp.presentation.posts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.a7learntutorialapp.data.local.PostEntity;
import com.example.android.a7learntutorialapp.data.repository.PostRepository;

import java.util.List;

public class PostViewModel extends AndroidViewModel {
    private PostRepository mRepository;
    private LiveData<List<PostEntity>> mPosts;

    public PostViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PostRepository(application);
        mPosts = mRepository.getPosts();
    }

    public LiveData<List<PostEntity>> getPosts() {
        return mPosts;
    }

    public void addPosts(List<PostEntity> posts) {
        mRepository.addPosts(posts);
    }
}
