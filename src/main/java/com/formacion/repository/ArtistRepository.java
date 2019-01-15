package com.formacion.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.formacion.domain.Artist;

// TODO: Auto-generated Javadoc
/**
 * The Interface ArtistRepository.
 */
@org.springframework.stereotype.Repository
public interface ArtistRepository extends Repository<Artist, Long> {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<Artist> findAll();

	/**
	 * Find all by order by name.
	 *
	 * @return the list
	 */
	List<Artist> findAllByOrderByName();	
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the list
	 */
	List<Artist> findByName(String name);

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the artist
	 */
	Artist findById(Long id);

	/**
	 * Save.
	 *
	 * @param artist the artist
	 * @return the artist
	 */
	Artist save(Artist artist);

}