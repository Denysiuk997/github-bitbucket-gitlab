/** Данный класс имлементирует интерфейс CustomerDAO. при помощи него Выполняется внисение и получение пользователя с БД.
 Клас имеет два свойства. con - для хранения соединения с БД и ps для хранения запроса к БД
 <b>con</b>, <b>ps</b>
 @author Yevhenii
 @version 1.0
 */

package app.entities;
import app.model.ConnectionPool;
import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDAOImpl implements CustomerDAO {
    /** Свойство - con */
private Connection con;
    /** Свойство - ps */
private PreparedStatement ps;


    /** Данный метод осуществляет создание обекта зареестрованого пользователя. Данные метод получает с БД.
     @param username принимает Username пользователя
     @param pass принимает пароль пользователя
     @return возвращает новый обект класа Customer
     */
    @Override
    public Customer getCustomer(String username, String pass) {
        Customer c = new Customer();
        try {
            con= ConnectionPool.getInstance().getConnection();
//        con=MyConnectionProvider.getCon();
        ps=con.prepareStatement("select * from customer where username=? and password=?");
        ps.setString(1,username);
            ps.setString(2,pass);

            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                c.setUserId(rs.getInt(1));
                c.setUserName(rs.getString(2));
                c.setPassword(rs.getString(3));
                c.setName(rs.getString(4));
                c.setRole(rs.getString(5));
            }


        }catch (Exception e){
            System.out.println(e);
        }

        return c;
    }


    /** Данный метод осуществляет внесение данных нового пользователя в БД
     @param c принимает обект готового класа Customer
     @return возвращает статус операции
     */
    @Override
    public int insertCustomer(Customer c) {
        int status = 0;
        try {
//            con= MyConnectionProvider.getCon();
            con= ConnectionPool.getInstance().getConnection();
            ps=con.prepareStatement("insert into customer values(?,?,?,?,?)");
            ps.setString(1,null);
            ps.setString(2,c.getUserName());
            ps.setString(3,c.getPassword());
            ps.setString(4,c.getName());
            ps.setString(5,"user");
            status=ps.executeUpdate();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
                return status;
    }

    /** Данный метод осуществляет проверку userName пользователя на наявность такого в БД. Метод нужен чтобы предотвратить регистрацию пользователей с одним и тем же логином.
     @param userName принимает имя пользователя
     @return res возвращает статус операции
     */
    public boolean checkCustomer(String userName){
        boolean res = false;

        try {
            con= ConnectionPool.getInstance().getConnection();
//            con=MyConnectionProvider.getCon();
            ps=con.prepareStatement("select idcustomer from customer where username=?");
            ps.setString(1,userName);


            res = ps.executeQuery().next();
        }catch (Exception e){
            System.out.println(e);
        }

        return res;
    }
}
