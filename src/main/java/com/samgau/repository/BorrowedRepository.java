package com.samgau.repository;

import com.samgau.entity.Borrowed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BorrowedRepository extends JpaRepository<Borrowed, Long> {
}
