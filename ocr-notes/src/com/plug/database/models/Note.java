package com.plug.database.models;

import com.google.gson.annotations.SerializedName;

public class Note {

	@SerializedName("id") 			private long id;
	@SerializedName("title") 		private String title;
	@SerializedName("content") 	private String content;
		
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
	
}
