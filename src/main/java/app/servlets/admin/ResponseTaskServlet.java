/**
 * Данный класс является сервлетом для возможности назначения задания пользователю. Отображение выполняется при помощи файла views/admin/responseTask.jsp
 *
 * @author Yevhenii
 * @version 1.0
 */

package app.servlets.admin;

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


/**
 * Данный метод выполняет проверку прав пользователя посещать даную страницу. в соответствии с его ролю выполняется перенаправление на
 * нужную страницу
 */
public class ResponseTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String userRole = MyFunction.getCookie(req, "userRole");
        if (userRole.equalsIgnoreCase("user")) {
            resp.sendRedirect("http://localhost:8080/TimeTracking_war_exploded/requestTask");
        } else if (userRole != "") {
            req.getRequestDispatcher("views/admin/responseTask.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("http://localhost:8080/TimeTracking_war_exploded/login");
        }



    }

    /** Данный метод выполняет обработку пост запросов со страницы отправки задания администратора пользувателю
   *Метод выполняет проверку данных, которые ввел пользователь. Если они коректны  - создается новое задание
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        req.setCharacterEncoding("UTF-8");
        String taskName = req.getParameter("taskname");
        String description = req.getParameter("description");
        String userName = req.getParameter("userName");
        String submitType = req.getParameter("submit");

        Task task = new Task();
        TaskDAOImpl taskDAO = new TaskDAOImpl();



        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);


        if (submitType.equals("response")) {
            if (taskName == "" || description == "" || userName=="") {
                req.setAttribute("errormessage", "Fill in all fields!!!");
                req.getRequestDispatcher("views/admin/responseTask.jsp").forward(req, resp);
            } else
                if(!customerDAO.checkCustomer(userName)) {
                    req.setAttribute("errormessage", "This username don't exist!!!");
                    req.getRequestDispatcher("views/admin/responseTask.jsp").forward(req, resp);
                }else {
                task.setTaskName(taskName);
                task.setStartTime(currentTime);
                task.setUserName(userName);
                task.setStatus(TaskStatus.RESPONSE.getName());
                task.setDescription(description);
                taskDAO.insertTask(task);
                req.setAttribute("successmessage", "Task response create!!");
                req.getRequestDispatcher("views/admin/responseTask.jsp").forward(req, resp);
            }
        }

    }
}
