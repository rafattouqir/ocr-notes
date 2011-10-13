package com.plug.database;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import android.content.Context;
import android.util.Log;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.plug.database.models.Note;

public class Db4oHelper {
	
	private static final String TAG = "Db4oHelper Class";
	
	private static final String DATABASE_NAME = "plug.db4o";
	
	private static EmbeddedObjectContainer oc = null;
	private Context context;
	
	public Db4oHelper(Context context) {
		this.context = context;
	}
	
	public ObjectContainer db() {
		try{
			if( oc == null || oc.ext().isClosed()){
				oc = Db4oEmbedded.openFile(dbConfig(), context.getDir("databases", 0)+"/"+DATABASE_NAME);
			}
			return oc;
		} catch (Exception e ) {
			Log.e(TAG, e.toString());
			return null;
		}
	}
	
	private EmbeddedConfiguration dbConfig() throws IOException {
    EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
//    configuration.common().objectClass(Note.class).objectField("id").indexed(true);
//    configuration.common().objectClass(Note.class).cascadeOnUpdate(true);
//    configuration.common().objectClass(Note.class).cascadeOnActivate(true);
    configuration.common().objectClass(Note.class).storeTransientFields(true);
    configuration.common().objectClass(Date.class).storeTransientFields(true);   
    configuration.common().objectClass(Timestamp.class).storeTransientFields(true);
    return configuration;
  }
	
	public String db4oDBFullPath(Context ctx) {
		return ctx.getDir("databases", 0) + "/" + DATABASE_NAME;
	}
}
