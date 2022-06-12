package com.samgau.service.analysis;

import com.samgau.entity.analysis.AnalysisBook;

import java.util.List;

public interface AnalysisBookService {
    List<AnalysisBook> findAllAnalysisBook();

    AnalysisBook findAnalysisBookByName(String name);

    void saveAnalysisBook(AnalysisBook analysisBook);

    void updateAnalysisBook(AnalysisBook analysisBook);
}
