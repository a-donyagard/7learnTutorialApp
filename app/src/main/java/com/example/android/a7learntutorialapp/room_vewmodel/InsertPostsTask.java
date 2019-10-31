package com.example.android.a7learntutorialapp.room_vewmodel;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.List;

public class InsertPostsTask extends AsyncTask<Post, Void, Void> {
    private PostDao mAsyncTaskDao;
    private ProgressDialog progressDialog;

    InsertPostsTask(PostDao dao) {
        mAsyncTaskDao = dao;
    }


    @Override
    protected Void doInBackground(Post... posts) {
        for(Post post: posts){
            mAsyncTaskDao.addPosts(post);
        }
        return null;
    }
}