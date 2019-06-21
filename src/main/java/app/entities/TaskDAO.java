package app.entities;

import app.model.TaskStatus;

import java.util.ArrayList;

public interface TaskDAO {


    public ArrayList<Task> getTasks(String username,TaskStatus status);

    public ArrayList<Task> getAllTasks(TaskStatus status);

    public int insertTask(Task t);

    public int changeStatus(String id, String s);

}
