package de.hdmstuttgart.todolist.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ListItem.class}, version=1)
public abstract class ItemDatabase extends RoomDatabase {
    public abstract ItemDao ItemDao();

    private static volatile ItemDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static final ItemDatabase getDatabase(final Context context){
        if(INSTANCE== null){
            synchronized (ItemDatabase.class){
                if(INSTANCE== null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ItemDatabase.class,"item_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
