package com.samgau.service;

import com.samgau.entity.Author;

import java.util.List;

public interface AuthorService {

	List<Author> findAllAuthors();

	List<Author> searchAuthors(String keyword);

	Author findAuthorById(Long id);

	void createAuthor(Author author);

	void updateAuthor(Author author);

	void deleteAuthor(Long id);

}
