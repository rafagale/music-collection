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

@Controller
public class MusicController {

	private final MusicService musicService;

	@Autowired
	public MusicController(MusicService musicService) {
		this.musicService = musicService;
	}

	@GetMapping({ "/", "/artistslist", "/artistform/cancel" })
	public String artistsList(Model model) {
		model.addAttribute("artists", musicService.findAllArtists());
		model.addAttribute("styles", musicService.findAllStyles());
		return "artists_list";
	}

	@GetMapping("/artistListBy")
	public String artistSearchBy(@RequestParam(name = "style") String style, Model model) {
		Style styleObj = musicService.findStyleByName(style);

		model.addAttribute("artists", musicService.findArtistsByStyle(styleObj));
		model.addAttribute("styles", musicService.findAllStyles());
		return "artists_list";
	}

	@GetMapping("/addartist")
	public String addArtist(Model model) {
		model.addAttribute("artist", new Artist());
		model.addAttribute("styles", musicService.findAllStyles());
		return "artistform";
	}

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

	@GetMapping("/artistdetails")
	public String ArtistDetails(@RequestParam(name = "id", required = true) Long id, Model model) {
		Artist artist = musicService.findArtistById(id);

		model.addAttribute("artist", artist);
		return "/artist_details";
	}

	@GetMapping("/addfeatured")
	public String addFeatured(@RequestParam(name = "id", required = true) Long id, Model model) {
		Artist artist = musicService.findArtistById(id);
		model.addAttribute("artists", musicService.findAllExceptOwner(artist));
		model.addAttribute("artist", artist);

		return "artist_artistform";
	}

	@GetMapping("/addmembers")
	public String addMembersForm(@RequestParam(name = "id", required = true) Long id, Model model) {
		Artist artist = musicService.findArtistById(id);
		model.addAttribute("people", musicService.findAllPeople());
		model.addAttribute("artist", artist);
		return "artistmembersform";
	}

	@PostMapping("/saveartist")
	public String saveArtist(@ModelAttribute(name = "artist") Artist artist, Model model) {
		for (People ppl : artist.getPeople()) {
			ppl.setArtist(artist);
			musicService.savePeople(ppl);
		}
		musicService.saveArtist(artist);
		return "redirect:/artistslist";
	}

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

	@GetMapping({ "/peoplelist", "/people/cancel" })
	public String listPeople(Model model) {
		model.addAttribute("people", musicService.findAllPeople());
		return "/people_list";
	}

	@GetMapping("/peopleform")
	public String peopleFormRedirect(Model model) {
		model.addAttribute("people", new People());
		return "peopleform";
	}

	@PostMapping("/addpeople")
	public String altaPeople(@ModelAttribute(name = "people") People people, Model model) {
		if (musicService.altaPeople(people)) {
			model.addAttribute("people", musicService.findAllPeople());
			return "redirect:/peoplelist";
		} else {
			return "peopleform";
		}
	}

}
