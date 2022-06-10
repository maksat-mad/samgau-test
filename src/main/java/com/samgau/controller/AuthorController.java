package com.samgau.controller;

import java.util.List;

import com.samgau.entity.Author;
import com.samgau.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {

	private final AuthorService authorService;

	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@RequestMapping("/authors")
	public String findAllAuthors(Model model) {
		final List<Author> authors = authorService.findAllAuthors();

		model.addAttribute("authors", authors);
		return "list/list-authors";
	}

	@RequestMapping("/searchAuthor")
	public String searchAuthor(@Param("keyword") String keyword, Model model) {
		final List<Author> authors = authorService.searchAuthors(keyword);

		model.addAttribute("authors", authors);
		model.addAttribute("keyword", keyword);
		return "list/list-authors";
	}

	@GetMapping("/addAuthor")
	public String showCreateForm(Author author) {
		return "add/add-author";
	}

	@RequestMapping("/add-author")
	public String createAuthor(Author author, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add/add-author";
		}

		authorService.createAuthor(author);
		model.addAttribute("author", authorService.findAllAuthors());
		return "redirect:/list/list-authors";
	}
}
