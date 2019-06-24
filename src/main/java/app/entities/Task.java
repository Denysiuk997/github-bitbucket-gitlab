/** Класс служит для хранения объекта - задания со свойствами
 <b>idTask</b>, <b>TaskName</b>, <b>startTime</b>,<b>endTime</b>,<b>status</b>,<b>userName</b>,<b>description</b>
 @author Yevhenii
 @version 1.0
 */

package app.entities;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDateTime;

public class Task {
    /** Свойство - idTask */
    private int idTask;
    /** Свойство - TaskName */
    private String TaskName;
    /** Свойство - startTime */
    private String startTime;
    /** Свойство - endTime */
    private String endTime;
    /** Свойство - status предзначеное для проверки текущего состояния задачи */
    private String status;
    /** Свойство - userName для закрипления пользователя к заданию */
    private String userName;
    /** Свойство - description  описание задачи*/
private String description;

    /** Получает значение свойства description, которое можно задать с помощью метода {@link #setDescription(String)}
     @return Значение свойства description
     */
    public String getDescription() {
        return description;
    }

    /** Задает значение свойства description, которое можно получить при помощи метода {@link #getDescription()}
     @param description Новое значение свойства description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Получает значение свойства idTask, которое можно задать с помощью метода {@link #setIdTask(int)}
     @return Значение свойства idtask
     */
    public int getIdTask() {
        return idTask;
    }

    /** Задает значение свойства idTask, которое можно получить при помощи метода {@link #getIdTask()}
     @param idTask Новое значение свойства idTask
     */
    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    /** Получает значение свойства taskName, которое можно задать с помощью метода {@link #setTaskName(String)}
     @return Значение свойства taskName
     */
    public String getTaskName() {
        return TaskName;
    }
    /** Задает значение свойства taskName, которое можно получить при помощи метода {@link #getTaskName()}
     @param taskName Новое значение свойства taskName
     */
    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    /** Получает значение свойства startTime, которое можно задать с помощью метода {@link #setStartTime(String)}
     @return Значение свойства startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /** Задает значение свойства startTime, которое можно получить при помощи метода {@link #getStartTime()}
     @param startTime Новое значение свойства startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /** Получает значение свойства endTime, которое можно задать с помощью метода {@link #setEndTime(String)}
     @return Значение свойства endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /** Задает значение свойства endTime, которое можно получить при помощи метода {@link #getEndTime()}
     @param endTime Новое значение свойства endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    /** Получает значение свойства status, которое можно задать с помощью метода {@link #setStatus(String)}
     @return Значение свойства status
     */
    public String getStatus() {
        return status;
    }
    /** Задает значение свойства status, которое можно получить при помощи метода {@link #getStatus()}
     @param status Новое значение свойства status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /** Получает значение свойства userName, которое можно задать с помощью метода {@link #setUserName(String)}
     @return Значение свойства endTime
     */
    public String getUserName() {
        return userName;
    }

    /** Задает значение свойства userName, которое можно получить при помощи метода {@link #getUserName()}
     *  @param userName Новое значение свойства userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
