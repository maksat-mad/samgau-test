package com.samgau.controller;

import com.samgau.entity.Book;
import com.samgau.entity.Borrowed;
import com.samgau.entity.Student;
import com.samgau.exception.NotFoundException;
import com.samgau.service.BookService;
import com.samgau.service.BorrowedService;
import com.samgau.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BorrowedController {

    private final BorrowedService borrowedService;
    private final BookService bookService;
    private final StudentService studentService;

    @Autowired
    public BorrowedController(BorrowedService borrowedService, BookService bookService, StudentService studentService) {
        this.borrowedService = borrowedService;
        this.bookService = bookService;
        this.studentService = studentService;
    }

    @RequestMapping("/profile")
    public String findStudentsAllBorrowedBooks(Model model) {
        final String name = getCurrentUsername();
        final List<Borrowed> borroweds = borrowedService.findAllBorrowedBooks();
        List<Borrowed> result = borroweds.stream()
                .filter(el -> el.getStudent().getStudentName().equals(name))
                .collect(Collectors.toList());
        model.addAttribute("borroweds", result);
        model.addAttribute("money", calculateMoney(result));
        return "student/profile";
    }

    @RequestMapping("/borrows")
    public String findAllBorrowedBooks(Model model) {
        final List<Borrowed> borroweds = borrowedService.findAllBorrowedBooks();
        model.addAttribute("borroweds", borroweds);
        model.addAttribute("money", calculateMoney(borroweds));
        return "librarian/borrows";
    }

    @RequestMapping("/borrow/{id}")
    public String borrow(@PathVariable("id") Long id) {
        String studentName = getCurrentUsername();
        Book book = bookService.findBookById(id);
        Student student = studentService.findStudentByName(studentName);
        List<Borrowed> borroweds = borrowedService.findAllBorrowedBooks();
        Borrowed borrowed = null;
        try {
            borrowed = (Borrowed) borroweds.stream()
                    .filter(b -> b.getStudent().getStudentName().equals(studentName)
                            && b.getBookId() == id)
                    .findAny()
                    .orElseThrow(() -> new NotFoundException("Not found borrowed"));
        } catch (Exception ex) {
            borrowed = new Borrowed();
            borrowed.setBookId(book.getBookId());
            borrowed.setTitle(book.getTitle());
            borrowed.setBorrowedAmount(0);
            borrowed.setPrice(book.getPrice());
            borrowed.setAuthor(book.getAuthor());
            borrowed.setGenre(book.getGenre());
            borrowed.setStudent(student);
        }
        borrowed.setBorrowedAmount(borrowed.getBorrowedAmount() + 1);
        borrowedService.updateBorrowed(borrowed);
        if (book.getAmount() <= 1) {
            bookService.deleteBook(id);
        } else {
            book.setAmount(book.getAmount() - 1);
            bookService.updateBook(book);
        }
        return "redirect:/books";
    }

    @RequestMapping("/return/{id}")
    public String returnBook(@PathVariable("id") Long id) {
        Borrowed borrowed = borrowedService.findBorrowedById(id);
        List<Book> books = bookService.findAllBooks();
        Book book = null;
        try {
            book = books.stream()
                    .filter(b -> b.getTitle().equals(borrowed.getTitle()) &&
                            b.getAuthor().getAuthorId() == borrowed.getAuthor().getAuthorId())
                    .findAny()
                    .orElseThrow(() -> new NotFoundException("Not found book"));
        } catch (Exception ex) {
            book = new Book();
            book.setTitle(borrowed.getTitle());
            book.setAmount(0);
            book.setPrice(borrowed.getPrice());
            book.setAuthor(borrowed.getAuthor());
            book.setGenre(borrowed.getGenre());
        }
        book.setAmount(book.getAmount() + 1);
        bookService.updateBook(book);
        if (borrowed.getBorrowedAmount() <= 1) {
            borrowedService.deleteBorrowed(borrowed.getBorrowedId());
        } else {
            borrowed.setBorrowedAmount(borrowed.getBorrowedAmount() - 1);
            borrowedService.updateBorrowed(borrowed);
        }
        if (isStudent(getCurrentUsername())) {
            return "redirect:/profile";
        }
        return "redirect:/borrows";
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String studentName = null;
        if (principal instanceof UserDetails) {
            studentName = ((UserDetails) principal).getUsername();
        } else {
            studentName = principal.toString();
        }
        return studentName;
    }

    private boolean isStudent(String username) {
        Student student = null;
        try {
            student = studentService.findStudentByName(username);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    private int calculateMoney(List<Borrowed> borroweds) {
        int money = 0;
        if (!borroweds.isEmpty()) {
            money = borroweds
                    .stream()
                    .map(obj -> obj.getPrice() * obj.getBorrowedAmount())
                    .reduce(0, Integer::sum);
        }
        return money;
    }
}
