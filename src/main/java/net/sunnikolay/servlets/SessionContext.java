package net.sunnikolay.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "Example parameters sessions and cookie",
        urlPatterns = "/session_cookie",
        loadOnStartup = 0 // Загружать сервлет при старте сервера
)
public class SessionContext extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println( "Вызов метода при инициализации сервлета" );
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.basicHandler( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.basicHandler( request, response );
    }

    private void basicHandler( HttpServletRequest request, HttpServletResponse response ) throws IOException {
        // Установка кодировки запроса
        request.setCharacterEncoding( "UTF-8" );

        // Вывод в консоль
        System.out.println( "Start session_cookie servlet" );

        // Установка кодировки ответа
        response.setContentType( "text/html" );
        response.setCharacterEncoding( "UTF-8" );

        String         user    = request.getParameter( "user" );

        // Сессия http достпна только для конкретного пользователя
        HttpSession    session = request.getSession();

        // Контекст сервлета доступен для всех клиентов... !!!
        ServletContext context = request.getServletContext();

        if ( user != "" && user != null ) {
            session.setAttribute( "user", user );
            context.setAttribute( "user", user );
        }

        // Установить время хранения сессии
        session.setMaxInactiveInterval( 1800 );

        PrintWriter out = response.getWriter();
        out.println( "Request parameter: " + user + "</br>" );
        out.println( "Session parameter: " + session.getAttribute( "user" ) + "</br>" );
        out.println( "Context parameter: " + context.getAttribute( "user" ) + "</br>" );
        out.println( "<a href='http://localhost:8080/JavaPro_WEB_Web_exploded/form_session_cookie.html'>Назад</a></br>" );
        out.close();

        // Показать время существования сессии
        System.out.println( "session time in seconds: " + session.getMaxInactiveInterval() );
    }

}
