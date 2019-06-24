/**
 * Данный класс является сервлетом для авторизации пользователей. Отображение выполняется при помощи файла views/login.jsp
 *
 * @author Yevhenii
 * @version 1.0
 */
package app.servlets;

import app.entities.Customer;
import app.entities.CustomerDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/** Данный метод выполняет перенаправление на файл views/register.jsp
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (MyFunction.getCookie(req, "username") != null) {
            MyFunction.clearCookies(req, resp);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    /** Данный метод выполняет обработку пост запроса со страницы авторизации. Если данные пользователь ввел коректные - метод выполняет авторизацию пользователя
     * и сохраняет его userName в куки  и перенаправляет его на страницу его профиля
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("username").toLowerCase();
        String password = req.getParameter("password").toLowerCase();
        String submitType = req.getParameter("submit").toLowerCase();

        Customer c = customerDAO.getCustomer(userName, password);


        if (submitType.equals("login") && c.getName() != null && c != null) {


            // set cookie//
            Cookie coo = new Cookie("username", c.getUserName());
            Cookie cookie = new Cookie("userRole", c.getRole());
            coo.setMaxAge(-1);
            cookie.setMaxAge(-1);
            resp.addCookie(cookie);
            resp.addCookie(coo);
            req.setAttribute("message", "Hello, " + c.getName() + "!!!");
            if(c.getRole().equalsIgnoreCase("admin")){
                resp.sendRedirect("http://localhost:8080/TimeTracking_war_exploded/dashboard");
            }else{
                resp.sendRedirect("http://localhost:8080/TimeTracking_war_exploded/profile");
            }
//            req.getRequestDispatcher("/index.html").forward(req, resp);

        } else {
            req.setAttribute("message", "This login or password invalid");
            req.getRequestDispatcher("views/login.jsp").forward(req, resp);
        }


    }

}
