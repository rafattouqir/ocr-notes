package com.database;

public class DBSchema {
  
  /* Columns for Subjects Table */
  public static final String KEY_TABLE_ID = "_id";
  public static final String KEY_TABLE_TITLE = "title";
  
  /* Columns for Notes Table  */
  public static final String KEY_NOTE_ID = "_id";
  public static final String KEY_NOTE_TITLE = "title";
  public static final String KEY_NOTE_CONTENT = "content";
  public static final String KEY_NOTE_SUBJECT = "subject_id";
  public static final String DATABASE_NAME = "plug_database";
  public static final String DATABASE_TABLE_NOTE = "notes";
  public static final String DATABASE_TABLE_SUBJECT = "subjects";
  private static final int DATABASE_VERSION = 1;
 
}
