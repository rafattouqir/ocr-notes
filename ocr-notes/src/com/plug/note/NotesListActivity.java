package com.plug.note;

import java.util.List;

import keendy.projects.R;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.plug.database.DatabaseAdapter;
import com.plug.database.models.Note;
import com.plug.database.providers.NotesProvider;

public class NotesListActivity extends ListActivity {

  private static final String TAG = "List Activity: ";
  
  private Cursor mCursor;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		loadList();
  }
  
  protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	
		mCursor.moveToPosition(position);
		
		Intent mIntent = new Intent();
		mIntent.setAction(Intent.ACTION_EDIT);
		mIntent.putExtra("_id", 
			mCursor.getLong(mCursor.getColumnIndex(DatabaseAdapter.KEY_NOTE_ID)));
		mIntent.setClass(NotesListActivity.this, NoteEditorActivity.class);
		startActivity(mIntent);
  }
  
  public void onResume() {
		super.onResume();
		
		Log.i(TAG, "Resuming");
		loadList();
  }
  
  private void loadList() {
		DatabaseAdapter dbAdapter  = new DatabaseAdapter(this);
		dbAdapter.open();
  	
		mCursor = dbAdapter.getAllNotes();	
		NotesProvider provider = null;
		try {
  		provider = NotesProvider.getInstance(this);
  		List<Note> notes = provider.findAll();
  		
  		for(Note note : notes) {
  			Log.i(TAG, "------==== NOTE ====-------");
  			Log.i(TAG, note.getTitle());
  			Log.i(TAG, note.getContent());
  			Log.i(TAG, note.getCreatedAt());
  			Log.i(TAG, note.getUpdatedAt());
  		}
		} finally {
			provider.db().close();
		}
		startManagingCursor(mCursor);
	
		String[] column = {DatabaseAdapter.KEY_NOTE_TITLE};
		int[] to = {R.id.notes_title_list};
		
		ListAdapter list = new SimpleCursorAdapter(this,
			R.layout.notes_list, 
			mCursor, 
			column, 
			to);
		
		this.setListAdapter(list);
		
		dbAdapter.close();
  }
}
