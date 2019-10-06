package com.example.android.a7learntutorialapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.a7learntutorialapp.ApiService;
import com.example.android.a7learntutorialapp.SevenLearnDatabaseOpenHelper;
import com.example.android.a7learntutorialapp.adapter.PostsAdapter;
import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.datamodel.Post;

import java.util.List;

public class PostsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        setupRecyclerView();
        getPostsFromDatabase();

        ApiService apiService = new ApiService(this);
        apiService.getPosts(new ApiService.OnPostsReceived() {
            @Override
            public void onReceived(List<Post> posts) {
                SevenLearnDatabaseOpenHelper openHelper = new SevenLearnDatabaseOpenHelper(PostsActivity.this);
                openHelper.addPosts(posts);

                PostsAdapter postsAdapter = new PostsAdapter(PostsActivity.this, posts);
                recyclerView.setAdapter(postsAdapter);
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostsActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    private void getPostsFromDatabase() {
        SevenLearnDatabaseOpenHelper databaseOpenHelper = new SevenLearnDatabaseOpenHelper(this);
        List<Post> posts = databaseOpenHelper.getPosts();
        PostsAdapter postsAdapter = new PostsAdapter(this, posts);
        recyclerView.setAdapter(postsAdapter);
    }
}
