package com.samgau.service;

import com.samgau.entity.Author;

import java.util.List;

public interface AuthorService {

	public List<Author> findAllAuthors();

	public List<Author> searchAuthors(String keyword);

	public Author findAuthorById(Long id);

	public void createAuthor(Author author);

	public void updateAuthor(Author author);

	public void deleteAuthor(Long id);

}
