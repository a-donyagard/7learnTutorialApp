package com.example.android.a7learntutorialapp.roomviewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PostRepository {
    private PostDao mPostDao;

    PostRepository(Application application) {
        PostRoomDatabase db = PostRoomDatabase.getDatabase(application);
        mPostDao = db.postDao();
    }

    LiveData<List<Post>> getPosts(){
        return mPostDao.getPosts();
    }

    public void addPosts(List<Post> posts){
        new InsertPostsTask(mPostDao).execute(posts.toArray(new Post[posts.size()]));
    }
}
