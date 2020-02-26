package com.ipiecoles.java.audio.controller;

import com.ipiecoles.java.audio.model.Artist;
import com.ipiecoles.java.audio.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Artist> getArtistById(@PathVariable("id") Integer id) {
        return artistService.getArtistById(id);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long countArtists() {
        return artistService.countArtists();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody Page<Artist> getArtists(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortProperty", defaultValue = "name") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(value = "name", required = false) String name) {
        return artistService.getArtists(page, size, sortProperty, sortDirection, name);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Artist addArtist(@RequestBody Artist artist) {
        return artistService.addArtist(artist);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void deleteArtist(@PathVariable("id") Integer id) {
        artistService.deleteArtist(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody Artist saveArtist(@PathVariable("id") Integer id, @RequestBody Artist artist) {
        return artistService.saveArtist(id, artist);
    }
}