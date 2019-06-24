/** Интерфейс служит для обозначения основных методов при помощи которых осуществляется взаемодействие класа имплементирующего даный интерфейс с базой данных.
 @author Yevhenii
 @version 1.0
 */

package app.entities;

public interface CustomerDAO {

    /** Данный метод осуществляет создание обекта зареестрованого пользователя
     @param username принимает Username пользователя
     @param pass принимает пароль пользователя
     @return возвращает новый обект класа Customer
     */
    public Customer getCustomer(String username, String pass);

    /** Данный метод осуществляет внесение данных нового пользователя в БД
     @param c принимает обект готового класа Customer
     @return возвращает статус операции
     */
    public int insertCustomer(Customer c);
}
