package app.servlets.user;

import app.entities.Customer;
import app.entities.CustomerDAOImpl;
import app.entities.Task;
import app.entities.TaskDAOImpl;
import app.model.TaskStatus;
import app.servlets.MyFunction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String userName = MyFunction.getCookie(req, "username");
//        if(userName!=null){
//            req.getRequestDispatcher("views/user/requestTask.jsp").forward(req, resp);
//        }else {
//            resp.sendRedirect("http://localhost:8080/TimeTracking_war_exploded/login");
//        }



        String userRole = MyFunction.getCookie(req, "userRole");
        if (userRole.equalsIgnoreCase("admin")) {
            resp.sendRedirect("http://localhost:8080/TimeTracking_war_exploded/response");
        } else if (userRole != "") {
            req.getRequestDispatcher("views/user/requestTask.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("http://localhost:8080/TimeTracking_war_exploded/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        String taskName = req.getParameter("taskname");
        String description = req.getParameter("description");
        String submitType = req.getParameter("submit");

        Task task = new Task();
        TaskDAOImpl taskDAO = new TaskDAOImpl();

      String  userName = MyFunction.getCookie(req, "username");


        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);


        if (submitType.equals("request")) {
            if (taskName == "" || description == "") {
                req.setAttribute("errormessage", "Fill in all fields!!!");
                req.getRequestDispatcher("views/user/requestTask.jsp").forward(req, resp);
            } else {

                task.setTaskName(taskName);
                task.setStartTime(currentTime);
                task.setUserName(userName);
                task.setStatus(TaskStatus.REQUEST.getName());
                task.setDescription(description);
                taskDAO.insertTask(task);
                req.setAttribute("successmessage", "Task request create!!");
                req.getRequestDispatcher("views/user/requestTask.jsp").forward(req, resp);
            }
        }

    }
}
