package net.sunnikolay.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "Handler get and post request",
        urlPatterns = "/worker_request"
)
public class HandlerGetPost extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.basicHandler( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        this.basicHandler( req,resp );
    }

    private void basicHandler( HttpServletRequest request, HttpServletResponse response ) throws IOException {
        // Установка кодировки запроса
        request.setCharacterEncoding( "UTF-8" );

        // Вывод в консоль
        System.out.println( "Start worker_request servlet" );

        // Установка кодировки ответа
        response.setContentType( "text/html" );
        response.setCharacterEncoding( "UTF-8" );

        String firstName = request.getParameter( "firstName" );
        String lastName = request.getParameter( "lastName" );
        System.out.println( "incoming data=" + firstName + ", " + lastName );

        PrintWriter out = response.getWriter();
        out.println( firstName + ": " + lastName );
        out.close();
    }
}
