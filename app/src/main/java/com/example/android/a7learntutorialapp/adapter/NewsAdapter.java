package com.example.android.a7learntutorialapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.datamodel.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Saeed shahini on 7/22/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


    private Context context;
    private List<Post> posts;

    public NewsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_news, parent, false);
        Typeface yekanTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/yekan.ttf");
        return new NewsViewHolder(view, yekanTypeface);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        Post post = posts.get(position);
        //استفاده از کتابخانه پیکاسو جهت کش کردن تصاویر دریافتی از سرور برای استفاده های بعدی
        Picasso.with(context).load(post.getPostImageUrl().replace("localhost","192.168.1.106")).into(holder.newsImage);
        holder.title.setText(post.getTitle());
        holder.content.setText(post.getContent());
        holder.date.setText(post.getDate());
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
            title = (TextView) itemView.findViewById(R.id.news_title);
            title.setTypeface(yekanTypeface);

            content = (TextView) itemView.findViewById(R.id.news_content);
            content.setTypeface(yekanTypeface);

            date = (TextView) itemView.findViewById(R.id.news_date);
            date.setTypeface(yekanTypeface);
        }
    }
}
