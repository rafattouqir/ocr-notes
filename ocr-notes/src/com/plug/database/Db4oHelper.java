package com.plug.database;

import java.io.IOException;

import android.content.Context;
import android.util.Log;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.plug.database.models.Note;

public class Db4oHelper {
	
	private static final String TAG = "Db4oHelper Class";
	
	private static final String DATABASE_NAME = "plug.db4o";
	
	private static ObjectContainer oc = null;
	private Context context;
	
	private ObjectContainer db() {
		try{
			if( oc == null || oc.ext().isClosed()){
				oc = Db4oEmbedded.openFile(dbConfig(), DATABASE_NAME);
			}
			return oc;
		} catch (Exception e ) {
			Log.e(TAG, e.toString());
			return null;
		}
	}
	
	private EmbeddedConfiguration dbConfig() throws IOException {
    EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
    configuration.common().objectClass(Note.class).objectField("id").indexed(true);
    configuration.common().objectClass(Note.class).cascadeOnUpdate(true);
    configuration.common().objectClass(Note.class).cascadeOnActivate(true);
    return configuration;
  }
	
	public String db4oDBFullPath(Context ctx) {
		return ctx.getDir("data", 0) + "/" + "android.db4o";
	}
}
