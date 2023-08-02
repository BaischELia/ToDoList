package de.hdmstuttgart.todolist;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdmstuttgart.todolist.model.ListItem;


public class AddItemFragment extends Fragment {
    public static final int PICK_IMAGE = 1;
private Uri imageUri;

    EditText itemName;
    private ImageView itemImage;
    private ListViewModel LViewmodel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_item_fragment, container, false);

        itemImage = view.findViewById(R.id.Image);
        Button addImageButton = view.findViewById(R.id.addImageButton);
        Button exitButton = view.findViewById(R.id.exit);
        Button saveButton= view.findViewById(R.id.save);
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        itemName= view.findViewById(R.id.itemName);
        EditText itemDescription= view.findViewById(R.id.itemDescription);

        LViewmodel= new ViewModelProvider(this).get(ListViewModel.class);

        exitButton.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().popBackStack();
            fab.show();

        });

        addImageButton.setOnClickListener(v -> {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_IMAGE);
        });

        saveButton.setOnClickListener(v -> {
            String itemNameInput= itemName.getText().toString();
            String itemDescInput= itemDescription.getText().toString();

            if(itemNameInput.length()==0){
                Toast.makeText(getContext(),getString(R.string.empty_item_name), Toast.LENGTH_LONG).show();
                itemName.requestFocus();
            }
            else {

                LViewmodel.insert(new ListItem(itemNameInput, itemDescInput, false, "URI"));

                fab.show();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });






        return view;
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            itemImage.setImageURI(imageUri);
        }
    }

}
