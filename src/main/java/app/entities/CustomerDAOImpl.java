package app.entities;

import app.model.MyConnectionProvider;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDAOImpl implements CustomerDAO {
private Connection con;
private PreparedStatement ps;



    @Override
    public Customer getCustomer(String username, String pass) {
        Customer c = new Customer();
        try {
        con=MyConnectionProvider.getCon();
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

    @Override
    public int insertCustomer(Customer c) {
        int status = 0;
        try {
            con= MyConnectionProvider.getCon();
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

    public boolean checkCustomer(String userName){
        boolean res = false;

        try {
            con=MyConnectionProvider.getCon();
            ps=con.prepareStatement("select idcustomer from customer where username=?");
            ps.setString(1,userName);


            res = ps.executeQuery().next();
        }catch (Exception e){
            System.out.println(e);
        }

        return res;
    }
}
