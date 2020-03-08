package net.sunnikolay.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "Simple Servlet",
        description = "Simple example for java servlet",
        urlPatterns = "/myservlet"
)
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        // Вывод в консоль
        System.out.println( "Start myservlet" );

        // Установка кодировки ответа
        resp.setContentType( "text/html" );
        resp.setCharacterEncoding( "UTF-8" );

        PrintWriter out = resp.getWriter();
        out.println( "<h3>Hello from servlet</h3>" );
        out.println( "<small>Проверка...</small>" );
        out.close();
    }

}
