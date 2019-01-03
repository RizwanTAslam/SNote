package com.example.rizwanaslam.snote.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.rizwanaslam.snote.R;
import com.example.rizwanaslam.snote.callback.NoteEventListener;
import com.example.rizwanaslam.snote.model.Note;
import com.example.rizwanaslam.snote.utils.NoteUtils;

import java.util.ArrayList;

public class NotesAdapter extends  RecyclerView.Adapter<NotesAdapter.NoteHolder> {
    private Context context;
    private ArrayList<Note> notes;
    private NoteEventListener listener;

    public NotesAdapter(Context context, ArrayList<Note> note) {
        this.context = context;
        this.notes = note;
    }

    @Override
    public NoteHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(this.context).inflate(R.layout.note_layout,viewGroup,false);
        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder( NoteHolder noteHolder, int i) {
        final Note note = getNote(i);
        if(note != null){
            noteHolder.noteText.setText(note.getNoteText());
            noteHolder.noteDate.setText(NoteUtils.dateFromLong(note.getNoteDate()));

            noteHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNoteClick(note);
                }
            });
            noteHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNoteLongClick(note);
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return notes.size();
    }
    private Note getNote(int position)
    {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        TextView noteText,noteDate;

        public NoteHolder( View itemView) {
            super(itemView);
            noteText=itemView.findViewById(R.id.note_text);
            noteDate=itemView.findViewById(R.id.note_date);
        }
    }

    public void setListener(NoteEventListener listener)
    {
        this.listener = listener;
    }
}
