package de.hdmstuttgart.todolist;

import android.app.Application;
import android.content.ClipData;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.hdmstuttgart.todolist.model.ItemsRepository;
import de.hdmstuttgart.todolist.model.ListItem;

public class ListViewModel extends AndroidViewModel {
    private final ItemsRepository mItemsRepository;

    private final LiveData<List<ListItem>> mALlItems;

    public ListViewModel(Application application){
        super(application);
        mItemsRepository= new ItemsRepository(application);
        mALlItems= mItemsRepository.getAllItems();
    }
    LiveData<List<ListItem>> getALlItems(){return mALlItems;}

    public void insert(ListItem listItem){mItemsRepository.insert(listItem);}

    public void delete(ListItem listItem){mItemsRepository.delete(listItem);}

    public void updateState(ListItem listItem, boolean completion){
        mItemsRepository.updateState(listItem,completion);
    }
    public void deleteALl(){mItemsRepository.deleteAll();}
}
