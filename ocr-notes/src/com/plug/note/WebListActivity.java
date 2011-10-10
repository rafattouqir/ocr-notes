package com.plug.note;

import java.util.HashMap;

import keendy.projects.R;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;

public class WebListActivity extends ListActivity {

	private Cursor mCursor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	/*
	 * TODO Create a HashMap to handle JSON arrays
	 * Alternately, create a loop that will handle the JSON array
	 */
	public void handleJSON(String JSON) {
		HashMap<String, String> myJSONarray = 
			new HashMap<String, String>() {
	};
		
	}
}
