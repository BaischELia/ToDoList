package de.hdmstuttgart.todolist.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ListItem {

    @PrimaryKey(autoGenerate = true)

    public  int itemNumber ;

    private final String name;
    private final String description;
    private boolean finished; //TODO
    private final String imageUrl;

    public ListItem(String name, String description, boolean finished,String imageUrl ){
       // this.itemNumber=itemNumber;
        this.name= name;
        this.description= description;
        this.finished=finished;
        this.imageUrl= imageUrl;
    }
    public int getItemNumber(){
        return itemNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
