package com.plug;

import android.app.Application;

import com.plug.database.Db4oHelper;
import com.plug.database.models.Note;
import com.plug.database.providers.NotesProvider;

public class PlugApplication extends Application {
			
	private String currentUser;
	private Note currentNote;
	
	private Db4oHelper dbHelper = new Db4oHelper(this);
	private NotesProvider notesProvider = NotesProvider.getInstance(this);

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	private Note getCurrentNote() {
		return currentNote;
	}

	private void setCurrentNote(Note currentNote) {
		this.currentNote = currentNote;
	}

	public NotesProvider getNotesProvider() {
  	return notesProvider;
  }

}
