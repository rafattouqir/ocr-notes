package com.note;

import keendy.projects.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ListTabActivity extends TabActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_tab);
		
		TabHost tabHost = getTabHost();
		TabSpec spec;
		Intent intent;
		
		intent = new Intent(this, NotesListActivity.class);
		
		spec = tabHost.newTabSpec("Local Notes").setIndicator("Local Notes")
			.setContent(intent);
		tabHost.addTab(spec);
	}
}
