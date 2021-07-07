package com.bookMyShowExample.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookMyShowExample.demo.exception.BadRequestException;
import com.bookMyShowExample.demo.exception.NoDataFoundException;
import com.bookMyShowExample.demo.model.Screen;
import com.bookMyShowExample.demo.model.pojo.ScreenRequest;
import com.bookMyShowExample.demo.repositiory.ScreenRepo;
import com.bookMyShowExample.demo.service.ScreenService;

@RestController
@RequestMapping("/")
public class ScreenController {

	@Autowired
	ScreenRepo screenrepo;

	@Autowired
	ScreenService screenService;

	@PostMapping("admin/add-screen")
	public ResponseEntity<Screen> createScreen(@RequestBody ScreenRequest screenReq) {
		try {
			Screen screenEntryData = screenrepo
					.save(new Screen(screenReq.getScreenId(), screenReq.getScreenName(), screenReq.getTheatre()));
			return new ResponseEntity<>(screenEntryData, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new BadRequestException("Please check request data");
		}
	}

	@GetMapping("user/screen/{id}")
	public ResponseEntity<Screen> getScreenById(@PathVariable("id") int id) throws NoDataFoundException {
		Optional<Screen> screenData = screenrepo.findById(id);

		if (screenData.isPresent()) {
			return new ResponseEntity<>(screenData.get(), HttpStatus.OK);
		} else {
			throw new NoDataFoundException("Screen id " + id + " is not present in database.");
		}
	}

	@GetMapping("user/screens")
	public ResponseEntity<List<Screen>> getAllScreen() {
		List<Screen> screenData = screenService.getAllScreen();

		if (!screenData.isEmpty()) {
			return new ResponseEntity<>(screenData, HttpStatus.OK);
		} else {
			throw new NoDataFoundException("Screen data is not present in database.");
		}
	}

	@GetMapping("user/screens-by-theatreId")
	public ResponseEntity<List<Screen>> getAllScreenByTheaterId(@RequestParam("id") int id)
			throws NoDataFoundException {
		List<Screen> screenData = screenService.getListofScreenByTheatreId(id);
		if (!screenData.isEmpty()) {
			return new ResponseEntity<>(screenData, HttpStatus.OK);
		} else {
			throw new NoDataFoundException("Theatre id " + id + " is not present in screen table.");
		}
	}

}
