package com.example.android.a7learntutorialapp.data.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "roomtbl_posts")
public class PostEntity {

    @PrimaryKey
    @ColumnInfo(name = "col_id")
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "col_post_image_url")
    @SerializedName("image_url")
    private String postImageUrl;

    @ColumnInfo(name = "col_title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "col_content")
    @SerializedName("content")
    private String content;

    @ColumnInfo(name = "col_date")
    @SerializedName("date")
    private String date;

    @ColumnInfo(name = "col_is_visited")
    private int isVisited = 0;

    public PostEntity(int id, String postImageUrl, String title, String content, String date, int isVisited) {
        this.id = id;
        this.postImageUrl = postImageUrl;
        this.title = title;
        this.content = content;
        this.date = date;
        this.isVisited = isVisited;
    }

    public PostEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(int isVisited) {
        this.isVisited = isVisited;
    }
}
