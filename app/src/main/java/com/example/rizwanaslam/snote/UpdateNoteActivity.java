package com.example.rizwanaslam.snote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;

import com.example.rizwanaslam.snote.adapters.NotesAdapter;
import com.example.rizwanaslam.snote.db.NotesDB;
import com.example.rizwanaslam.snote.db.NotesDao;
import com.example.rizwanaslam.snote.model.Note;

import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {
    private EditText inputnote;
    private NotesDao dao;
    private Note temp;


    public static final String NOTE_EXTRA_Key = "note_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_update_note);
        inputnote=findViewById(R.id.input_note);
        dao=NotesDB.getInstance(this).notesDao();
        if(getIntent().getExtras() != null){
          int id=getIntent().getExtras().getInt(NOTE_EXTRA_Key,0);
            temp=dao.getNoteById(id);
            inputnote.setText(temp.getNoteText());
        }
        //else
          //  temp = new Note();


        //this.recyclerView.setAdapter(this.adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
                if(id==R.id.save_note)
                  onSaveNote();
                if(id==R.id.delete_note)
                    onDelete();
        return super.onOptionsItemSelected(item);
    }

    private void onDelete() {
        dao.deleteNote(temp);
        finish();
    }

    private void onSaveNote() {
        String text=inputnote.getText().toString();
        if(!text.isEmpty())
            {
            long date=new Date().getTime();
            if(temp==null) {
                temp = new Note(text, date);
                //if(temp.getId()== -1)
                dao.insertNote(temp);
            }
            else {
                temp.setNoteText(text);
                temp.setNoteDate(date);
                dao.updateNote(temp);
            }
            finish();
        }
    }
}
