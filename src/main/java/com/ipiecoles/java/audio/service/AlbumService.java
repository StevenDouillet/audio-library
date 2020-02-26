package com.ipiecoles.java.audio.service;

import com.ipiecoles.java.audio.exceptions.NotFoundException;
import com.ipiecoles.java.audio.exceptions.NullPropertyException;
import com.ipiecoles.java.audio.model.Album;
import com.ipiecoles.java.audio.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public void deleteAlbum(Integer id) {
        Optional<Album> album = albumRepository.findById(id);

        if (!album.isPresent()) {
            throw new NotFoundException("Album not found");
        }

        albumRepository.delete(album.get());
    }

    public Album addAlbum(Album album) {

        if (album.isNullTitle() || album.getTitle().isEmpty()) {
            throw new NullPropertyException("Can't add album without title");
        }

        return albumRepository.save(album);
    }

}
