package com.samgau.service.analysis.impl;

import com.samgau.entity.analysis.AnalysisAuthor;
import com.samgau.exception.NotFoundException;
import com.samgau.repository.analysis.AnalysisAuthorRepository;
import com.samgau.service.analysis.AnalysisAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisAuthorServiceImpl implements AnalysisAuthorService {

    private final AnalysisAuthorRepository analysisAuthorRepository;

    @Autowired
    public AnalysisAuthorServiceImpl(AnalysisAuthorRepository analysisAuthorRepository) {
        this.analysisAuthorRepository = analysisAuthorRepository;
    }

    @Override
    public List<AnalysisAuthor> findAllAnalysisAuthor() {
        return analysisAuthorRepository.findAll();
    }

    @Override
    public AnalysisAuthor findAnalysisAuthorByName(String name) {
        return analysisAuthorRepository.findAll().stream()
                .filter(obj -> obj.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new NotFoundException("AnalysisAuthor not found"));
    }

    @Override
    public void saveAnalysisAuthor(AnalysisAuthor analysisAuthor) {
        analysisAuthorRepository.save(analysisAuthor);
    }

    @Override
    public void updateAnalysisAuthor(AnalysisAuthor analysisAuthor) {
        analysisAuthorRepository.save(analysisAuthor);
    }
}
