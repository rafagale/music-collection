package com.formacion.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.formacion.domain.Style;

// TODO: Auto-generated Javadoc
/**
 * The Interface StyleRepository.
 */
@org.springframework.stereotype.Repository
public interface StyleRepository extends Repository<Style, Long> {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<Style> findAll();

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the style
	 */
	Style findById(Long id);

	/**
	 * Save.
	 *
	 * @param style the style
	 * @return the style
	 */
	Style save(Style style);

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the style
	 */
	Style findByName(String name);
}