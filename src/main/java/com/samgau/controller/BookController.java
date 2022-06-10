package com.samgau.controller;

import com.samgau.entity.Book;
import com.samgau.service.AuthorService;
import com.samgau.service.BookService;
import com.samgau.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookController {

	private final BookService bookService;
	private final AuthorService authorService;
	private final GenreService genreService;

	@Autowired
	public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.genreService = genreService;
	}

	@RequestMapping("/books")
	public String findAllBooks(Model model) {
		final List<Book> books = bookService.findAllBooks();

		model.addAttribute("books", books);
		return "list/list-books";
	}

	@RequestMapping("/searchBook")
	public String searchAuthor(@Param("keyword") String keyword, Model model) {
		final List<Book> authors = bookService.searchBooks(keyword);

		model.addAttribute("books", authors);
		model.addAttribute("keyword", keyword);
		return "list/list-books";
	}
}
