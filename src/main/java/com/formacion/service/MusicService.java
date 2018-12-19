package com.formacion.service;

import com.formacion.domain.Artist;
import com.formacion.domain.People;
import com.formacion.domain.Style;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface MusicService {

    List<Artist> findAllArtists();

    Artist findArtistById(Long id);

    People findPeopleById(Long id);

    Boolean altaArtist(Artist artist);

    Boolean altaPeople(People people);

    Set<Artist> findArtistsByStyle(Style style);

    List<People> findAllPeople();

    List<Style> findAllStyles();

    void saveArtist(Artist artist);

    Style findStyleByName(String name);
}
