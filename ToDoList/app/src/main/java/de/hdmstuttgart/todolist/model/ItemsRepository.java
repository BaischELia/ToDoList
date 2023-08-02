package de.hdmstuttgart.todolist.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemsRepository {
    private ItemDao mItemDao;
    private LiveData<List<ListItem>> mAllListItems;


    public ItemsRepository(Application application){
        ItemDatabase db= ItemDatabase.getDatabase(application);
        mItemDao= db.ItemDao();
        mAllListItems= mItemDao.getAll();
    }

    public LiveData<List<ListItem>> getAllItems(){
        return mAllListItems;
    }

    public void insert(ListItem listItem){
        ItemDatabase.databaseWriteExecutor.execute(()->
                mItemDao.insert(listItem));
    }
    public void delete(ListItem listItem){
        ItemDatabase.databaseWriteExecutor.execute(()->
                mItemDao.delete(listItem));
    }

    public void updateState(ListItem listItem, boolean completion){
       int itemNumber= listItem.getItemNumber();
        ItemDatabase.databaseWriteExecutor.execute(()->
                mItemDao.updateState(itemNumber,completion));
    }

    /*public boolean getState(ListItem listItem){
        int itemNumber= listItem.getItemNumber();

    }*/

    public void deleteAll(){
        ItemDatabase.databaseWriteExecutor.execute(()->
                mItemDao.deleteAll());
    }

}
