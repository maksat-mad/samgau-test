package com.samgau.service;

import com.samgau.entity.Borrowed;

import java.util.List;

public interface BorrowedService {
    List<Borrowed> findAllBorrowedBooks();

    Borrowed findBorrowedById(Long id);

    void createBorrowed(Borrowed borrowed);

    void updateBorrowed(Borrowed borrowed);

    void deleteBorrowed(Long id);
}
