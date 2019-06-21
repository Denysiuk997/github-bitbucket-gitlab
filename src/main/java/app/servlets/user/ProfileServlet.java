package app.servlets.user;

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
import java.io.PrintWriter;

public class ProfileServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userRole = MyFunction.getCookie(req, "userRole");
        if (userRole.equalsIgnoreCase("admin")) {
            resp.sendRedirect("http://localhost:8080/TimeTracking_war_exploded/dashboard");
        } else if (userRole != "") {
            req.getRequestDispatcher("views/user/profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("http://localhost:8080/TimeTracking_war_exploded/login");
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = MyFunction.getCookie(req, "username");

        String submitType = req.getParameter("submit");
        char lastChar = submitType.charAt(submitType.length() - 1);
        String idTask = submitType.substring(0, submitType.length() - 1);
        TaskDAOImpl taskDAO = new TaskDAOImpl();


        if (submitType.equals("Active tasks") || lastChar == 'F' || lastChar == 'C') {
            if (lastChar == 'F') {
                taskDAO.changeStatus(idTask, TaskStatus.FINISH.getName());
                req.setAttribute("messageChangeStatus", "Task Finish");
            }
            if (lastChar == 'C') {
                taskDAO.changeStatus(idTask, TaskStatus.CANCEL.getName());
                req.setAttribute("messageChangeStatus", "Task Cancel");
            }
            req.setAttribute("messageGroupTask", "Active tasks");
            req.setAttribute("activeTasks", taskDAO.getTasks(userName, TaskStatus.MAKING));
            req.getRequestDispatcher("views/user/profile.jsp").forward(req, resp);

        } else if (submitType.equals("Received tasks") || lastChar == 'S' || lastChar == 'V') {
            if (lastChar == 'S') {
                taskDAO.changeStatus(idTask, TaskStatus.MAKING.getName());
                req.setAttribute("messageChangeStatus", "Task Start");
            }
            if (lastChar == 'V') {
                taskDAO.changeStatus(idTask, TaskStatus.CANCEL.getName());
                req.setAttribute("messageChangeStatus", "Cancel Task");
            }
            req.setAttribute("messageGroupTask", "Received tasks");
            req.setAttribute("reciveTasks", taskDAO.getTasks(userName, TaskStatus.RESPONSE));
            req.getRequestDispatcher("views/user/profile.jsp").forward(req, resp);

        } else if (submitType.equals("Request tasks") || lastChar == 'B') {

            if (lastChar == 'B') {
                taskDAO.changeStatus(idTask, TaskStatus.CANCEL.getName());
                req.setAttribute("messageChangeStatus", "Cancel Task");
            }

            req.setAttribute("messageGroupTask", "Request tasks");
            req.setAttribute("requestTasks", taskDAO.getTasks(userName, TaskStatus.REQUEST));
            req.getRequestDispatcher("views/user/profile.jsp").forward(req, resp);

        } else if (submitType.equals("Finish tasks")) {
            req.setAttribute("messageGroupTask", "Finish tasks");
            req.setAttribute("finishTasks", taskDAO.getTasks(userName, TaskStatus.FINISH));
            req.getRequestDispatcher("views/user/profile.jsp").forward(req, resp);
        }


    }
}
