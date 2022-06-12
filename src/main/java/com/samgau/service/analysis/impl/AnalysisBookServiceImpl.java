package com.samgau.service.analysis.impl;

import com.samgau.entity.analysis.AnalysisBook;
import com.samgau.exception.NotFoundException;
import com.samgau.repository.analysis.AnalysisBookRepository;
import com.samgau.service.analysis.AnalysisBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisBookServiceImpl implements AnalysisBookService {

    private final AnalysisBookRepository analysisBookRepository;

    @Autowired
    public AnalysisBookServiceImpl(AnalysisBookRepository analysisBookRepository) {
        this.analysisBookRepository = analysisBookRepository;
    }


    @Override
    public List<AnalysisBook> findAllAnalysisBook() {
        return analysisBookRepository.findAll();
    }

    @Override
    public AnalysisBook findAnalysisBookByName(String name) {
        return analysisBookRepository.findAll().stream()
                .filter(obj -> obj.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new NotFoundException("AnalysisBook not found"));
    }

    @Override
    public void saveAnalysisBook(AnalysisBook analysisBook) {
        analysisBookRepository.save(analysisBook);
    }

    @Override
    public void updateAnalysisBook(AnalysisBook analysisBook) {
        analysisBookRepository.save(analysisBook);
    }
}
