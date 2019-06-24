/**
 * Данный класс имлементирует интерфейс TaskDAO. при помощи него Выполняется внисение и получение задания с БД а также изменение статуса.
 * Клас имеет два свойства. con - для хранения соединения с БД и ps для хранения запроса к БД
 * <b>con</b>, <b>ps</b>
 *
 * @author Yevhenii
 * @version 1.0
 */

package app.entities;

import app.model.ConnectionPool;
import app.model.MyConnectionProvider;
import app.model.TaskStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;


public class TaskDAOImpl implements TaskDAO {
    /** Свойство - con */
    private Connection con;
    /** Свойство - ps */
    private PreparedStatement ps;
    /** Свойство - listOfTasks - для хранения обьектов класа таск */
    private ArrayList<Task> listOfTask = new ArrayList<>();


    public Task newTaskObject(Task c) {
        Task newTask = new Task();
        newTask.setIdTask(c.getIdTask());
        newTask.setUserName(c.getUserName());
        newTask.setStatus(c.getStatus());
        newTask.setEndTime(c.getEndTime());
        newTask.setStartTime(c.getStartTime());
        newTask.setTaskName(c.getTaskName());
        newTask.setDescription(c.getDescription());
        return newTask;
    }


    /** Данный метод осуществляет создание списка обектов заданий, которые закрепленны указаному пользователю и имеют указаный статус
     @param username принимает Username пользователя
     @param status принимает статус задания
     @return возвращает лист обектов класа Task
     */
    @Override
    public ArrayList<Task> getTasks(String username, TaskStatus status) {
        Task t = new Task();
        try {
//            con = MyConnectionProvider.getCon();
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement("select * from task where username=? and status =?");
            ps.setString(1, username);
            ps.setString(2, status.getName());

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                t.setIdTask(rs.getInt(1));
                t.setTaskName(rs.getString(2));
                t.setStartTime(rs.getString(3));
                t.setEndTime(rs.getString(4));
                t.setStatus(rs.getString(5));
                t.setUserName(rs.getString(6));
                t.setDescription(rs.getString(7));
                listOfTask.add(newTaskObject(t));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return listOfTask;
    }


    /** Данный метод осуществляет создание списка обектов заданий, которые имеют один и тот же статус
     @param status принимает статус задания
     @return возвращает лист обектов класа Task
     */
    @Override
    public ArrayList<Task> getAllTasks(TaskStatus status) {
        Task t = new Task();
        try {
//            con = MyConnectionProvider.getCon();
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement("select * from task where status =?");
            ps.setString(1, status.getName());
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                t.setIdTask(rs.getInt(1));
                t.setTaskName(rs.getString(2));
                t.setStartTime(rs.getString(3));
                t.setEndTime(rs.getString(4));
                t.setStatus(rs.getString(5));
                t.setUserName(rs.getString(6));
                t.setDescription(rs.getString(7));
                listOfTask.add(newTaskObject(t));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }



        return listOfTask;
    }

    /** Данный метод осуществляет внесение данных нового задания в БД
     @param t принимает обект готового класа Task
     @return возвращает статус операции
     */
    @Override
    public int insertTask(Task t) {
        int status = 0;
        try {
//            con= MyConnectionProvider.getCon();
            con = ConnectionPool.getInstance().getConnection();
            ps = con.prepareStatement("insert into task values(?,?,?,?,?,?,?)");
            ps.setString(1, null);
            ps.setString(2, t.getTaskName());
            ps.setString(3, t.getStartTime());
            ps.setString(4, null);
            ps.setString(5, t.getStatus());
            ps.setString(6, t.getUserName());
            ps.setString(7, t.getDescription());
            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    /** Данный метод осуществляет изминения статуса конкретного задания
     @param id принимает id задания
     @param s принимает новый статус. который следует присвоить заданию
     @return возвращает статус операции
     */
    @Override
    public int changeStatus(String id, String s) {

        int status = 0;
        try {
//            con= MyConnectionProvider.getCon();
            con = ConnectionPool.getInstance().getConnection();
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);

            ps = con.prepareStatement("UPDATE task SET endtime=?, status=?  WHERE idtask=?");
            ps.setString(1, currentTime);
            ps.setString(2, s);
            ps.setString(3, id);
            status = ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;

    }


}
