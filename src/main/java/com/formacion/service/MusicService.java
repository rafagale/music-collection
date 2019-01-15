package com.formacion.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.formacion.domain.Artist;
import com.formacion.domain.People;
import com.formacion.domain.Style;

// TODO: Auto-generated Javadoc
/**
 * The Interface MusicService.
 */
@Service
public interface MusicService {

	/**
	 * Find all artists.
	 *
	 * @return the list
	 */
	List<Artist> findAllArtists();

	/**
	 * Find artist by id.
	 *
	 * @param id the id
	 * @return the artist
	 */
	Artist findArtistById(Long id);

	/**
	 * Find people by id.
	 *
	 * @param id the id
	 * @return the people
	 */
	People findPeopleById(Long id);

	/**
	 * Alta artist.
	 *
	 * @param artist the artist
	 * @return the boolean
	 */
	Boolean altaArtist(Artist artist);

	/**
	 * Alta people.
	 *
	 * @param people the people
	 * @return the boolean
	 */
	Boolean altaPeople(People people);

	/**
	 * Find artists by style.
	 *
	 * @param style the style
	 * @return the sets the
	 */
	Set<Artist> findArtistsByStyle(Style style);

	/**
	 * Find all people.
	 *
	 * @return the list
	 */
	List<People> findAllPeople();

	/**
	 * Find all styles.
	 *
	 * @return the list
	 */
	List<Style> findAllStyles();

	/**
	 * Save artist.
	 *
	 * @param artist the artist
	 */
	void saveArtist(Artist artist);

	/**
	 * Save people.
	 *
	 * @param people the people
	 */
	void savePeople(People people);

	/**
	 * Find style by name.
	 *
	 * @param name the name
	 * @return the style
	 */
	Style findStyleByName(String name);

	/**
	 * Save style.
	 *
	 * @param style the style
	 */
	void saveStyle(Style style);

	/**
	 * Find all except owner.
	 *
	 * @param artist the artist
	 * @return the list
	 */
	List<Artist> findAllExceptOwner(Artist artist);
}
