package de.hdmstuttgart.todolist.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ListItem listItem);

    @Delete
    void delete(ListItem listItem);

    @Query("DELETE FROM listitem")
    void deleteAll();

  /*  @Update
    void updateState(ListItem listItem, boolean completion);*/

    @Query("UPDATE ListItem SET finished=:state  WHERE itemNumber= :itemId")
    void updateState(int itemId, boolean state);

    @Query("SELECT finished FROM ListItem WHERE itemNumber=:ItemId")
    boolean getState(int ItemId);

    @Query("SELECT * FROM listItem")
    LiveData<List<ListItem>> getAll();
}
