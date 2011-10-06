package com.plug.main;

import keendy.projects.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.plug.camera.CameraActivity;
import com.plug.doodle.DoodleActivity;
import com.plug.note.ListTabActivity;
import com.plug.note.NoteEditorActivity;

/**
 * HomeActivity that handles all key presses during home view
 * 
 * TODO Implement all onClick features
 */

public class HomeActivity extends Activity{
	//implements OnClickListener {

	/* Called when the activity is first created. */

	private Button create;
	private Button doodle;
	private Button allNotes;
	private Button tags; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		init();
	}

	private void init() {
		/* Initialize Buttons */
		create = (Button) findViewById(R.id.home_createnote);
		doodle =  (Button) findViewById(R.id.home_doodleNote);
		allNotes = (Button) findViewById(R.id.home_notebooks);
		tags = (Button) findViewById(R.id.home_tags);
	}

//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.home_createnote:
//			//startActivity(new Intent(HomeActivity.this, CameraActivity.class));
//			Intent mIntent = new Intent();
//			mIntent.setAction(Intent.ACTION_INSERT);
//			mIntent.setClass(HomeActivity.this, NoteEditorActivity.class);
//			startActivity(mIntent);
//			break;
//		case R.id.home_doodleNote:
//			startActivity(new Intent(HomeActivity.this, DoodleActivity.class));
//			break;
//		case R.id.home_notebooks:
//			break;
//		case R.id.home_tags:
//			break;
//		
//		case R.id.home_about:
//			startActivity(new Intent(HomeActivity.this, AboutActivity.class));
//			break;
//		case R.id.home_myNotebook:
//			startActivity(new Intent(HomeActivity.this, ListTabActivity.class));
//			break;
//		case R.id.home_writeNote:
////			Intent mIntent = new Intent();
////			mIntent.setAction(Intent.ACTION_INSERT);
////			mIntent.setClass(HomeActivity.this, NoteEditorActivity.class);
////			startActivity(mIntent);
//			break;
//		}
//	}
	public void onClickCreate(View view) {
		Intent mIntent = new Intent();
		mIntent.setAction(Intent.ACTION_INSERT);
		mIntent.setClass(HomeActivity.this, NoteEditorActivity.class);
		startActivity(mIntent);
	}
	
	public void onClickNotebooks(View v){
	}
	
	public void onClickDoodle(View v){
		HomeActivity.this.finish();
		startActivity(new Intent(HomeActivity.this, DoodleActivity.class));
	}
	
	public void onClickTags(View v){
		
	}
}