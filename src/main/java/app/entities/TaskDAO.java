/** Интерфейс служит для обозначения основных методов при помощи которых осуществляется взаемодействие класа имплементирующего даный интерфейс с базой данных.
 @author Yevhenii
 @version 1.0
 */

package app.entities;
import app.model.TaskStatus;
import java.util.ArrayList;
public interface TaskDAO {

    /** Данный метод осуществляет создание списка обектов заданий, которые закрепленны указаному пользователю и имеют указаный статус
     @param username принимает Username пользователя
     @param status принимает статус задания
     @return возвращает лист обектов класа Task
     */
    public ArrayList<Task> getTasks(String username,TaskStatus status);


    /** Данный метод осуществляет создание списка обектов заданий, которые имеют один и тот же статус
     @param status принимает статус задания
     @return возвращает лист обектов класа Task
     */
    public ArrayList<Task> getAllTasks(TaskStatus status);

    /** Данный метод осуществляет внесение данных нового задания в БД
     @param t принимает обект готового класа Task
     @return возвращает статус операции
     */
    public int insertTask(Task t);

/** Данный метод осуществляет изминения статуса конкретного задания
 @param id принимает id задания
 @param s принимает новый статус. который следует присвоить заданию
 @return возвращает статус операции
 */
    public int changeStatus(String id, String s);

}
