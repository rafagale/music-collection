package com.formacion.repository;

import com.formacion.domain.Artist;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ArtistRepository extends Repository<Artist, Long> {

    List<Artist> findAll();

    List<Artist> findByName(String name);

    Artist findById(Long id);

    Artist save(Artist artist);
}