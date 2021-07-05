package com.bookMyShowExample.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookMyShowExample.demo.model.Theatre;
import com.bookMyShowExample.demo.repositiory.TheatreRepo;
import com.bookMyShowExample.demo.service.TheatreService;

@RestController
@RequestMapping("/")
public class TheatreController {

	@Autowired
	TheatreRepo theatrerepo;

	@Autowired
	TheatreService theatreservice;

	@PostMapping("admin/AddTheatre")
	public ResponseEntity<Theatre> createTheatre(@RequestBody Theatre theatre) {
		try {
			Theatre theatreEntryData = theatrerepo
					.save(new Theatre(theatre.getTheatreId(), theatre.getCityName(), theatre.getTheatreName()));
			return new ResponseEntity<>(theatreEntryData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("user/Theatre/{id}")
	public ResponseEntity<Theatre> getTheatreById(@PathVariable("id") int id) {
		Theatre theatreData = theatreservice.findOneTheatre(id);

		if (theatreData != null) { 
			Link ordersLink=linkTo(methodOn(this.getClass()).getAllTheatre()).withRel("all-theatres");
			theatreData.add(ordersLink);
			return new ResponseEntity<>(theatreData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("user/Theatres/WithPagination")
	public ResponseEntity<List<Theatre>> getAllTheatreWithPagination(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "2") Integer pageSize) {
		List<Theatre> theatreData = theatreservice.getAllTheatres(pageNo, pageSize);

		if (!theatreData.isEmpty()) {
			return new ResponseEntity<>(theatreData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("user/Theatres")
	public ResponseEntity<List<Theatre>> getAllTheatre() {
		List<Theatre> theatreData = theatreservice.getAllTheatre();

		if (!theatreData.isEmpty()) {
			return new ResponseEntity<>(theatreData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
