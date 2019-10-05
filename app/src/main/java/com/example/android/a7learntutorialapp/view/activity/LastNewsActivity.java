package com.example.android.a7learntutorialapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.a7learntutorialapp.ApiService;
import com.example.android.a7learntutorialapp.adapter.NewsAdapter;
import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.datamodel.Post;

import java.util.List;

public class LastNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_news);

        ApiService apiService=new ApiService(this);

        apiService.getPosts(new ApiService.OnPostsReceived() {
            @Override
            public void onReceived(List<Post> posts) {
                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                NewsAdapter newsAdapter=new NewsAdapter(LastNewsActivity.this,posts);
                recyclerView.setLayoutManager(new LinearLayoutManager(LastNewsActivity.this,LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(newsAdapter);
            }
        });
    }
}
