package app.entities;

import app.model.MyConnectionProvider;
import app.model.TaskStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;


public class TaskDAOImpl implements TaskDAO {
    private Connection con;
    private PreparedStatement ps;
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

    @Override
    public ArrayList<Task> getTasks(String username, TaskStatus status) {
        Task t = new Task();
        try {
            con = MyConnectionProvider.getCon();
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

        } catch (Exception e) {
            System.out.println(e);
        }

        return listOfTask;
    }

    @Override
    public ArrayList<Task> getAllTasks(TaskStatus status) {
        Task t = new Task();
        try {
            con = MyConnectionProvider.getCon();
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

        } catch (Exception e) {
            System.out.println(e);
        }

        return listOfTask;
    }

    @Override
    public int insertTask(Task t) {
        int status = 0;
        try {
            con= MyConnectionProvider.getCon();
            ps=con.prepareStatement("insert into task values(?,?,?,?,?,?,?)");
            ps.setString(1,null);
            ps.setString(2,t.getTaskName());
            ps.setString(3,t.getStartTime());
            ps.setString(4,null);
            ps.setString(5,t.getStatus());
            ps.setString(6,t.getUserName());
            ps.setString(7,t.getDescription());
            status=ps.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return status;
    }

    @Override
    public int changeStatus(String id, String s) {

        int status = 0;
        try {
            con= MyConnectionProvider.getCon();

                java.util.Date dt = new java.util.Date();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(dt);

                ps = con.prepareStatement("UPDATE task SET endtime=?, status=?  WHERE idtask=?");
                ps.setString(1, currentTime);
                ps.setString(2,s);
                ps.setString(3, id);
                status = ps.executeUpdate();
                con.close();


        }catch (Exception e){
            System.out.println(e);
        }
        return status;

    }


}
