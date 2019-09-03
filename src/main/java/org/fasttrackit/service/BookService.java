package org.fasttrackit.service;

import org.fasttrackit.domain.Book;
import org.fasttrackit.persistance.BookRepository;
import org.fasttrackit.transfer.SaveBookRequest;
import org.fasttrackit.transfer.UpdateBookRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookRepository bookRepository = new BookRepository();

    public void createBook(SaveBookRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating Book: " +request);
        bookRepository.createBook(request.getFirstName(), request.getLastName() , request.getPhoneNumber());

    }

    public List<Book> getBook() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving book...");
        return bookRepository.getBook();

    }

    public void deleteBook(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting book:" + id);
        bookRepository.deleteBook(id);

    }
    public void updateBook(long id, UpdateBookRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating book:" +id + ":" + request.getLastName());
        bookRepository.updateBook(id, request.getLastName());
    }



}

