package de.hdmstuttgart.todolist;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.hdmstuttgart.todolist.model.ItemsRepository;
import de.hdmstuttgart.todolist.model.ListItem;

public class ListFragment extends Fragment {
        public ListFragment(){
            super(R.layout.list_fragment);
        }

        private ItemListAdapter adapter;
        private ListViewModel LViewModel;
        private  Context context;

        @Override
        public void onAttach(@NonNull Context context) {
                super.onAttach(context);
                this.context=context;
        }


        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
                super.onViewCreated(view, savedInstanceState);

                FloatingActionButton fab= getActivity().findViewById(R.id.fab);
                Button deleteAll= getActivity().findViewById(R.id.deleteAll);
                FragmentManager fragmentManager= getActivity().getSupportFragmentManager();


                RecyclerView recyclerView= getActivity().findViewById(R.id.itemRecyclerView);

                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setHasFixedSize(true);

                LViewModel= new ViewModelProvider(this).get(ListViewModel.class);

                adapter= new ItemListAdapter( context,new ArrayList<>(), ((listItem, position) -> {}));


                LViewModel.getALlItems().observe(getViewLifecycleOwner(), itemList -> {

                        adapter= new ItemListAdapter(context, itemList,((lItem, position) -> {
                        ItemListAdapter adapter=(ItemListAdapter)recyclerView.getAdapter();

                        if(itemList==null)return;

                        LViewModel.updateState(lItem,!lItem.isFinished());

                        adapter.notifyItemChanged(position);

                        }));




                        recyclerView.setAdapter(adapter);

                });


                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();



                deleteAll.setOnClickListener(v->{
                        LViewModel.deleteALl();
                        adapter.notifyDataSetChanged();
                });


                fab.setOnClickListener(view2 -> {
                        fragmentManager.beginTransaction() //change Fragment
                                .replace(R.id.fragment_container_view, AddItemFragment.class,null)
                                .setReorderingAllowed(true)
                                .addToBackStack("name")
                                .commit();
                        fab.hide();
                });
        }

        @Override
        public void onResume() {
                super.onResume();
                adapter.notifyDataSetChanged();
        }

        public static ListFragment newInstance() {
                return new ListFragment();
        }
}
