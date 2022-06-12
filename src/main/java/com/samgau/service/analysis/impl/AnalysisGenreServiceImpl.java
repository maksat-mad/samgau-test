package com.samgau.service.analysis.impl;

import com.samgau.entity.analysis.AnalysisGenre;
import com.samgau.exception.NotFoundException;
import com.samgau.repository.analysis.AnalysisGenreRepository;
import com.samgau.service.analysis.AnalysisGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisGenreServiceImpl implements AnalysisGenreService {

    private final AnalysisGenreRepository analysisGenreRepository;

    @Autowired
    public AnalysisGenreServiceImpl(AnalysisGenreRepository analysisGenreRepository) {
        this.analysisGenreRepository = analysisGenreRepository;
    }

    @Override
    public List<AnalysisGenre> findAllAnalysisGenre() {
        return analysisGenreRepository.findAll();
    }

    @Override
    public AnalysisGenre findAnalysisGenreByName(String name) {
        return analysisGenreRepository.findAll().stream()
                .filter(obj -> obj.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new NotFoundException("AnalysisGenre not found"));
    }

    @Override
    public void saveAnalysisGenre(AnalysisGenre analysisGenre) {
        analysisGenreRepository.save(analysisGenre);
    }

    @Override
    public void updateAnalysisGenre(AnalysisGenre analysisGenre) {
        analysisGenreRepository.save(analysisGenre);
    }
}
