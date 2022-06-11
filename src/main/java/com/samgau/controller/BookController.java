package com.samgau.controller;

import com.samgau.entity.Book;
import com.samgau.service.AuthorService;
import com.samgau.service.BookService;
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

    @GetMapping("/addBook")
    public String showCreateForm(Book book, Model model) {
        model.addAttribute("allAuthors", authorService.findAllAuthors());
        model.addAttribute("allGenres", genreService.findAllGenres());
        return "add/add-book";
    }

    @RequestMapping("/add-book")
    public String createBook(Book book, Model model) {
        bookService.createBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("allAuthors", authorService.findAllAuthors());
        model.addAttribute("allGenres", genreService.findAllGenres());
        return "update/update-book";
    }

    @RequestMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") Long id, Book book, Model model) {
        book.setBookId(id);
        bookService.updateBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @RequestMapping("/increase-book/{id}")
    public String increaseBookAmount(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findBookById(id);
        book.setAmount(book.getAmount() + 1);
        bookService.updateBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @RequestMapping("/remove-one-book/{id}")
    public String deleteOneBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findBookById(id);
        if (book.getAmount() <= 1) {
            bookService.deleteBook(id);
        } else {
            book.setAmount(book.getAmount() - 1);
            bookService.updateBook(book);
        }
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @RequestMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookService.deleteBook(id);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }
}
