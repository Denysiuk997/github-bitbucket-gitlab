/**
 * Данный класс является сервлетом для регистрации пользователей. Отображение выполняется при помощи файла views/register.jsp
 *
 * @author Yevhenii
 * @version 1.0
 */

package app.servlets;

import app.entities.Customer;
import app.entities.CustomerDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Данный метод выполняет перенаправление на файл views/register.jsp
 */
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    /** Данный метод выполняет обработку пост запроса со страницы регистрации. Если данные пользователь ввел коректные - метод выполняет регистрацию нового пользователя и перенаправляет его на страницу авторизации
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        String userName = req.getParameter("userName").toLowerCase();
        String name = req.getParameter("name").toLowerCase();
        String password1 = req.getParameter("password1").toLowerCase();
        String password2 = req.getParameter("password2").toLowerCase();
        String sumbitType = req.getParameter("submit").toLowerCase();

        Customer c = new Customer();

        if (sumbitType.equals("register")) {
            if(userName==""||name==""||password1==""||password2==""){
                req.setAttribute("errormessage", "Fill in all fields!!!");
                req.getRequestDispatcher("views/register.jsp").forward(req, resp);
            }else if(customerDAO.checkCustomer(userName)){
                req.setAttribute("errormessage", "This username is in use, please select another one!!!");
                req.getRequestDispatcher("views/register.jsp").forward(req, resp);
            }else if (!password1.equals(password2)) {
                req.setAttribute("errormessage", "Password are different!!!");
                req.getRequestDispatcher("views/register.jsp").forward(req, resp);
            }else if(password1.length()<4 || userName.length()<4){
                req.setAttribute("errormessage", "Password or login name are short!!!");
                req.getRequestDispatcher("views/register.jsp").forward(req, resp);
            }else {
                c.setName(name);
                c.setUserName(userName);
                c.setPassword(password1);
                customerDAO.insertCustomer(c);
                req.setAttribute("successmessage", "Registration done!!!");
                req.getRequestDispatcher("views/login.jsp").forward(req, resp);
            }
        }

    }
}
