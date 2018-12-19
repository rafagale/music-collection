package com.formacion.service.impl;

import com.formacion.domain.Artist;
import com.formacion.domain.People;
import com.formacion.domain.Style;
import com.formacion.repository.ArtistRepository;
import com.formacion.repository.PeopleRepository;
import com.formacion.repository.StyleRepository;
import com.formacion.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MusicServiceImpl implements MusicService {

    private final ArtistRepository artistRepository;
    private final PeopleRepository peopleRepository;
    private final StyleRepository styleRepository;

    @Autowired
    public MusicServiceImpl(ArtistRepository artistRepository, PeopleRepository peopleRepository,
                            StyleRepository styleRepository) {
        this.artistRepository = artistRepository;
        this.peopleRepository = peopleRepository;
        this.styleRepository = styleRepository;
    }

    @Override
    public List<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Boolean altaArtist(Artist artist) {
        artistRepository.save(artist);
        return true;
    }

    @Override
    public Boolean altaPeople(People people) {
        if (people == null) {
            return false;
        } else {
            peopleRepository.save(people);
            return true;
        }
    }

    @Override
    public Set<Artist> findArtistsByStyle(Style style) {
        Set<Artist> set = new HashSet<>();
        if (style == null) {
            return set;
        }
        set = styleRepository.findById(style.getId()).getArtists();
        return set;
    }

    @Override
    public List<People> findAllPeople() {
        List<People> list = peopleRepository.findAll();
        return list;
    }

    @Override
    public People findPeopleById(Long id) {
        return peopleRepository.findById(id);
    }

    @Override
    public List<Style> findAllStyles() {
        return styleRepository.findAll();
    }

    @Override
    public Artist findArtistById(Long id) {
        return artistRepository.findById(id);
    }

    @Override
    public void saveArtist(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    public Style findStyleByName(String name) {
        return styleRepository.findByName(name);
    }

}
