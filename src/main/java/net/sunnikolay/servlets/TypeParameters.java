package net.sunnikolay.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;

@WebServlet(
        name = "Example parameters",
        urlPatterns = "/type_parameters"
)
public class TypeParameters extends HttpServlet {

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
        String[] jobs = request.getParameterValues( "job" );
        String gender = request.getParameter( "gender" );
        String age18 = request.getParameter( "age18" );
        System.out.println( "incoming data=" + firstName + "," + lastName + "," + Arrays.deepToString( jobs ) + "," + gender + "," + age18 );

        PrintWriter out = response.getWriter();
        out.println( "<h3>Профиль сотрудника</h3>" );
        out.println( "Имя: " + firstName + "<br>" );
        out.println( "Фамилия: " + lastName + "<br>" );
        out.println( "Количество ролей: " + jobs.length + "<br>" );
        out.println( "Профессии: " + Arrays.deepToString( jobs ) + "<br>" );
        out.println( "Пол: " + gender + "<br>" );
        out.println( "Старше 18: " + age18 + "<br>" );
        out.close();
    }

}
