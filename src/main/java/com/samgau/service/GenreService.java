package com.samgau.service;

import java.util.List;

import com.samgau.entity.Genre;

public interface GenreService {

	public List<Genre> findAllGenres();

	public Genre findGenreById(Long id);

	public void createGenre(Genre category);

	public void updateGenre(Genre category);

	public void deleteGenre(Long id);

}
