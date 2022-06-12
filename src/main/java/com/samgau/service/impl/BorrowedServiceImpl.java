package com.samgau.service.impl;

import com.samgau.entity.Borrowed;
import com.samgau.exception.NotFoundException;
import com.samgau.repository.BorrowedRepository;
import com.samgau.service.BorrowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowedServiceImpl implements BorrowedService {

    private final BorrowedRepository borrowedRepository;

    @Autowired
    public BorrowedServiceImpl(BorrowedRepository borrowedRepository) {
        this.borrowedRepository = borrowedRepository;
    }

    @Override
    public List<Borrowed> findAllBorrowedBooks() {
        return borrowedRepository.findAll();
    }

    @Override
    public Borrowed findBorrowedById(Long id) {
        return borrowedRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Borrowed not found with ID %d", id)));
    }

    @Override
    public void createBorrowed(Borrowed borrowed) {
        borrowedRepository.save(borrowed);
    }

    @Override
    public void updateBorrowed(Borrowed borrowed) {
        borrowedRepository.save(borrowed);
    }

    @Override
    public void deleteBorrowed(Long id) {
        final Borrowed borrowed = borrowedRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Borrowed not found with ID %d", id)));

        borrowedRepository.deleteById(borrowed.getBorrowedId());
    }
}
