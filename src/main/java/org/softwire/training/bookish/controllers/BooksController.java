package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Technology;
import org.softwire.training.bookish.models.page.AboutPageModel;
import org.softwire.training.bookish.models.page.BookPageModel;
import org.softwire.training.bookish.services.BooksService;
import org.softwire.training.bookish.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @RequestMapping("")
    ModelAndView books() {

        List<Book> allBooks = booksService.getAllbooks();

        BookPageModel bookPageModel = new BookPageModel();
        bookPageModel.setBooks(allBooks);

        return new ModelAndView("books", "model", bookPageModel);
    }

    @RequestMapping("/add-books")
    RedirectView addBook(@ModelAttribute Book book) {

        booksService.addBook(book);

        return new RedirectView("/books");
    }

    @RequestMapping("/delete-book")
    RedirectView deleteBook(@RequestParam int bookId) {

        booksService.deleteBook(bookId);

        return new RedirectView("/books");
    }
}