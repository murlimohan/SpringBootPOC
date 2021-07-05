package com.bookMyShowExample.demo.repositiory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bookMyShowExample.demo.model.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Integer> {
	
	Page<Theatre> findAll(Pageable pageable);
}
