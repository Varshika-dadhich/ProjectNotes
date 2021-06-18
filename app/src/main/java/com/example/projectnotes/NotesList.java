package com.example.projectnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class
NotesList extends AppCompatActivity {
    RecyclerView list;
    Adapter adapter;
    Database Database;
    ArrayList<Modelclass> data = new ArrayList<>();
    Intent intent = getIntent();
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        img = (ImageView) findViewById(R.id.addnotes);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Description.class);
                startActivity(intent);
            }
        });
        Database = new Database(this);
        ArrayList<Modelclass> allNotes = Database.getAllNotes();
        list = (RecyclerView) findViewById(R.id.rcl);

    }

    private void displayList(ArrayList<Modelclass> allNotes) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        list.setLayoutManager(linearLayoutManager);
        list.setItemAnimator(new DefaultItemAnimator());
        adapter = new Adapter(this,allNotes);
        list.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item=menu.findItem(R.id.wordsearch);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
   protected void onResume() {
        super.onResume();
        ArrayList<Modelclass> getAllNotes = Database.getAllNotes();
            displayList(getAllNotes);
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}


