package com.bookMyShowExample.demo.service;

import java.util.List;

import com.bookMyShowExample.demo.model.Theatre;

public interface TheatreService {

	public List<Theatre> getAllTheatres(Integer pageNo, Integer pageSize);

	public List<Theatre> getAllTheatre();
	
	public Theatre findOneTheatre(Integer id);
}
