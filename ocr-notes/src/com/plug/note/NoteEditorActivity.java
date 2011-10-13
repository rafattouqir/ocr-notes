package com.plug.note;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import keendy.projects.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.plug.PlugApplication;
import com.plug.database.DatabaseAdapter;
import com.plug.database.models.Note;

/**
 * 
 * @author kevin
 *
 * TODO implement menus for editing the title, discarding etc
 */
public class NoteEditorActivity extends Activity implements OnClickListener{

	private PlugApplication application;
	
  private static String TAG = "Note Editor";
  
  /**  Different states this note editor may enter */
  private static final int STATE_EDIT = 0;
  private static final int STATE_INSERT = 1;

  private static final int PIC_RESULT = 0;
  
  /** our variables worth noting :) */
  private EditText noteView;
  private String title = "Untitled";
  
  /** Our current state */
  private int mState;
  
  /** SQLite variables or projections */
  private Cursor mCursor;
  private long _id;
  private String content;
  
  NoteAddTitle addDialog;
  
  public static class PLUGEditText extends EditText {

  /** variables for our custom text editor with awesome underline churva */
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
		application = (PlugApplication) getApplicationContext();
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
		  } else {
				Log.i(TAG, "Nothing was retrieved");
		  }
		  mCursor.close();
		  db.close();
		}
	
		setContentView(R.layout.note_editor);
		
		noteView = (EditText) findViewById(R.id.note);
		if(mState == STATE_EDIT) {
		  noteView.setText(content);
		}	
  }
  
	/*
   * TODO implement options menu depending on intent
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
  	super.onCreateOptionsMenu(menu);

    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.title_menu, menu);
     
	
  	if(mState == STATE_INSERT) {
  		
  		
  	} else {
  		
  		
  	}
  	
		return true;
  }
  
  @Override
	public boolean onOptionsItemSelected(MenuItem item) {
  	switch (item.getItemId()) {
      case R.id.add_menu:
      	  addDialog = new NoteAddTitle(this);
      	  Button addBttn = (Button) addDialog.findViewById(R.id.add_bttn);
      	  addBttn.setOnClickListener(this);
      	  addDialog.show();
          return true;
      case R.id.cancel_menu:
      	
          return true;
          
      case R.id.camera_menu:
    	  callCamera();
      default:
          return super.onOptionsItemSelected(item);
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
//				uploadNote(save(title, noteView.getText().toString()),
//						title, noteView.getText().toString());
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
  private long save(String title, String content) {
		long id = -1;
		if(!content.equals("")) {
			java.util.Date date = new java.util.Date(System.currentTimeMillis()-(24*3600*1000)); 
			Timestamp timestamp = new Timestamp(date.getTime());
			Note note = new Note(title, content, timestamp);
			
			try {
  			application.getNotesProvider().store(note);
  			for ( Note currentNote : application.getNotesProvider().findAll()) {
  				Log.i(TAG, currentNote.getTitle());
   				Log.i(TAG, currentNote.getContent()); 				
   				Log.i(TAG, currentNote.getCreatedAt());  				
  				Log.i(TAG, currentNote.getUpdatedAt());
  			}
			} finally {
				
			}
			
		  DatabaseAdapter database = new DatabaseAdapter(this);
		  database.open();
		  id = database.createNote(title, content);
		  Log.i(TAG, "Note created, it contained " + noteView.getText().toString());
		  database.close();
		}
		return id;
  }
  
  /** Update note */ 
  private void save(long noteId, String content, String title) {
	  DatabaseAdapter database = new DatabaseAdapter(this);
	  database.open();
	  database.updateNote(noteId, title, content);
	  Log.i(TAG, "Note successfully updated!");
	  database.close();
  }
  
  private void uploadNote(long id, String title, String content) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://192.168.0.101/notes"); //change heroku
		
		JSONObject holder = new JSONObject();
		JSONObject jsonO = new JSONObject();
		
		try{
		  jsonO.put("title", title);
		  jsonO.put("content", content);
		  
		  holder.put("note", jsonO);
		  
		  StringEntity se = new StringEntity(holder.toString());
		  post.setEntity(se);
		  post.setHeader("Content-type","application/json");
		  
		  Log.i(TAG, "Finished setting up JSON Object");
		  
		} catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		  Log.e(TAG, ""+e);
		} catch (JSONException js) {
		  js.printStackTrace();
		  Log.e(TAG, ""+js);
		}
		
		HttpResponse response = null;
		
		try {
		  Log.i(TAG, "Executing POST request");
		  response = client.execute(post);
	  } catch (ClientProtocolException e) {
		  e.printStackTrace();
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
	    
	  HttpEntity entity = response.getEntity();
	    
	  if(entity != null) {
	    try {
	    	Log.i(TAG, "Successfully posted on the website!");
		    entity.consumeContent();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
	  }
  }

	@Override
	public void onClick(View v) {
		EditText title_text= (EditText) addDialog.findViewById(R.id.add_title);
		title = title_text.getText().toString();
		addDialog.dismiss();
		
	}
	
	public void callCamera(){
		Log.d(TAG, "Starting camera...");
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		startActivityForResult(intent, PIC_RESULT);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PIC_RESULT)
			if (resultCode == RESULT_OK) 
			{
			
			Bitmap x = (Bitmap) data.getExtras().get("data");
			//((ImageView)findViewById(R.id.pictureView)).setImageBitmap(x);
            ContentValues values = new ContentValues();
		    values.put(Images.Media.TITLE, "title");
		    values.put(Images.Media.BUCKET_ID, "test");
		    values.put(Images.Media.DESCRIPTION, "test Image taken");
		    values.put(Images.Media.MIME_TYPE, "image/jpeg");
		    Uri uri = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
		    OutputStream outstream;
		    try {
		    	outstream = getContentResolver().openOutputStream(uri);

		    	x.compress(Bitmap.CompressFormat.JPEG, 70, outstream);
		    	outstream.close();
            } catch (FileNotFoundException e) {
                    e.printStackTrace();
            }catch (IOException e){
                    e.printStackTrace();
            }
            }
		else if (resultCode == Activity.RESULT_CANCELED) {
			
		}
//		if (requestCode == PIC_RESULT)
//			if (resultCode == RESULT_OK) 
//			{
////				Bundle bundle = data.getExtras();				
////				photoBitmap = (Bitmap) bundle.get("data");
////				startSignature();
//				
//				
//			}
//			else if (resultCode == Activity.RESULT_CANCELED) {
//				this.finish();
//			}
	}
}