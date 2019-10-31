package com.example.android.a7learntutorialapp.room_vewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PostViewModel extends AndroidViewModel {
    private PostRepository mRepository;
    private LiveData<List<Post>> mPosts;

    public PostViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PostRepository(application);
        mPosts = mRepository.getPosts();
    }

    public LiveData<List<Post>> getPosts() {
        return mPosts;
    }

    public void addPosts(List<Post> posts) {
        mRepository.addPosts(posts);
    }
}
