package com.example.android.a7learntutorialapp.presentation.posts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.data.local.PostEntity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.NewsViewHolder> {


    private Context context;
//    private List<PostEntity> posts = new ArrayList<>();
    private List<PostEntity> posts;
    Typeface yekanTypeface;

    public PostsAdapter(Context context, List<PostEntity> posts) {
        this.context = context;
        this.posts = posts;
    }

    /* public void addPosts(List<PostEntity> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    } */

    /*public void clear() {
        this.posts = new ArrayList<>();
        notifyDataSetChanged();
    }*/

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_posts, parent, false);
//        Typeface yekanTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/yekan.ttf");
        return new NewsViewHolder(view, yekanTypeface);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final PostEntity post = posts.get(position);
        //استفاده از کتابخانه پیکاسو جهت کش کردن تصاویر دریافتی از سرور برای استفاده های بعدی
        Picasso.with(context).load(post.getPostImageUrl().replace("localhost", "192.168.1.106")).into(holder.newsImage);
//        Picasso.with(context).load(post.getPostImage()).into(holder.newsImage);
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
//                intent.putExtra("image_res_id", post.getPostImage());
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
