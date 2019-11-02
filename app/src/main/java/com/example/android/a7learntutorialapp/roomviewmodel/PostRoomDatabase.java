package com.example.android.a7learntutorialapp.roomviewmodel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Post.class}, version = 4, exportSchema = false)
public abstract class PostRoomDatabase extends RoomDatabase {
    public abstract PostDao postDao();

    private static PostRoomDatabase INSTANCE;

    public static PostRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (PostRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PostRoomDatabase.class, "roomdb_7learn")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
