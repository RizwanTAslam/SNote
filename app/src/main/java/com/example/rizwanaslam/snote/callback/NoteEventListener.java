package com.example.rizwanaslam.snote.callback;

import com.example.rizwanaslam.snote.model.Note;

public interface NoteEventListener {

    void onNoteClick(Note note);

    void onNoteLongClick(Note note);


}
