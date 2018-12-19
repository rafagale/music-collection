package com.formacion.repository;

import com.formacion.domain.People;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface PeopleRepository extends Repository<People, Long> {

    List<People> findAll();

    People findById(Long id);

    People save(People people);

}