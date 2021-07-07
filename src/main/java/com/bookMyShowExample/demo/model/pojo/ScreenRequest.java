package com.bookMyShowExample.demo.model.pojo;

import com.bookMyShowExample.demo.model.Theatre;

public class ScreenRequest {

	private int screenId;
	private String screenName;
	private Theatre theatre;

	public ScreenRequest() {
		super();
	}

	public ScreenRequest(int screenId, String screenName,Theatre theatre) {
		super();
		this.screenId = screenId;
		this.screenName = screenName;
		this.theatre = theatre;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

}
