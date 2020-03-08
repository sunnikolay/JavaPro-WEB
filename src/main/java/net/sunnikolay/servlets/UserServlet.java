package net.sunnikolay.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(
        name = "User Servlet",
        urlPatterns = "/user_servlet",
        loadOnStartup = 0
)
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Синхранизированная коллекция, предназначенна для работы в многопоточной системе
     */
    private Map<Integer, String> users = new ConcurrentHashMap<>();

    /**
     * Синхронизированный integer, для многопоточного обращения
     */
    private AtomicInteger counter;

    @Override
    public void init() throws ServletException {
        super.init();
        users.put( 1, "Ivan" );
        users.put( 2, "Alex" );
        users.put( 3, "John" );
        counter = new AtomicInteger( 3 );
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // Установка кодировки запроса
        request.setCharacterEncoding( "UTF-8" );

        // Установка кодировки ответа
        response.setContentType( "text/html" );
        response.setCharacterEncoding( "UTF-8" );

        Integer id   = Integer.parseInt( request.getParameter( "id" ) );
        String  user = users.get( id );
        if ( user == null ) user = "";

        PrintWriter out = response.getWriter();
        out.println( "<h3>user: " + user + "</h3><hr>" );
        out.println( "<a href='javascript:history.back();'>Назад</a>" );
        out.close();
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // Установка кодировки запроса
        request.setCharacterEncoding( "UTF-8" );

        // Установка кодировки ответа
        response.setContentType( "text/html" );
        response.setCharacterEncoding( "UTF-8" );

        String name = request.getParameter( "name" );
        Integer id = null;

        if ( !users.containsValue( name ) ) {
            id = counter.incrementAndGet();
            users.put( id, name );
        }

        PrintWriter out = response.getWriter();
        if ( id == null ) {
            out.println( "<h3>user " + name + " уже существует...</h3>" );
        }
        else {
            out.println( "<h3>Создан user " + name + " с id=" + counter.get() + "</h3>" );
        }

        out.println( "<hr>" );
        out.println( "<a href='http://localhost:8080/JavaPro_WEB_Web_exploded/user.html'>Главная</a>" );
        out.close();
    }

}
