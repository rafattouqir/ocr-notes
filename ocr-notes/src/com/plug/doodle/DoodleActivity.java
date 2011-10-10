package com.plug.doodle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.plug.main.HomeActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Toast;

import keendy.projects.R;

public class DoodleActivity extends Activity{    

	private final static String TAG = DoodleActivity.class.getSimpleName();

	private Bitmap bitmap;

	DoodleView doodleView;

    public void onCreate(Bundle savedInstanceState) {
    	
    	Log.d(TAG, "Starting Activity");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.doodle);
        doodleView = (DoodleView) findViewById(R.id.doodle_view);
    }

    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.doodle_menu, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.doodle_save:
	    	
	    	bitmap = doodleView.getBitmap();
	    	SaveAsImage.writeAsJPG(DoodleActivity.this, bitmap, "doodle");
	    	
	    	Toast.makeText(this, "Saved successfully.", Toast.LENGTH_LONG).show();
	    	
	    	this.finish();
	    	startActivity(new Intent(DoodleActivity.this, HomeActivity.class));
	    	//saveDoodle(doodleView);
	        return true;
	    case R.id.doodle_clear:
	    	doodleView.clear();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public void onBackPressed() {
		this.finish();
		super.onBackPressed();
	}
	
//	private void saveDoodle(View v){
//		v.setDrawingCacheEnabled(true);
//		v.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//		v.layout(0, 0, v.getWidth(), v.getHeight());
//		v.buildDrawingCache(true);
//
//		Bitmap bm = Bitmap.createBitmap(v.getDrawingCache());
//		v.setDrawingCacheEnabled(false);
//		
//		if (bm != null) {
//		    try {
//		        String path = Environment.getExternalStorageDirectory().toString();
//		        OutputStream fOut = null;
//		        File file = new File(path, "doodletest.jpg");
//		        fOut = new FileOutputStream(file);
//		        bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
//		        fOut.flush();
//		        fOut.close();
//		        Log.e("ImagePath", "Image Path : " + MediaStore.Images.Media.insertImage
//		        		( getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName()));
//		    }
//		    catch (Exception e) {
//		        e.printStackTrace();
//		    }
//		}
//	}
}