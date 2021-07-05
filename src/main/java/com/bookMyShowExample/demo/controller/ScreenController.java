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

import com.bookMyShowExample.demo.model.Screen;
import com.bookMyShowExample.demo.repositiory.ScreenRepo;
import com.bookMyShowExample.demo.service.ScreenService;

@RestController
@RequestMapping("/")
public class ScreenController {

	@Autowired
	ScreenRepo screenrepo;

	@Autowired
	ScreenService screenService;

	@PostMapping("admin/AddScreen")
	public ResponseEntity<Screen> createScreen(@RequestBody Screen screen) {
		try {
			Screen screenEntryData = screenrepo
					.save(new Screen(screen.getScreenId(), screen.getScreenName(), screen.getTheatre()));
			return new ResponseEntity<>(screenEntryData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("user/Screen/{id}")
	public ResponseEntity<Screen> getScreenById(@PathVariable("id") int id) {
		Optional<Screen> screenData = screenrepo.findById(id);

		if (screenData.isPresent()) {
			return new ResponseEntity<>(screenData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("user/Screens")
	public ResponseEntity<List<Screen>> getAllScreen() {
		List<Screen> screenData = screenService.getAllScreen();

		if (!screenData.isEmpty()) {
			return new ResponseEntity<>(screenData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("user/Screens/scrrenListByTheatreId")
	public ResponseEntity<List<Screen>> getAllScreenByTheaterId(@RequestParam("id") int id) {
		List<Screen> screenData = screenService.getListofScreenByTheatreId(id);
		if (!screenData.isEmpty()) {
			return new ResponseEntity<>(screenData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
