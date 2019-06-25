import app.entities.Customer;
import app.entities.CustomerDAOImpl;
import app.model.ConnectionPool;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDAOImplTest {

    @Test
    public void getCustomer(){

        Customer user = new Customer();
        user.setName("misha");
        user.setUserName("misha202");
        user.setPassword("password");
        user.setRole("user");
        user.setUserId(1);

//        PreparedStatement ps;
//        Connection con;
//        try {
//
//            con= ConnectionPool.getInstance().getConnection();
//            ps=con.prepareStatement("insert into customer values(?,?,?,?,?)");
//            ps.setString(1,null);
//            ps.setString(2,user.getUserName());
//            ps.setString(3,user.getPassword());
//            ps.setString(4,user.getName());
//            ps.setString(5,user.getRole());
//           ps.executeUpdate();
//            con.close();
//        }catch (Exception e){
//            System.out.println(e);
//        }

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();


        Customer testUser;
        testUser = customerDAO.getCustomer("admin","pass");

        user.setUserId(user.getUserId());
        System.out.println(testUser.getUserName());
        //Assert.assertEquals(user.getRole(), testUser.getRole());
    }

}
