package com.samgau.service;

import java.util.List;

import com.samgau.entity.Genre;

public interface GenreService {

	public List<Genre> findAllGenres();

	public List<Genre> searchGenres(String keyword);

	public Genre findGenreById(Long id);

	public void createGenre(Genre genre);

	public void updateGenre(Genre genre);

	public void deleteGenre(Long id);

}
