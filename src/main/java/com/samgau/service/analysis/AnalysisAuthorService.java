package com.samgau.service.analysis;

import com.samgau.entity.analysis.AnalysisAuthor;

import java.util.List;

public interface AnalysisAuthorService {
    List<AnalysisAuthor> findAllAnalysisAuthor();

    AnalysisAuthor findAnalysisAuthorByName(String name);

    void saveAnalysisAuthor(AnalysisAuthor analysisAuthor);

    void updateAnalysisAuthor(AnalysisAuthor analysisAuthor);
}
