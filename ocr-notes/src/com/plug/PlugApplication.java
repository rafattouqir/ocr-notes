package com.plug;

import java.util.List;

import android.app.Application;

import com.plug.database.models.Note;

public class PlugApplication extends Application {
			
	private String currentUser;
	private Note currentNote;

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
	
}
