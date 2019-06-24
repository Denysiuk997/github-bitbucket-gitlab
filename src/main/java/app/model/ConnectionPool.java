/** Класс служит для создания пула соединений к БД. Файл конфигурации БД находится в файле \web\META-INF\context.xml
 @author Yevhenii
 @version 1.0
 */

package app.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    /** Свойство - instance которое хранит сылку на обект Класа ConnectionPool для реализации singleton*/
    private static ConnectionPool instance = null;

    /** Возвращает единственный обект класа ConnectionPool
     @return возвращает instance
     */
    public static ConnectionPool getInstance() {
        if (instance==null)
            instance = new ConnectionPool();
        return instance;

    }

    /** Данный метод осуществляет подключение к БД, конфигурация которой прописана в файле \web\META-INF\context.xml
     @return возвращает соединение
     */
    public Connection getConnection(){
        Context ctx;
        Connection connection = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mydatabase");
            connection = ds.getConnection();

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
