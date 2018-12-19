package com.formacion.repository;

import com.formacion.domain.Style;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface StyleRepository extends Repository<Style, Long> {

    List<Style> findAll();

    Style findById(Long id);

    Style save(Style style);

    Style findByName(String name);
}