package com.samgau.repository.analysis;

import com.samgau.entity.analysis.AnalysisBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AnalysisBookRepository extends JpaRepository<AnalysisBook, Long> {
}
