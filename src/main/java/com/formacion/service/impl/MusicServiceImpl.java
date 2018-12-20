package com.formacion.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacion.domain.Artist;
import com.formacion.domain.People;
import com.formacion.domain.Style;
import com.formacion.repository.ArtistRepository;
import com.formacion.repository.PeopleRepository;
import com.formacion.repository.StyleRepository;
import com.formacion.service.MusicService;

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
		return artistRepository.findAllByOrderByName();
	}

	@Override
	public Boolean altaArtist(Artist artist) {
		if (artist == null) {
			return false;
		}

		if (artistRepository.findByName(artist.getName()).size() < 1) {
			if (artist.getStyles().size() < 1) {
				Style style = new Style();
				style.setName("Unspecified");
				styleRepository.save(style);
				artist.getStyles().add(style);
			}
			artistRepository.save(artist);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean altaPeople(People people) {
		if (people == null) {
			return false;
		}
		if (peopleRepository.findByName(people.getName()).size() < 1) {
			peopleRepository.save(people);
			return true;
		} else {
			return false;
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
		List<People> list = peopleRepository.findAllByOrderByName();
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
	public void savePeople(People people) {
		peopleRepository.save(people);
	}

	@Override
	public Style findStyleByName(String name) {
		return styleRepository.findByName(name);
	}

	@Override
	public void saveStyle(Style style) {
		styleRepository.save(style);
	}

	@Override
	public List<Artist> findAllExceptOwner(Artist artist) {
		List<Artist> allArtists = artistRepository.findAll();
		List<Artist> list = new ArrayList<>();

		for (Artist a : allArtists) {
			if (!a.getName().equalsIgnoreCase(artist.getName())) {
				list.add(a);
			}
		}
		return list;
	}

}
