package com.plug.database.models;

import android.text.format.Time;

import com.google.gson.annotations.SerializedName;

public class Note {

	@SerializedName("id") 								private long id;
	@SerializedName("title") 							private String title;
	@SerializedName("content") 						private String content;
	@SerializedName("created_at") 				private Time created;
	@SerializedName("updated_at")					private Time updated;	
	
	private boolean isUploaded = false;
	
	public Note(String title, String content, Time created, Time updated) {
	  this.title = title;
	  this.content = content;
	  this.created = created;
	  this.updated = updated;
  }

	public Note() {
  }

	public Note(long id, String title, String content, Time created, Time updated) {
	  this.id = id;
	  this.title = title;
	  this.content = content;
	  this.created = created;
	  this.updated = updated;
  }

	private String getTitle() {
		return title;
	}
	
	private void setTitle(String title) {
		this.title = title;
	}
	
	private String getContent() {
		return content;
	}
	
	private void setContent(String content) {
		this.content = content;
	}
	
	private long getId() {
		return id;
	}
	
	private void setId(long id) {
		this.id = id;
	}

	public boolean isUploaded() {
  	return isUploaded;
  }

	public void setUploaded(boolean isUploaded) {
  	this.isUploaded = isUploaded;
  }
	
	public void setCreated(Time created) {
		this.created = created;
	}
	
	public Time getCreated() {
		return created;
	}
	
	public void setUpdated(Time updated) {
		this.updated = updated;
	}
	
	public Time getUpdated() {
		return updated;
	}
}
