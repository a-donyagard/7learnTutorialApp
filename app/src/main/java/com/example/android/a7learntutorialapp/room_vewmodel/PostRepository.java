package com.example.android.a7learntutorialapp.room_vewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PostRepository {
    PostDao mPostDao;
    LiveData<List<Post>> mPosts;

    PostRepository(Application application) {
        PostRoomDatabase db = PostRoomDatabase.getDatabase(application);
        mPostDao = db.postDao();
        mPosts = mPostDao.getPosts();
    }

    LiveData<List<Post>> getPosts(){
        return mPosts;
    }

    public void addPosts(List<Post> posts){
        new InsertPostsTask(mPostDao).execute(posts.toArray(new Post[posts.size()]));
    }
}
