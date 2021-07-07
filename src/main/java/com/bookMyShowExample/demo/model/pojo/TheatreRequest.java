package com.bookMyShowExample.demo.model.pojo;

public class TheatreRequest {

	private int theatreId;
	private String theatreName;
	private String cityName;

	public TheatreRequest() {
		super();
	}

	public TheatreRequest(int theatreId, String theatreName, String cityName) {
		super();
		this.theatreId = theatreId;
		this.theatreName = theatreName;
		this.cityName = cityName;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
