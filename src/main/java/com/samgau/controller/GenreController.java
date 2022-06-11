package com.samgau.controller;

import com.samgau.entity.Genre;
import com.samgau.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @RequestMapping("/genres")
    public String findAllGenres(Model model) {
        final List<Genre> genres = genreService.findAllGenres();
        model.addAttribute("genres", genres);
        return "list/list-genres";
    }

    @RequestMapping("/searchGenre")
    public String searchGenre(@Param("keyword") String keyword, Model model) {
        final List<Genre> authors = genreService.searchGenres(keyword);
        model.addAttribute("genres", authors);
        model.addAttribute("keyword", keyword);
        return "list/list-genres";
    }

    @GetMapping("/addGenre")
    public String showCreateForm(Genre genre) {
        return "add/add-genre";
    }

    @RequestMapping("/add-genre")
    public String createGenre(Genre genre, Model model) {
        final List<Genre> genres = genreService.findAllGenres();
        for (Genre genre1 : genres) {
            if (genre1.getGenreName().equalsIgnoreCase(genre.getGenreName())) {
                return "add/add-genre";
            }
        }
        genreService.createGenre(genre);
        model.addAttribute("genre", genreService.findAllGenres());
        return "redirect:/genres";
    }

    @RequestMapping("/remove-genre/{id}")
    public String deleteGenre(@PathVariable("id") Long id, Model model) {
        genreService.deleteGenre(id);
        model.addAttribute("genre", genreService.findAllGenres());
        return "redirect:/genres";
    }

    @GetMapping("/updateGenre/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Genre genre = genreService.findGenreById(id);
        model.addAttribute("genre", genre);
        return "update/update-genre";
    }

    @RequestMapping("/update-genre/{id}")
    public String updateGenre(@PathVariable("id") Long id, Genre genre, Model model) {
        genre.setGenreId(id);
        final List<Genre> genres = genreService.findAllGenres();
        for (Genre genre1 : genres) {
            if (genre1.getGenreName().equalsIgnoreCase(genre.getGenreName())) {
                return "update/update-genre";
            }
        }
        genreService.updateGenre(genre);
        model.addAttribute("genre", genreService.findAllGenres());
        return "redirect:/genres";
    }
}
