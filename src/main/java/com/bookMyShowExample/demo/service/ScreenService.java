package com.bookMyShowExample.demo.service;

import java.util.List;

import com.bookMyShowExample.demo.model.Screen;

public interface ScreenService {   
	public List<Screen> getListofScreenByTheatreId(int id);
	
	public List<Screen> getAllScreen();
}
