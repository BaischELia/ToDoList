package de.hdmstuttgart.todolist;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdmstuttgart.todolist.model.ListItem;

public class ItemListAdapter extends RecyclerView.Adapter <ItemListAdapter.ItemViewHolder> {

   private final List<ListItem> itemList;
   private LayoutInflater layoutInflater;
   private OnFinishListener finishListener;
  // private OnDeleteListener deleteListener;


   public ItemListAdapter(Context context,List <ListItem> itemList, OnFinishListener finishListener/*,OnDeleteListener deleteListener*/){

       layoutInflater= LayoutInflater.from(context);
       this.itemList=itemList;
       this.finishListener = finishListener;
      // this.deleteListener= deleteListener;
   }
   public ItemListAdapter(){
       this.itemList= new ArrayList<>();
   }

   public interface OnFinishListener {
       void onItemClicked(ListItem lItem,int position );
   }
   public interface OnDeleteListener{
       void onDeleteClicked(ListItem listItem, int position);
   }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View itemView= layoutInflater.inflate(R.layout.list_layout,parent,false);
       return new ItemViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.ItemViewHolder holder, int position) {

       ListItem lItem= itemList.get(position);
       TextView itemTitle= holder.itemTitle;
       TextView itemDescription= holder.itemDescription;


       holder.finishedState.setChecked(lItem.isFinished());
       // holder.image.setImageURI(Uri.parse(lItem.getImageUrl()));

        itemTitle.setText(lItem.getName());
        itemDescription.setText(lItem.getDescription());

       if(lItem.isFinished()) {
           itemTitle.setPaintFlags(itemTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
           itemDescription.setPaintFlags(itemDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

       // holder.deleteButton.setOnClickListener(V-> deleteListener.onDeleteClicked(lItem,position));
        holder.itemView.setOnClickListener(v->finishListener.onItemClicked(lItem,position));

    }

    @Override
    public int getItemCount() {
        return itemList.size();

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        CheckBox finishedState;
        TextView itemTitle;
        TextView itemDescription;
      //  ImageView image;
        ImageButton deleteButton;

        public ItemViewHolder(View itemView){
            super(itemView);

            finishedState= itemView.findViewById(R.id.checkbox);
            itemTitle= itemView.findViewById(R.id.itemTitle);
            itemDescription= itemView.findViewById(R.id.itemDescription);
          //  image= itemView.findViewById(R.id.imageForItem);
            deleteButton= itemView.findViewById(R.id.delete);

        }

    }
}
