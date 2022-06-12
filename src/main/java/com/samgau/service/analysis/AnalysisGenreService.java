package com.samgau.service.analysis;

import com.samgau.entity.analysis.AnalysisGenre;

import java.util.List;

public interface AnalysisGenreService {
    List<AnalysisGenre> findAllAnalysisGenre();

    AnalysisGenre findAnalysisGenreByName(String name);

    void saveAnalysisGenre(AnalysisGenre analysisGenre);

    void updateAnalysisGenre(AnalysisGenre analysisGenre);
}
