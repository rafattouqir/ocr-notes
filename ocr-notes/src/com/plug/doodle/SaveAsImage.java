package com.plug.doodle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;

public  class SaveAsImage {
	private static final String TAG = SaveAsImage.class.getSimpleName();


	public static boolean writeAsJPG( Context context, Bitmap bitmap, String filename){
		int quality=100;
		
		filename = filename +".jpg";
		Log.d("WRITING", "writing image: " + filename);
		FileOutputStream fos = null;
		try {
			fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d(TAG, "file not found");
			return false;
		}
		bitmap.compress(CompressFormat.JPEG, quality, fos);
		Log.d(TAG, "finished writing");
		
		//bos.flush();
		//bos.close();	
		try {
			fos.flush();
			fos.close();
		} catch (IOException e) {
			Log.d(TAG, "error closing");
			e.printStackTrace();
		}
//		ExifInterface exif = new ExifInterface(file.getAbsolutePath());
//		exif.setAttribute(ExifInterface.TAG_DATETIME, new TimeStamp().getDate());
//		exif.saveAttributes();
		Log.d(TAG, "Saved successful");
	

		return true;
	}
	
	
	

}
