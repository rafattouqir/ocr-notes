package com.plug.database.models;

public class Note {

	private String title;
	private String content;
		
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
}
