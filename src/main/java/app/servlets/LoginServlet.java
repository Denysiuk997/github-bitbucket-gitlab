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

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (MyFunction.getCookie(req, "username") != null) {
            MyFunction.clearCookies(req, resp);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();

        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String submitType = req.getParameter("submit");

        Customer c = customerDAO.getCustomer(userName, password);
        System.out.println(c.getUserName() + " " + c.getRole());

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
