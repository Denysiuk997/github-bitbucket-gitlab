package app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MyConnectionProvider implements MyProvider {

    static Connection con = null;

    public static Connection getCon() {
        try {
            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", pwd);
            props.setProperty("useUnicode", "true");
            props.setProperty("characterEncoding", "UTF-8");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connUrl,props);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}