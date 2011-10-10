package com.plug;

import android.app.Application;

public class PlugApplication extends Application {
	
	private String currentUser;

	private String getCurrentUser() {
		return currentUser;
	}

	private void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

}
