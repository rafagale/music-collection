package com.formacion.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.formacion.domain.Artist;

@org.springframework.stereotype.Repository
public interface ArtistRepository extends Repository<Artist, Long> {

	List<Artist> findAll();

	List<Artist> findAllByOrderByName();	
	
	List<Artist> findByName(String name);

	Artist findById(Long id);

	Artist save(Artist artist);

}