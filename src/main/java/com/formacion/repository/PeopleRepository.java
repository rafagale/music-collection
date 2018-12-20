package com.formacion.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.formacion.domain.People;

@org.springframework.stereotype.Repository
public interface PeopleRepository extends Repository<People, Long> {

	List<People> findAll();
	
	List<People> findAllByOrderByName();
	
	People findById(Long id);

	People save(People people);

}