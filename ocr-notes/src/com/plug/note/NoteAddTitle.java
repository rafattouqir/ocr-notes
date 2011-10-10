package com.plug.note;

import com.plug.database.DatabaseAdapter;

import keendy.projects.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteAddTitle extends Dialog{


	
	public NoteAddTitle(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.title_dialog);
	}

	

	

	
}

