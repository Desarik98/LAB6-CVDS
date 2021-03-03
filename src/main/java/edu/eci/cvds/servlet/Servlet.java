package edu.eci.cvds.servlet;

import edu.eci.cvds.servlet.model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(
        urlPatterns = "/todos"
)

public class Servlet extends HttpServlet {
    static final long serialVersionUID = 35L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Todo> todoList = new ArrayList<Todo>();
        Writer responseWriter = resp.getWriter();
        try {
            Optional<String> optName = Optional.ofNullable(req.getParameter("id"));
            int id = optName.isPresent() && !optName.get().isEmpty() ? Integer.parseInt(optName.get()) : null;
            todoList.add(Service.getTodo(id));
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("text/html");
            responseWriter.write(Service.todosToHTMLTable(todoList));
        }
        catch (MalformedURLException e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseWriter.write("malformed");
        }
        catch (NumberFormatException | NullPointerException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseWriter.write("requerimiento invalido");
        }
        catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseWriter.write("requerimiento invalido");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Todo> todoList = new ArrayList<Todo>();
        Writer responseWriter = resp.getWriter();
        try {
            Optional<String> optName = Optional.ofNullable(req.getParameter("id"));
            int id = optName.isPresent() && !optName.get().isEmpty() ? Integer.parseInt(optName.get()) : null;
            todoList.add(Service.getTodo(id));
            resp.setContentType("text/html");
            resp.setStatus(HttpServletResponse.SC_OK);
            responseWriter.write(Service.todosToHTMLTable(todoList));
        }
        catch (MalformedURLException e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseWriter.write("malformed");
        }
        catch (NumberFormatException | NullPointerException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseWriter.write("requerimiento invalido");
        }
        catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseWriter.write("requerimiento invalido");
        }
    }
}
