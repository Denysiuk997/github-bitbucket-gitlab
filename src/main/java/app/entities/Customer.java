/** Класс служит для хранения объекта - пользователя со свойствами
 <b>userName</b>, <b>name</b>, <b>password</b>,<b>role</b>,<b>userId</b>
 @author Yevhenii
 @version 1.0
 */

package app.entities;

public class Customer {
    /** Свойство - userName */
    private String userName;
    /** Свойство - name */
    private String name;
    /** Свойство - password */
    private String password;
    /** Свойство - роль пользователя (админ или простой пользователь */
    private String role;
    /** Свойство - ид пользователя */
    private int userId;

    /** Создает новый объект, который в последствии будет заполнятся сетерами
     @see Customer#Customer()
     */
    public Customer() {
    }
    /** Получает значение свойства role, которое можно задать с помощью метода {@link #setRole(String) }
     @return Значение свойства role
     */
    public String getRole() { return role; }

    /** Задает значение свойства role, которое можно получить при помощи метода {@link #getRole() }
     @param role Новое значение свойства role
     */
    public void setRole(String role) { this.role = role; }

    /** Задает значение свойства userId, которое можно получить при помощи метода {@link #getUserId()} ()}
     @param userId Новое значение свойства userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /** Получает значение свойства userId, которое можно задать с помощью метода {@link #setUserId(int)}
     @return Значение свойства userId
     */
    public int getUserId() {
        return userId;
    }

    /** Задает значение свойства userName, которое можно получить при помощи метода {@link #getUserName()}
     @param userName Новое значение свойства userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** Задает значение свойства name, которое можно получить при помощи метода {@link #getName()}
     @param name Новое значение свойства name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Задает значение свойства password, которое можно получить при помощи метода {@link #getPassword()}
     @param password Новое значение свойства password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /** Получает значение свойства userName, которое можно задать с помощью метода {@link #setUserName(String)}
     @return Значение свойства userName
     */
    public String getUserName() {
        return userName;
    }
    /** Получает значение свойства name, которое можно задать с помощью метода {@link #setName(String)}
     @return Значение свойства name
     */
    public String getName() {
        return name;
    }
    /** Получает значение свойства password, которое можно задать с помощью метода {@link #setPassword(String)}
     @return Значение свойства password
     */
    public String getPassword() {
        return password;
    }
}
