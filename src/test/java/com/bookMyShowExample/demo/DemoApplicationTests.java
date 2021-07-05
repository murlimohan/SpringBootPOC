package com.bookMyShowExample.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bookMyShowExample.demo.model.Screen;
import com.bookMyShowExample.demo.model.Theatre;
import com.bookMyShowExample.demo.repositiory.ScreenRepo;
import com.bookMyShowExample.demo.repositiory.TheatreRepo;
import com.bookMyShowExample.demo.service.ScreenService;
import com.bookMyShowExample.demo.service.TheatreService;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private TheatreService theatreService;
	
	@Autowired
	private ScreenService screenService;

	@MockBean
	private TheatreRepo theatreRepo;
	
	@MockBean
	private ScreenRepo screenRepo;

	@Test
    void getTheatreListTest() {
		when(theatreRepo.findAll()).thenReturn(Stream
				.of(new Theatre(1, "xyz", "Mumbai"), new Theatre(2, "xyz", "Delhi")).collect(Collectors.toList()));
		assertEquals(2, theatreService.getAllTheatre().size());
	}
	
	@Test
    void getScreenListTest() {
		when(screenRepo.findAll()).thenReturn(Stream
				.of(new Screen(1,"screen1",new Theatre(1, "xyz", "Mumbai")),new Screen(2,"screen2",new Theatre(2, "xyz", "Delhi"))).collect(Collectors.toList()));
		assertEquals(2, screenService.getAllScreen().size());
	}

}
