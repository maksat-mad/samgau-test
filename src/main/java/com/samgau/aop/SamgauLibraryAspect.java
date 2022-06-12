package com.samgau.aop;

import com.samgau.entity.analysis.AnalysisAuthor;
import com.samgau.entity.analysis.AnalysisBook;
import com.samgau.entity.analysis.AnalysisGenre;
import com.samgau.service.analysis.AnalysisAuthorService;
import com.samgau.service.analysis.AnalysisBookService;
import com.samgau.service.analysis.AnalysisGenreService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SamgauLibraryAspect {

    private final AnalysisAuthorService analysisAuthorService;
    private final AnalysisBookService analysisBookService;
    private final AnalysisGenreService analysisGenreService;

    @Autowired
    public SamgauLibraryAspect(AnalysisAuthorService analysisAuthorService, AnalysisBookService analysisBookService, AnalysisGenreService analysisGenreService) {
        this.analysisAuthorService = analysisAuthorService;
        this.analysisBookService = analysisBookService;
        this.analysisGenreService = analysisGenreService;
    }

    @Pointcut("execution(* com.samgau.service.AuthorService.search*(..))")
    public void userServiceAuthorSearchMethods() {
    }

    @Pointcut("execution(* com.samgau.service.BookService.search*(..))")
    public void userServiceBookSearchMethods() {
    }

    @Pointcut("execution(* com.samgau.service.GenreService.search*(..))")
    public void userServiceGenreSearchMethods() {
    }

    @Before("userServiceAuthorSearchMethods()")
    public void saveAuthorSearchDetails(JoinPoint joinPoint) {
        Object[] signatureArgs = joinPoint.getArgs();
        AnalysisAuthor analysisAuthor = null;
        try {
            analysisAuthor = analysisAuthorService.findAnalysisAuthorByName( (String) signatureArgs[0]);
        } catch (Exception ex) {
            analysisAuthor = new AnalysisAuthor();
            analysisAuthor.setName( (String) signatureArgs[0]);
            analysisAuthor.setCount(0L);
        }
        analysisAuthor.setCount(analysisAuthor.getCount() + 1);
        analysisAuthorService.updateAnalysisAuthor(analysisAuthor);
    }

    @Before("userServiceBookSearchMethods()")
    public void saveBookSearchDetails(JoinPoint joinPoint) {
        Object[] signatureArgs = joinPoint.getArgs();
        AnalysisBook analysisBook = null;
        try {
            analysisBook = analysisBookService.findAnalysisBookByName( (String) signatureArgs[0]);
        } catch (Exception ex) {
            analysisBook = new AnalysisBook();
            analysisBook.setName( (String) signatureArgs[0]);
            analysisBook.setCount(0L);
        }
        analysisBook.setCount(analysisBook.getCount() + 1);
        analysisBookService.updateAnalysisBook(analysisBook);
    }

    @Before("userServiceGenreSearchMethods()")
    public void saveGenreSearchDetails(JoinPoint joinPoint) {
        Object[] signatureArgs = joinPoint.getArgs();
        AnalysisGenre analysisGenre = null;
        try {
            analysisGenre = analysisGenreService.findAnalysisGenreByName( (String) signatureArgs[0]);
        } catch (Exception ex) {
            analysisGenre = new AnalysisGenre();
            analysisGenre.setName( (String) signatureArgs[0]);
            analysisGenre.setCount(0L);
        }
        analysisGenre.setCount(analysisGenre.getCount() + 1);
        analysisGenreService.updateAnalysisGenre(analysisGenre);
    }
}
