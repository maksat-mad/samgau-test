package com.samgau.controller;

import com.samgau.entity.analysis.AnalysisAuthor;
import com.samgau.entity.analysis.AnalysisBook;
import com.samgau.entity.analysis.AnalysisGenre;
import com.samgau.service.analysis.AnalysisAuthorService;
import com.samgau.service.analysis.AnalysisBookService;
import com.samgau.service.analysis.AnalysisGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AnalysisController {

    private final AnalysisAuthorService analysisAuthorService;
    private final AnalysisBookService analysisBookService;
    private final AnalysisGenreService analysisGenreService;

    @Autowired
    public AnalysisController(AnalysisAuthorService analysisAuthorService, AnalysisBookService analysisBookService, AnalysisGenreService analysisGenreService) {
        this.analysisAuthorService = analysisAuthorService;
        this.analysisBookService = analysisBookService;
        this.analysisGenreService = analysisGenreService;
    }

    @GetMapping("/analysis")
    public String list() {
        return "librarian/analysis";
    }

    @RequestMapping("/analysisAuthor")
    public String findAnalysisAuthor(Model model) {
        final List<AnalysisAuthor> analysisAuthors = analysisAuthorService.findAllAnalysisAuthor();
        List<AnalysisAuthor> result = analysisAuthors.stream()
                .sorted((a, b) -> (int) (b.getCount() - a.getCount()))
                .collect(Collectors.toList());
        model.addAttribute("authors", result);
        return "librarian/list-analysis-authors";
    }

    @RequestMapping("/analysisBook")
    public String findAnalysisBook(Model model) {
        final List<AnalysisBook> analysisBook = analysisBookService.findAllAnalysisBook();
        List<AnalysisBook> result = analysisBook.stream()
                .sorted((a, b) -> (int) (b.getCount() - a.getCount()))
                .collect(Collectors.toList());
        model.addAttribute("books", result);
        return "librarian/list-analysis-books";
    }

    @RequestMapping("/analysisGenre")
    public String findAnalysisGenre(Model model) {
        final List<AnalysisGenre> analysisGenre = analysisGenreService.findAllAnalysisGenre();
        List<AnalysisGenre> result = analysisGenre.stream()
                .sorted((a, b) -> (int) (b.getCount() - a.getCount()))
                .collect(Collectors.toList());
        model.addAttribute("genres", result);
        return "librarian/list-analysis-genres";
    }
}
