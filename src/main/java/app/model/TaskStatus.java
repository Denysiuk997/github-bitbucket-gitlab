/** Перечисление служит для обозначения возможных состояний задания
 * CANCEL - задание отменено
 * RESPONSE - задание отправлено пользователю
 * REQUEST - Пользователь запросил подтвирждение созданого им задания
 * MAKING - задание выполняется
 * FINISH - задание выполнено
 @author Yevhenii
 @version 1.0
 */

package app.model;

public enum TaskStatus {


    FINISH("FINISH"), MAKING("MAKING"), REQUEST("REQUEST"), RESPONSE("RESPONSE"), CANCEL("CANCEL");

    private String name;

    TaskStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
