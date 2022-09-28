package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService extends DatabaseService {

    public List<Book> getAllbooks() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT Title FROM Books;")
                        .mapToBean(Book.class)
                        .list()
        );
    }

    public void addBook(Book book) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO Books (Author, Title, ISBN, NumberOfCopies) " +
                                "VALUES (:Author, :Title, :ISBN, :NumberOfCopies)")
                        .bind("Author", book.getAuthor())
                        .bind("Title", book.getTitle())
                        .bind("ISBN", book.getIsbn())
                        .bind("NumberOfCopies", book.getNumberOfCopies())
                        .execute()
        );
    }

    public void deleteBook(int bookId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM Books WHERE BookID = :bookId")
                        .bind("id", bookId)
                        .execute()
        );
    }
}