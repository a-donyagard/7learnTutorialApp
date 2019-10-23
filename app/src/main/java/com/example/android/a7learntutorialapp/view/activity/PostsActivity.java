package com.example.android.a7learntutorialapp.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.a7learntutorialapp.ApiService;
import com.example.android.a7learntutorialapp.DataFakeGenerator;
import com.example.android.a7learntutorialapp.DownloadImageTask;
import com.example.android.a7learntutorialapp.SevenLearnDatabaseOpenHelper;
import com.example.android.a7learntutorialapp.adapter.PostsAdapter;
import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.datamodel.Post;

import java.util.ArrayList;
import java.util.List;

import ss.com.infinitescrollprovider.InfiniteScrollProvider;
import ss.com.infinitescrollprovider.OnLoadMoreListener;

public class PostsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Post> posts = new ArrayList<>();
    private static final int REQUEST_PERMISSION_CODE = 200;
    private int page = 0;
    private PostsAdapter postsAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        setupRecyclerView();

        /*
        getPostsFromDatabase();

        ApiService apiService = new ApiService(this);
        apiService.getPosts(new ApiService.OnPostsReceived() {
            @Override
            public void onReceived(List<Post> posts) {
                PostsActivity.this.posts = posts;

                SevenLearnDatabaseOpenHelper openHelper = new SevenLearnDatabaseOpenHelper(PostsActivity.this);
                //openHelper.addPosts(posts);

                PostsAdapter postsAdapter = new PostsAdapter(PostsActivity.this, posts);
                recyclerView.setAdapter(postsAdapter);

                //چک کردن و درخواست پرمیژن نوشتن در کارت حافظه از کاربر و سپس ذخیره عکس ها در کارت حافظه
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        saveImagesInSdCard();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
                    }
                } else {
                    saveImagesInSdCard();
                }
            }
        });
        */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //افزودن دکمه back به بالای صفحه
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostsActivity.this, LinearLayoutManager.VERTICAL, false));

        postsAdapter = new PostsAdapter(this);
        postsAdapter.addPosts(getFakePosts());
        recyclerView.setAdapter(postsAdapter);

        InfiniteScrollProvider infiniteScrollProvider = new InfiniteScrollProvider();
        infiniteScrollProvider.attach(recyclerView, new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page += 1;
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        postsAdapter.addPosts(DataFakeGenerator.getPosts(page));
                        progressBar.setVisibility(View.GONE);
                    }
                }, 2000);
            }
        });
    }

    /*
    private void getPostsFromDatabase() {
        SevenLearnDatabaseOpenHelper databaseOpenHelper = new SevenLearnDatabaseOpenHelper(this);
        List<Post> posts = databaseOpenHelper.getPosts();
        PostsAdapter postsAdapter = new PostsAdapter(this, posts);
        recyclerView.setAdapter(postsAdapter);
    }
    */

    private void saveImagesInSdCard() {
        List<String> urls = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            urls.add(posts.get(i).getPostImageUrl().replace("localhost", "192.168.1.106"));
        }
        DownloadImageTask downloadImageTask = new DownloadImageTask(this, urls);
        downloadImageTask.execute();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults[0] >= 0) {
                saveImagesInSdCard();
            } else {
                Toast.makeText(this, "برای ذخیره سازی در حافظه دستگاه باید دسترسی لازم را بدهید.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private List<Post> getFakePosts() {
        return DataFakeGenerator.getPosts(page);
    }
}
