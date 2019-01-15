package com.formacion.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.formacion.domain.People;

// TODO: Auto-generated Javadoc
/**
 * The Interface PeopleRepository.
 */
@org.springframework.stereotype.Repository
public interface PeopleRepository extends Repository<People, Long> {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<People> findAll();

	/**
	 * Find all by order by name.
	 *
	 * @return the list
	 */
	List<People> findAllByOrderByName();

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the people
	 */
	People findById(Long id);

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the list
	 */
	List<People> findByName(String name);

	/**
	 * Save.
	 *
	 * @param people the people
	 * @return the people
	 */
	People save(People people);

}