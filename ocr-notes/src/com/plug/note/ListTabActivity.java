package com.plug.note;

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
		TabSpec spec2;
		Intent localListIntent;
		Intent webListIntent;
		
		localListIntent = new Intent(this, NotesListActivity.class);
		webListIntent = new Intent(this, WebListActivity.class);
		
		spec = tabHost.newTabSpec("Local Notes").setIndicator("Local Notes")
			.setContent(localListIntent);
		tabHost.addTab(spec);
		
		spec2 = tabHost.newTabSpec("Web Notes").setIndicator("Web Notes")
			.setContent(webListIntent);
		tabHost.addTab(spec2);
	}

	
	
}
