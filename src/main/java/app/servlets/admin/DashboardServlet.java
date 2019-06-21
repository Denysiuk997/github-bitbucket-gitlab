package app.servlets.admin;

import app.entities.TaskDAOImpl;
import app.model.TaskStatus;
import app.servlets.MyFunction;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String userName = MyFunction.getCookie(req, "username");
//        if(userName!=null){
//            req.getRequestDispatcher("views/admin/dashboard.jsp").forward(req, resp);
//        }else {
//            resp.sendRedirect("http://localhost:8080/TimeTracking_war_exploded/login");
//        }
//


        String userRole = MyFunction.getCookie(req, "userRole");
        if (userRole.equalsIgnoreCase("user")) {
            resp.sendRedirect("http://localhost:8080/TimeTracking_war_exploded/profile");
        } else if (userRole != "") {
            req.getRequestDispatcher("views/admin/dashboard.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("http://localhost:8080/TimeTracking_war_exploded/login");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String submitType = req.getParameter("submit");
        Character lastChar = submitType.charAt(submitType.length() - 1);
        String idTask = submitType.substring(0, submitType.length() - 1);
        TaskDAOImpl taskDAO = new TaskDAOImpl();
        if (submitType.equals("Active tasks")) {

            req.setAttribute("messageGroupTask", "Active tasks");
            req.setAttribute("activeTasks", taskDAO.getAllTasks(TaskStatus.MAKING));
            req.getRequestDispatcher("views/admin/dashboard.jsp").forward(req, resp);
        } else if (submitType.equals("Received tasks") || lastChar == 'R' || lastChar == 'C') {

            if (lastChar == 'R') {
                taskDAO.changeStatus(idTask, TaskStatus.RESPONSE.getName());
                req.setAttribute("messageChangeStatus", "Task sent for user");
            }
            if (lastChar == 'C') {
                taskDAO.changeStatus(idTask, TaskStatus.CANCEL.getName());
                req.setAttribute("messageChangeStatus", "Task cancel");
            }
            req.setAttribute("messageGroupTask", "  tasks");
            req.setAttribute("reciveTasks", taskDAO.getAllTasks(TaskStatus.REQUEST));
            req.getRequestDispatcher("views/admin/dashboard.jsp").forward(req, resp);
        } else if (submitType.equals("Response tasks") || lastChar == 'V') {
            if (lastChar == 'V') {
                taskDAO.changeStatus(idTask, TaskStatus.CANCEL.getName());
                req.setAttribute("messageChangeStatus", "Task cancel");
            }
            req.setAttribute("messageGroupTask", "Request tasks");
            req.setAttribute("requestTasks", taskDAO.getAllTasks(TaskStatus.RESPONSE));
            req.getRequestDispatcher("views/admin/dashboard.jsp").forward(req, resp);
        } else if (submitType.equals("Finish tasks")) {

            req.setAttribute("messageGroupTask", "Finish tasks");
            req.setAttribute("finishTasks", taskDAO.getAllTasks(TaskStatus.FINISH));
            req.getRequestDispatcher("views/admin/dashboard.jsp").forward(req, resp);
        }else if (submitType.equals("Cancel tasks")) {
            req.setAttribute("messageGroupTask", "Cancel tasks");
            req.setAttribute("cancelTasks", taskDAO.getAllTasks(TaskStatus.CANCEL));
            req.getRequestDispatcher("views/admin/dashboard.jsp").forward(req, resp);
        }
    }
}
