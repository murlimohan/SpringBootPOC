package com.bookMyShowExample.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookMyShowExample.demo.model.Screen;
import com.bookMyShowExample.demo.repositiory.ScreenRepo;
import com.bookMyShowExample.demo.service.ScreenService;

@Service
public class ScreenServiceImpl implements ScreenService{
	
	@Autowired
	ScreenRepo screenrepo;
	

	public List<Screen >getListofScreenByTheatreId(int id) {
		List<Screen> screenList=screenrepo.findByTheatre_TheatreId(id);
		return screenList;
	}

	public List<Screen> getAllScreen() {
		List<Screen> screenList=screenrepo.findAll();
		return screenList;
	}
}
