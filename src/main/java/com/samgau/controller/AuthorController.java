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
import org.springframework.web.bind.annotation.PathVariable;
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
	public String createAuthor(Author author, Model model) {
		final List<Author> authors = authorService.findAllAuthors();
		for (Author author1 : authors) {
			if (author1.getAuthorName().equalsIgnoreCase(author.getAuthorName())) {
				return "add/add-author";
			}
		}
		authorService.createAuthor(author);
		model.addAttribute("author", authorService.findAllAuthors());
		return "redirect:/authors";
	}

	@RequestMapping("/remove-author/{id}")
	public String deleteAuthor(@PathVariable("id") Long id, Model model) {
		authorService.deleteAuthor(id);
		model.addAttribute("author", authorService.findAllAuthors());
		return "redirect:/authors";
	}

	@GetMapping("/updateAuthor/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Author author = authorService.findAuthorById(id);
		model.addAttribute("author", author);
		return "update/update-author";
	}

	@RequestMapping("/update-author/{id}")
	public String updateAuthor(@PathVariable("id") Long id, Author author, Model model) {
		author.setAuthorId(id);
		final List<Author> authors = authorService.findAllAuthors();
		for (Author author1 : authors) {
			if (author1.getAuthorName().equalsIgnoreCase(author.getAuthorName())) {
				return "update/update-author";
			}
		}
		authorService.updateAuthor(author);
		model.addAttribute("author", authorService.findAllAuthors());
		return "redirect:/authors";
	}
}
