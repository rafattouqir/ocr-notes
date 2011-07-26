package com.note;

import keendy.projects.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.database.DatabaseAdapter;

/**
 * 
 * @author kevin
 *
 * TODO implement update note method
 */
public class NoteEditorActivity extends Activity {

  private static String TAG = "Note Editor";
  
  private static final int STATE_EDIT = 0;
  private static final int STATE_INSERT = 1;
  
  private EditText noteView;
  private String title = "Untitled";
  
  private int mState;
  
  private Cursor mCursor;
  private long _id;
  private String content;
  
  public static class PLUGEditText extends EditText {

	private Rect mRect;
	private Paint mPaint;

	/* Custom EditText with line underneath the text */
	public PLUGEditText(Context context, AttributeSet attrs) {
	  super(context, attrs);
	  
	  mRect = new Rect();
	  mPaint = new Paint();
	  mPaint.setStyle(Paint.Style.STROKE);
	  mPaint.setColor(Color.YELLOW);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
	  int count = getLineCount();
	  Rect r = mRect;
	  Paint paint = mPaint;

	  /* Drawing a line underneath the rows */
	  for (int i = 0; i < count; i++) {
		int baseline = getLineBounds(i, r);
		canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
	  }
	  super.onDraw(canvas);
	}
  } 
  
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	/** Get the intent (either insert new note or edit existing */
	final Intent intent = getIntent();
	String action = intent.getAction();

	/** Set current state depending on intent */
	if(Intent.ACTION_INSERT.equals(action)) {
	  mState = STATE_INSERT;
	} else if(Intent.ACTION_EDIT.equals(action)) {
	  mState = STATE_EDIT;
	  _id = intent.getLongExtra("_id", -1);
	  Log.i(TAG, "The id is: " + _id );
	  
	  DatabaseAdapter db = new DatabaseAdapter(this);
	  db.open();
	  mCursor = db.getNote(_id); 
	  if(mCursor.moveToFirst()) {
	    content = mCursor.getString(
			mCursor.getColumnIndex(DatabaseAdapter.KEY_NOTE_CONTENT));
	    title = mCursor.getString(
	    	mCursor.getColumnIndex(DatabaseAdapter.KEY_NOTE_TITLE));
	    Log.i(TAG, "Retrieving");
	  } else
		Log.i(TAG, "Nothing was retrieved");
	  mCursor.close();
	  db.close();
	}
	
	setContentView(R.layout.note_editor);
	
	noteView = (EditText) findViewById(R.id.note);
	if(mState == STATE_EDIT) {
	  noteView.setText(content);
	}	
  }
  
  @Override
  public void onDestroy() {
	super.onDestroy();
  }
  
  @Override
  public void onBackPressed() {
	switch(mState) {
	  case STATE_INSERT:
		Log.i(TAG, "created new note, exiting...");
		save(title, noteView.getText().toString());
		finish();
		break;
	  case STATE_EDIT:
		Log.i(TAG, "updated note, exiting...");
		save(_id, noteView.getText().toString(), title);
		finish();
		break;
	}
  }
  
  /** Create new note */
  private void save(String title, String content) {
	if(!content.equals(""))
	{
	  DatabaseAdapter database = new DatabaseAdapter(this);
	  database.open();
	  database.createNote(title, content);
	  Log.i(TAG, "Note created, it contained " + noteView.getText().toString());
	  database.close();
	}
  }
  
  /** Update note */ 
  private void save(long noteId, String content, String title) {
	  DatabaseAdapter database = new DatabaseAdapter(this);
	  database.open();
	  database.updateNote(noteId, title, content);
	  Log.i(TAG, "Note successfully updated!");
	  database.close();
  }
}