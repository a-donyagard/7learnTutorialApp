package com.example.android.a7learntutorialapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPosts(PostEntity... posts);

    @Query("SELECT * FROM roomtbl_posts")
    LiveData<List<PostEntity>> getPosts();

    @Query("UPDATE roomtbl_posts SET col_is_visited = :isVisited WHERE col_id = :postId")
    void setPostIsVisited(int postId, int isVisited);
}
