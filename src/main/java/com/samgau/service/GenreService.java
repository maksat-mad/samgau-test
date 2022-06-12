package com.samgau.service;

import java.util.List;

import com.samgau.entity.Genre;

public interface GenreService {

	List<Genre> findAllGenres();

	List<Genre> searchGenres(String keyword);

	Genre findGenreById(Long id);

	void createGenre(Genre genre);

	void updateGenre(Genre genre);

	void deleteGenre(Long id);

}
