package com.bookMyShowExample.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookMyShowExample.demo.model.Theatre;
import com.bookMyShowExample.demo.repositiory.TheatreRepo;
import com.bookMyShowExample.demo.service.TheatreService;

@Service
public class TheatreServiceImpl implements TheatreService {
	
	@Autowired
	TheatreRepo theatreRepo;

	public List<Theatre> getAllTheatres(Integer pageNo, Integer pageSize) {
		
		Pageable paging= PageRequest.of(pageNo, pageSize);
		Page<Theatre> pagedResult=theatreRepo.findAll(paging);
		if(pagedResult != null && pagedResult.hasContent()) {
			return pagedResult.getContent();
		}
		else {
			return new ArrayList<>();
		}
	}
	
	public List<Theatre> getAllTheatre() {
		List<Theatre> theatreData = theatreRepo.findAll();
		return theatreData;
	}

	@Override
	public Theatre findOneTheatre(Integer id) {
		Optional<Theatre> theatreData = theatreRepo.findById(id);
		if(theatreData.isPresent()) {
		return theatreData.get();
		}
		else {
			return null;
		}
	}
	
	

}
