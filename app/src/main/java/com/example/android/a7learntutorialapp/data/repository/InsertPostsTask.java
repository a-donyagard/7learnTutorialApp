package com.example.android.a7learntutorialapp.data.repository;

import android.os.AsyncTask;

import com.example.android.a7learntutorialapp.data.local.PostDao;
import com.example.android.a7learntutorialapp.data.local.PostEntity;

public class InsertPostsTask extends AsyncTask<PostEntity, Void, Void> {
    private PostDao mAsyncTaskDao;

    InsertPostsTask(PostDao dao) {
        mAsyncTaskDao = dao;
    }


    @Override
    protected Void doInBackground(PostEntity... posts) {
        mAsyncTaskDao.addPosts(posts);
        return null;
    }
}