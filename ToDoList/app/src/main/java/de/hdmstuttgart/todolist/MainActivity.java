package de.hdmstuttgart.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab= findViewById(R.id.fab);
        FragmentManager fragmentManager= getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, ListFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
    }
}
