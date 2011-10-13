package com.plug.database.models;

import java.sql.Timestamp;

import com.google.gson.annotations.SerializedName;

public class Note {

	@SerializedName("id") 								private long id;
	@SerializedName("title") 							private String title;
	@SerializedName("content") 						private String content;
	@SerializedName("created_at") 				private Timestamp created;
	@SerializedName("updated_at")					private Timestamp updated;	
	
	private boolean isUploaded = false;
	
	public Note(String title, String content, Timestamp timestamp) {
	  this.title = title;
	  this.content = content;
	  this.created = timestamp;
	  this.updated = timestamp;
  }

	public Note() {
  }

	public Note(long id, String title, String content, Timestamp timestamp) {
	  this.id = id;
	  this.title = title;
	  this.content = content;
	  this.created = timestamp;
	  this.updated = timestamp;
  }

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public boolean isUploaded() {
  	return isUploaded;
  }

	public void setUploaded(boolean isUploaded) {
  	this.isUploaded = isUploaded;
  }
	
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	public Timestamp getCreated() {
		return created;
	}
	
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	
	public Timestamp getUpdated() {
		return updated;
	}
	
	/** Custom methods */
	public String getCreatedAt() {
		return created.toGMTString();
	}
	
	public String getUpdatedAt() {
		return updated.toGMTString();
	}
}
