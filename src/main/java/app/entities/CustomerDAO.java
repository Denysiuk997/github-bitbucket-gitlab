package app.entities;

public interface CustomerDAO {
    public Customer getCustomer(String username, String pass);

    public int insertCustomer(Customer c);
}
