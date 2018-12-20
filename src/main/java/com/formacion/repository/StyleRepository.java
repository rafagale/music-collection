package com.formacion.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.formacion.domain.Style;

@org.springframework.stereotype.Repository
public interface StyleRepository extends Repository<Style, Long> {

	List<Style> findAll();

	Style findById(Long id);

	Style save(Style style);

	Style findByName(String name);
}