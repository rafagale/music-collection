package com.formacion.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Artist.
 */
@Entity
@Table(name = "artists")
@SequenceGenerator(name = "seq", initialValue = 101)
public class Artist {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The name. */
	private String name;
	
	/** The year. */
	private Integer year;
	
	/** The people. */
	@OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
	private Set<People> people = new LinkedHashSet<>();
	
	/** The artists. */
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Artist> artists = new LinkedHashSet<>();
	
	/** The styles. */
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Style> styles = new LinkedHashSet<>();

	/**
	 * Instantiates a new artist.
	 */
	public Artist() {

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * Gets the people.
	 *
	 * @return the people
	 */
	public Set<People> getPeople() {
		return people;
	}

	/**
	 * Sets the people.
	 *
	 * @param people the new people
	 */
	public void setPeople(Set<People> people) {
		this.people = people;
	}

	/**
	 * Gets the artists.
	 *
	 * @return the artists
	 */
	public Set<Artist> getArtists() {
		return artists;
	}

	/**
	 * Sets the artists.
	 *
	 * @param artists the new artists
	 */
	public void setArtists(Set<Artist> artists) {
		this.artists = artists;
	}

	/**
	 * Gets the styles.
	 *
	 * @return the styles
	 */
	public Set<Style> getStyles() {
		return styles;
	}

	/**
	 * Sets the styles.
	 *
	 * @param styles the new styles
	 */
	public void setStyles(Set<Style> styles) {
		this.styles = styles;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
