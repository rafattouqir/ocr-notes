package com.plug.main;

import keendy.projects.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.plug.doodle.DoodleActivity;
import com.plug.note.ListTabActivity;
import com.plug.note.NoteEditorActivity;

/**
 * HomeActivity that handles all key presses during home view
 * 
 * TODO Implement all onClick features
 */

public class HomeActivity extends Activity implements OnClickListener {

	/* Called when the activity is first created. */

	private Button create;
	private Button doodle;
	private Button notebooks;
	private Button allnotes;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		init();
	}

	/* Initialize Buttons */
	private void init() {
		create = (Button) findViewById(R.id.home_createnote);
		create.setOnClickListener(this);
		doodle =  (Button) findViewById(R.id.home_doodleNote);
		doodle.setOnClickListener(this);
		notebooks = (Button) findViewById(R.id.home_notebooks);
		notebooks.setOnClickListener(this);
		allnotes = (Button) findViewById(R.id.home_allnotes);
		allnotes.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
  		case R.id.home_createnote:
  			Intent mIntent = new Intent();
  			mIntent.setAction(Intent.ACTION_INSERT);
  			mIntent.setClass(HomeActivity.this, NoteEditorActivity.class);
  			startActivity(mIntent);
  			break;
  		case R.id.home_doodleNote:
  			startActivity(new Intent(HomeActivity.this, DoodleActivity.class));
  			break;
  		case R.id.home_allnotes:
  			startActivity(new Intent(HomeActivity.this, ListTabActivity.class));
  			break;
		}
	}


	
//	public void onClickCreate(View view) {
//		Intent mIntent = new Intent();
//		mIntent.setAction(Intent.ACTION_INSERT);
//		mIntent.setClass(HomeActivity.this, NoteEditorActivity.class);
//		startActivity(mIntent);
//	}
//	
//	public void onClickNotebooks(View v){
//	}
//	
//	public void onClickDoodle(View v){
////		HomeActivity.this.finish();
//		startActivity(new Intent(HomeActivity.this, DoodleActivity.class));
//	}
//	
//	public void onClickAllnotes(View v){
////		HomeActivity.this.finish();
//		startActivity(new Intent(HomeActivity.this, ListTabActivity.class));
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater inflater = getMenuInflater();
//	    inflater.inflate(R.menu.home_menu, menu);
//	    return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch(item.getItemId()){
//		case R.id.refresh:
//			return true;
//		default:
//	        return super.onOptionsItemSelected(item);
//		}
//	}
	
	
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater inflater = getMenuInflater();
//	    inflater.inflate(R.menu.home_menu, menu);
//	    return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//	    // Handle item selection
//	    switch (item.getItemId()) {
//	    case R.id.refresh:
//	    	
//	    	return true;
//	    default:
//	        return super.onOptionsItemSelected(item);
//	    }
//	}
}