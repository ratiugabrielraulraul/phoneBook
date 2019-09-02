package org.fasttrackit.persistance;

import org.fasttrackit.domain.Book;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    public void createBook(String firstName, String lastName, int phoneNumber) throws SQLException, IOException, ClassNotFoundException {
        String insertSql = "INSERT INTO book (first_name, last_name , phone_number) VALUES (?,?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, phoneNumber);

            preparedStatement.executeUpdate();

        }
    }

    public List<Book> getBook() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT id, first_name ,last_name, phone_number FROM book";
        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(query);

            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setFirstName(resultSet.getString("first_name"));
                book.setLastName(resultSet.getString("last_name"));
                book.setPhoneNumber(resultSet.getInt("phone_number"));
                books.add(book);
            }
            return books;
        }


    }

    public void deleteBook(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM book WHERE id= ?";
        try (Connection connection = DatabaseConfiguration.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        }

    }

    public void updateBook(long id, String lastName) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE book SET `last_name` = ? WHERE id = ? ";

         try(Connection connection = DatabaseConfiguration.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             preparedStatement.setLong(1,id);
             preparedStatement.setString(2,lastName);
             preparedStatement.executeUpdate();
         }

    }


}
