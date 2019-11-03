package com.example.android.a7learntutorialapp.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android.a7learntutorialapp.data.local.PostDao;
import com.example.android.a7learntutorialapp.data.local.PostEntity;
import com.example.android.a7learntutorialapp.data.local.PostRoomDatabase;

import java.util.List;

public class PostRepository {
    private PostDao mPostDao;

    public PostRepository(Application application) {
        PostRoomDatabase db = PostRoomDatabase.getDatabase(application);
        mPostDao = db.postDao();
    }

    public LiveData<List<PostEntity>> getPosts(){
        return mPostDao.getPosts();
    }

    public void addPosts(List<PostEntity> posts){
        new InsertPostsTask(mPostDao).execute(posts.toArray(new PostEntity[posts.size()]));
    }
}
