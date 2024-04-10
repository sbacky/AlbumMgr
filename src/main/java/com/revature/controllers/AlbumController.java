package com.revature.controllers;

import com.revature.daos.AlbumDAO;
import com.revature.models.Album;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/albums")
@ResponseBody
public class AlbumController {

    private AlbumDAO albumDAO;

    public AlbumController(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        return ResponseEntity.ok(albumDAO.save(album));
    }

    @GetMapping
    public ResponseEntity<Iterable<Album>> getAllAlbums() {
        return ResponseEntity.ok(albumDAO.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Integer id) {
        Optional<Album> album = albumDAO.findById(id);
        if (album.isPresent()) {
            return ResponseEntity.ok(album.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id) {
        if (albumDAO.existsById(id)) {
            albumDAO.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
