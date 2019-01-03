package com.example.rizwanaslam.snote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import com.example.rizwanaslam.snote.adapters.NotesAdapter;
import com.example.rizwanaslam.snote.callback.NoteEventListener;
import com.example.rizwanaslam.snote.db.NotesDB;
import com.example.rizwanaslam.snote.db.NotesDao;
import com.example.rizwanaslam.snote.model.Note;
import java.util.ArrayList;
import java.util.List;

import static com.example.rizwanaslam.snote.UpdateNoteActivity.NOTE_EXTRA_Key;

public class MainActivity extends AppCompatActivity implements NoteEventListener {

    private RecyclerView recyclerView;
    private ArrayList<Note> notes;
    private NotesAdapter adapter;
    private NotesDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNewNote();
                }
        });
        dao=NotesDB.getInstance(this).notesDao();

    }

    private void onAddNewNote() {

      startActivity(new Intent(this,UpdateNoteActivity.class));
    }

    private void loadNotes() {
        this.notes=new ArrayList<>();
        List<Note> lists=dao.getNotes();
        this.notes.addAll(lists);
        this.adapter =new NotesAdapter(this,this.notes);
        this.adapter.setListener(this);
        this.recyclerView.setAdapter(this.adapter);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    protected void onResume()
    {
        super.onResume();
        loadNotes();
    }


    @Override
    public void onNoteClick(Note note) {

   }

    @Override
    public void onNoteLongClick(Note note)
        {
        Intent edit=new Intent(this,UpdateNoteActivity.class);
        edit.putExtra(NOTE_EXTRA_Key,note.getId());
        startActivity(edit);
        }
}
