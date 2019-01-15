package com.formacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formacion.domain.Artist;
import com.formacion.domain.People;
import com.formacion.domain.Style;
import com.formacion.service.MusicService;

// TODO: Auto-generated Javadoc
/**
 * The Class MusicController.
 */
@Controller
public class MusicController {

	/** The music service. */
	private final MusicService musicService;

	/**
	 * Instantiates a new music controller.
	 *
	 * @param musicService the music service
	 */
	@Autowired
	public MusicController(MusicService musicService) {
		this.musicService = musicService;
	}

	/**
	 * Artists list. Redirige a un listado de artistas
	 * 
	 * @param model the model
	 * @return the string
	 */
	@GetMapping({ "/", "/artistslist", "/artistform/cancel" })
	public String artistsList(Model model) {
		model.addAttribute("artists", musicService.findAllArtists());
		model.addAttribute("styles", musicService.findAllStyles());
		return "artists_list";
	}

	/**
	 * Artist search by. Dependiendo del parametro (nombre de estilo), redirigira a
	 * un listado de artistas diferentes (que tengan el estilo)
	 *
	 * @param style the style
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/artistListBy")
	public String artistSearchBy(@RequestParam(name = "style") String style, Model model) {
		Style styleObj = musicService.findStyleByName(style);

		model.addAttribute("artists", musicService.findArtistsByStyle(styleObj));
		model.addAttribute("styles", musicService.findAllStyles());
		return "artists_list";
	}

	/**
	 * Adds the artist. Redirige al formulario de creacion de artista
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/addartist")
	public String addArtist(Model model) {
		model.addAttribute("artist", new Artist());
		model.addAttribute("styles", musicService.findAllStyles());
		return "artistform";
	}

	/**
	 * Alta artist. Crea el artista en BBDD y redirige a la lista o redirige al
	 * formulario de creacion si hay errores segun el metodo musicService.altaArtist
	 * (
	 * 
	 * @see com.formacion.service.MusicService#altaArtist(com.formacion.domain.Artist))
	 *
	 * @param artist the artist
	 * @param model  the model
	 * @return the string
	 */
	@PostMapping("/addartist")
	public String altaArtist(@ModelAttribute(name = "artist") Artist artist, Model model) {
		if (musicService.altaArtist(artist)) {
			model.addAttribute("artists", musicService.findAllArtists());
			model.addAttribute("styles", musicService.findAllStyles());
			return "redirect:/artistslist";
		} else {
			model.addAttribute("error", "There's already an artist called " + artist.getName());
			return "artistform";
		}
	}

	/**
	 * Artist details. Redirige a los detalles de un artista
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/artistdetails")
	public String ArtistDetails(@RequestParam(name = "id", required = true) Long id, Model model) {
		Artist artist = musicService.findArtistById(id);

		model.addAttribute("artist", artist);
		return "/artist_details";
	}

	/**
	 * Adds the featured. Redirige a un formulario especifico para añadir artistas a
	 * artistas
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/addfeatured")
	public String addFeatured(@RequestParam(name = "id", required = true) Long id, Model model) {
		Artist artist = musicService.findArtistById(id);
		model.addAttribute("artists", musicService.findAllExceptOwner(artist));
		model.addAttribute("artist", artist);

		return "artist_artistform";
	}

	/**
	 * Adds the members form. Redirige a un formulario especifico para añadir
	 * personas a artistas
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/addmembers")
	public String addMembersForm(@RequestParam(name = "id", required = true) Long id, Model model) {
		Artist artist = musicService.findArtistById(id);
		model.addAttribute("people", musicService.findAllPeople());
		model.addAttribute("artist", artist);
		return "artistmembersform";
	}

	/**
	 * Save artist. Pone la id en el campo artist_id de people (Establece la
	 * relacion) y guarda en BBDD
	 *
	 * @param artist the artist
	 * @param model  the model
	 * @return the string
	 */
	@PostMapping("/saveartist")
	public String saveArtist(@ModelAttribute(name = "artist") Artist artist, Model model) {
		for (People ppl : artist.getPeople()) {
			ppl.setArtist(artist);
			musicService.savePeople(ppl);
		}
		musicService.saveArtist(artist);
		return "redirect:/artistslist";
	}

	/**
	 * Removes the featured. Elimina un artista del set de artistas de un artista
	 *
	 * @param id         the id
	 * @param featuredId the featured id
	 * @param model      the model
	 * @return the string
	 */
	@GetMapping("/removefeatured/{id}/{featuredId}")
	public String removeFeatured(@PathVariable(value = "id") Long id,
			@PathVariable(value = "featuredId") Long featuredId, Model model) {
		Artist artist = musicService.findArtistById(id);
		Artist featuredArtist = musicService.findArtistById(featuredId);

		artist.getArtists().remove(featuredArtist);
		musicService.saveArtist(artist);

		model.addAttribute("artist", artist);
		model.addAttribute("styles", musicService.findAllStyles());
		model.addAttribute("artists", musicService.findAllArtists());
		return "/artists_list";
	}

	/**
	 * List people.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping({ "/peoplelist", "/people/cancel" })
	public String listPeople(Model model) {
		model.addAttribute("people", musicService.findAllPeople());
		return "/people_list";
	}

	/**
	 * People form redirect. Redirige al formulario de creacion de personas
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/peopleform")
	public String peopleFormRedirect(Model model) {
		model.addAttribute("people", new People());
		return "peopleform";
	}

	/**
	 * Alta people. Segun el metodo altaPeople (@see
	 * com.formacion.service.MusicService#altaPeople(com.formacion.domain.People))
	 * crea una persona en BBDD.
	 *
	 * @param people the people
	 * @param model  the model
	 * @return the string
	 */
	@PostMapping("/addpeople")
	public String altaPeople(@ModelAttribute(name = "people") People people, Model model) {
		if (musicService.altaPeople(people)) {
			model.addAttribute("people", musicService.findAllPeople());
			return "redirect:/peoplelist";
		} else {
			model.addAttribute("error", "There's already a person called " + people.getName());
			return "peopleform";
		}
	}

}
