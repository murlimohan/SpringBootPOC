package com.bookMyShowExample.demo.repositiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookMyShowExample.demo.model.Screen;

public interface ScreenRepo extends JpaRepository<Screen, Integer>{

	List<Screen> findByTheatre_TheatreId(int id);

}
