package com.formacion.controller;

import com.formacion.domain.Artist;
import com.formacion.domain.People;
import com.formacion.domain.Style;
import com.formacion.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MusicController {

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    // **************************
    // ARTIST
    // **************************
    @GetMapping({"/", "/artistslist", "/artistform/cancel"})
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

    @GetMapping("/addmembers")
    public String addMembersForm(@RequestParam(name = "id", required = false) Long id, Model model) {
        Artist artist = musicService.findArtistById(id);
        model.addAttribute("people", musicService.findAllPeople());
        model.addAttribute("artist", artist);
        return "artistmembersform";
    }

    @GetMapping("/addartist")
    public String addArtist(Model model) {
        model.addAttribute("artist", new Artist());
        model.addAttribute("styles", musicService.findAllStyles());
        return "artistform";
    }

    @PostMapping("/saveartist")
    public String saveArtist(@ModelAttribute(name = "artist") Artist artist, Model model) {
        musicService.saveArtist(artist);
        model.addAttribute("styles", musicService.findAllStyles());
        model.addAttribute("artists", musicService.findAllArtists());
        return "artists_list";
    }

    @GetMapping("/goartist")
    public String goArtist(@RequestParam(name = "id", required = false) Long id, Model model) {
        People people = musicService.findPeopleById(id);
        Artist artist = new Artist();
        people.setArtist(artist);
        model.addAttribute("artist", artist);
        model.addAttribute("styles", musicService.findAllStyles());
        return "/artistform";
    }

    @PostMapping("/addartist")
    public String altaArtist(@ModelAttribute(name = "artist") Artist artist, Model model) {
        if (musicService.altaArtist(artist)) {
            model.addAttribute("artists", musicService.findAllArtists());
            model.addAttribute("styles", musicService.findAllStyles());
            return "/artists_list";
        } else {
            return "artistform";
        }
    }

    @GetMapping("/artistdetails")
    public String ArtistDetails(@RequestParam(name = "id", required = false) Long id, Model model) {
        Artist artist = musicService.findArtistById(id);

        model.addAttribute("artist", artist);
        return "/artist_details";
    }

    @GetMapping("/addfeatured")
    public String addFeatured(@RequestParam(name = "id", required = false) Long id, Model model) {
        Artist artist = musicService.findArtistById(id);

        model.addAttribute("artists", musicService.findAllArtists());
        model.addAttribute("artist", artist);

        return "artist_artistform";
    }

//	@GetMapping("/addmembers")
//	public String addMembersForm(@RequestParam(name = "id", required = false) Long id, Model model) {
//		Artist artist = musicService.findArtistById(id);
//		model.addAttribute("people", musicService.findAllPeople());
//		model.addAttribute("artist", artist);
//		return "artistmembersform";
//	}

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

    // **************************
    // ARTIST
    // **************************

    // **************************
    // PEOPLE
    // **************************
    @GetMapping({"/peoplelist", "/people/cancel"})
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
            return "people_list";
        } else {
            return "peopleform";
        }
    }
    // **************************
    // PEOPLE
    // **************************
}
