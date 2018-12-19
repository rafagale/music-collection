package com.formacion.domain;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artists")
@SequenceGenerator(name = "seq", initialValue = 60, allocationSize = 100)
public class Artist {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer year;
    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private Set<People> people = new LinkedHashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Artist> artists = new LinkedHashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Style> styles = new LinkedHashSet<>();

    public Artist() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    public Set<People> getPeople() {
        return people;
    }

    public void setPeople(Set<People> people) {
        this.people = people;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<Style> getStyles() {
        return styles;
    }

    public void setStyles(Set<Style> styles) {
        this.styles = styles;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

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
