package com.example.android.a7learntutorialapp.roomviewmodel;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class InsertPostsTask extends AsyncTask<Post, Void, Void> {
    private PostDao mAsyncTaskDao;
    private ProgressDialog progressDialog;

    InsertPostsTask(PostDao dao) {
        mAsyncTaskDao = dao;
    }


    @Override
    protected Void doInBackground(Post... posts) {
        mAsyncTaskDao.addPosts(posts);
        return null;
    }
}