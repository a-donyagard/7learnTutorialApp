package com.example.android.a7learntutorialapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.SevenLearnDatabaseOpenHelper;
import com.example.android.a7learntutorialapp.datamodel.Post;
import com.example.android.a7learntutorialapp.view.activity.PostActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Saeed shahini on 7/22/2016.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.NewsViewHolder> {


    private Context context;
    private List<Post> posts;
    Typeface yekanTypeface;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_posts, parent, false);
//        Typeface yekanTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/yekan.ttf");
        return new NewsViewHolder(view, yekanTypeface);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final Post post = posts.get(position);
        //استفاده از کتابخانه پیکاسو جهت کش کردن تصاویر دریافتی از سرور برای استفاده های بعدی
        Picasso.with(context).load(post.getPostImageUrl().replace("localhost", "192.168.1.106")).into(holder.newsImage);
        holder.title.setText(post.getTitle());
        holder.content.setText(post.getContent());
        holder.date.setText(post.getDate());

        if (post.getIsVisited() == 1) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_visited_post));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_not_visited_post));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra(SevenLearnDatabaseOpenHelper.COL_ID, post.getId());
                intent.putExtra(SevenLearnDatabaseOpenHelper.COL_TITLE, post.getTitle());
                intent.putExtra(SevenLearnDatabaseOpenHelper.COL_CONTENT, post.getContent());
                intent.putExtra(SevenLearnDatabaseOpenHelper.COL_POST_IMAGE_URL, post.getPostImageUrl());
                intent.putExtra(SevenLearnDatabaseOpenHelper.COL_IS_VISITED, post.getIsVisited());
                intent.putExtra(SevenLearnDatabaseOpenHelper.COL_DATE, post.getDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView newsImage;
        private TextView title;
        private TextView content;
        private TextView date;

        public NewsViewHolder(View itemView, Typeface yekanTypeface) {
            super(itemView);
            newsImage = (ImageView) itemView.findViewById(R.id.item_image);
            title = (TextView) itemView.findViewById(R.id.posts_title);
//            title.setTypeface(yekanTypeface);

            content = (TextView) itemView.findViewById(R.id.posts_content);
//            content.setTypeface(yekanTypeface);

            date = (TextView) itemView.findViewById(R.id.posts_date);
//            date.setTypeface(yekanTypeface);
        }
    }
}
