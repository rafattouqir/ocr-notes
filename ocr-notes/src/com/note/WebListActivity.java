package com.note;

import java.util.HashMap;

import keendy.projects.R;
import android.app.Activity;
import android.os.Bundle;

public class WebListActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_list);
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
