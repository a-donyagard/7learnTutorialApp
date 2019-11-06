package com.example.android.a7learntutorialapp.presentation.posts;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.android.a7learntutorialapp.data.cloud.ApiService;
import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.data.local.PostEntity;

import java.util.ArrayList;
import java.util.List;

public class PostsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<PostEntity> posts = new ArrayList<>();
    private static final int REQUEST_PERMISSION_CODE = 200;
    private int page = 0;
    private PostsAdapter postsAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    private PostViewModel mPostViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        setupRecyclerView();

        /* swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        postsAdapter.clear();
                        postsAdapter.addPostsDb(getFakePosts());
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary)
                , ContextCompat.getColor(this, R.color.colorAccent)); */

        //old way by sqlite
//        getPostsFromDatabase();

        //new way by ROOM and ViewModel
        mPostViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        mPostViewModel.getPostsDb().observe(this, new Observer<List<PostEntity>>() {
            @Override
            public void onChanged(@Nullable final List<PostEntity> posts) {
                // Update the cached copy of the words in the adapter.
                PostsAdapter postsAdapter = new PostsAdapter(PostsActivity.this, posts);
            }
        });

        mPostViewModel.getPostsApi();
        mPostViewModel.getPostsApiLiveData().observe(this, new Observer<List<PostEntity>>() {
            @Override
            public void onChanged(List<PostEntity> postEntities) {
                PostsActivity.this.posts = postEntities;

                //old way by sqlite
//                SevenLearnDatabaseOpenHelper openHelper = new SevenLearnDatabaseOpenHelper(PostsActivity.this);
//                openHelper.addPostsDb(posts);

                mPostViewModel.addPostsDb(postEntities);

                PostsAdapter postsAdapter = new PostsAdapter(PostsActivity.this, postEntities);
                recyclerView.setAdapter(postsAdapter);
            }
        });

                /*
                //چک کردن و درخواست پرمیژن نوشتن در کارت حافظه از کاربر و سپس ذخیره عکس ها در کارت حافظه
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        saveImagesInSdCard();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
                    }
                } else {
                    saveImagesInSdCard();
                } */


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

        /* postsAdapter = new PostsAdapter(this);
        postsAdapter.addPostsDb(getFakePosts());
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
                        postsAdapter.addPostsDb(DataFakeGenerator.getPostsDb(page));
                        progressBar.setVisibility(View.GONE);
                    }
                }, 2000);
            }
        }); */
    }


    /* private void getPostsFromDatabase() {
        SevenLearnDatabaseOpenHelper databaseOpenHelper = new SevenLearnDatabaseOpenHelper(this);
        List<PostEntity> posts = databaseOpenHelper.getPostsDb();
        PostsAdapter postsAdapter = new PostsAdapter(this, posts);
        recyclerView.setAdapter(postsAdapter);
    } */


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

    /* private List<PostEntity> getFakePosts() {
        return DataFakeGenerator.getPostsDb(page);
    } */
}
