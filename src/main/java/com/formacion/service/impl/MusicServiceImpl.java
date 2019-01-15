package com.formacion.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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

// TODO: Auto-generated Javadoc
/**
 * The Class MusicServiceImpl.
 */
@Service
public class MusicServiceImpl implements MusicService {

	/** The artist repository. */
	private final ArtistRepository artistRepository;

	/** The people repository. */
	private final PeopleRepository peopleRepository;

	/** The style repository. */
	private final StyleRepository styleRepository;

	/**
	 * Instantiates a new music service impl.
	 *
	 * @param artistRepository the artist repository
	 * @param peopleRepository the people repository
	 * @param styleRepository  the style repository
	 */
	@Autowired
	public MusicServiceImpl(ArtistRepository artistRepository, PeopleRepository peopleRepository,
			StyleRepository styleRepository) {
		this.artistRepository = artistRepository;
		this.peopleRepository = peopleRepository;
		this.styleRepository = styleRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.formacion.service.MusicService#findAllArtists() Devuelve una lista
	 * de artistas ordenados alfabeticamente por nombre
	 */
	@Override
	public List<Artist> findAllArtists() {
		return artistRepository.findAllByOrderByName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.formacion.service.MusicService#altaArtist(com.formacion.domain.Artist)
	 * Devuelve true si no hay ningun artista en la BBDD con el mismo nombre y lo
	 * crea. Si no se especifica estilo en el formulario se crea y asigna uno por
	 * defecto. Devuelve false si ya existe un artista en BBDD con el mismo nombre.
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.formacion.service.MusicService#altaPeople(com.formacion.domain.People)
	 * Devuelve true y crea una persona sino hay ninguna ya en BBDD con ese mismo
	 * nombre Devuelve false si ya existe una persona con el mismo nombre en BBDD y
	 * no la crea
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.formacion.service.MusicService#findArtistsByStyle(com.formacion.domain.
	 * Style) Devuelve un set de artistas que tienen el estilo pasado como parametro
	 * en su set de estilos
	 */
	@Override
	public Set<Artist> findArtistsByStyle(Style style) {
		Set<Artist> set = new LinkedHashSet<>();
		if (style == null) {
			return set;
		}
		set = styleRepository.findById(style.getId()).getArtists();
		return set;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.formacion.service.MusicService#findAllPeople() Devuelve una lista de
	 * todas las personas ordenadas alfabeticamente por nombre
	 */
	@Override
	public List<People> findAllPeople() {
		List<People> list = peopleRepository.findAllByOrderByName();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.formacion.service.MusicService#findPeopleById(java.lang.Long)
	 */
	@Override
	public People findPeopleById(Long id) {
		return peopleRepository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.formacion.service.MusicService#findAllStyles()
	 */
	@Override
	public List<Style> findAllStyles() {
		return styleRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.formacion.service.MusicService#findArtistById(java.lang.Long)
	 */
	@Override
	public Artist findArtistById(Long id) {
		return artistRepository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.formacion.service.MusicService#saveArtist(com.formacion.domain.Artist)
	 */
	@Override
	public void saveArtist(Artist artist) {
		artistRepository.save(artist);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.formacion.service.MusicService#savePeople(com.formacion.domain.People)
	 */
	@Override
	public void savePeople(People people) {
		peopleRepository.save(people);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.formacion.service.MusicService#findStyleByName(java.lang.String)
	 */
	@Override
	public Style findStyleByName(String name) {
		return styleRepository.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.formacion.service.MusicService#saveStyle(com.formacion.domain.Style)
	 */
	@Override
	public void saveStyle(Style style) {
		styleRepository.save(style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.formacion.service.MusicService#findAllExceptOwner(com.formacion.domain.
	 * Artist) Devuelve una lista con todos los artistas excepto el seleccionado
	 * para impedir que un artista este relacionado consigo mismo
	 */
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
