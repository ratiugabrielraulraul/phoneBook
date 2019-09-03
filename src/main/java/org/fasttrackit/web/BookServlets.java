package org.fasttrackit.web;

import org.fasttrackit.config.ObjectMapperConfiguration;
import org.fasttrackit.domain.Book;
import org.fasttrackit.service.BookService;
import org.fasttrackit.transfer.SaveBookRequest;
import org.fasttrackit.transfer.UpdateBookRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/agenda")


public class BookServlets extends HttpServlet {

    private BookService bookService = new BookService();
    //endpoint
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        SaveBookRequest request =
                ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(), SaveBookRequest.class);


        try {
            bookService.createBook(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error:" + e.getMessage());
            resp.getWriter().flush();
            resp.getWriter().close();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        try {
            List<Book> toDoItem = bookService.getBook();
            String responseJson = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(toDoItem);
            resp.getWriter().print(responseJson);


        } catch (SQLException | ClassNotFoundException e) {

            resp.sendError(500, "Internal server error:" + e.getMessage());

        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        String id =req.getParameter("id");

        try {
            bookService.deleteBook(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendError(500, "Internal server error:" + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        String id =req.getParameter("id");

        UpdateBookRequest request =
                ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(),UpdateBookRequest.class);
        try {
            bookService.updateBook(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error:" + e.getMessage());
        }
    }
    //pre-flight requests
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);
    }
    //CORS (CROSS-Origin-Resource-Sharing)
    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Headers","content-type");
    }

}
