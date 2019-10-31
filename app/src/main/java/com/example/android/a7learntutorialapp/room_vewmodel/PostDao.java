package com.example.android.a7learntutorialapp.room_vewmodel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostDao {

    @Insert
    void addPosts(Post post);

    @Query("SELECT * FROM roomtbl_posts")
    LiveData<List<Post>> getPosts();

    @Query("UPDATE roomtbl_posts SET col_is_visited = :isVisited WHERE col_id = :postId")
    void setPostIsVisited(int postId, int isVisited);
}
