package com.samgau.service.impl;

import com.samgau.entity.Genre;
import com.samgau.exception.NotFoundException;
import com.samgau.repository.GenreRepository;
import com.samgau.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository categoryRepository) {
        this.genreRepository = categoryRepository;
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> searchGenres(String keyword) {
        return genreRepository.findAll()
                .stream()
                .filter(x -> x.getGenreName().toLowerCase().replace(" ", "")
                        .contains(keyword.toLowerCase().replace(" ", "")))
                .collect(Collectors.toList());
    }

    @Override
    public Genre findGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));
    }

    @Override
    public void createGenre(Genre category) {
        genreRepository.save(category);
    }

    @Override
    public void updateGenre(Genre category) {
        genreRepository.save(category);
    }

    @Override
    public void deleteGenre(Long id) {
        final Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));

        genreRepository.deleteById(genre.getGenreId());
    }

}
