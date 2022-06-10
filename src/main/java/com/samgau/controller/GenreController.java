package com.samgau.controller;

import java.util.List;

import com.samgau.entity.Genre;
import com.samgau.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GenreController {

	private final GenreService genreService;

	@Autowired
	public GenreController(GenreService categoryService) {
		this.genreService = categoryService;
	}

	@RequestMapping("/genres")
	public String findAllCategories(Model model) {
		final List<Genre> categories = genreService.findAllGenres();

		model.addAttribute("genres", categories);
		return "list/list-genres";
	}
}
