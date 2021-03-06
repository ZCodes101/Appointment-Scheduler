package sample.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Customers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomersDB {

    private static final ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
    private static final ObservableList<Integer> allCustomersID = FXCollections.observableArrayList();


    // Return all Customers in db
    public static ObservableList<Customers> getAllCustomers() {
        allCustomers.clear();
        try {
            Statement statement = DB.startConnection().createStatement();

            String y = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Phone, customers.Postal_Code, first_level_divisions.Division"
                    + " FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID "
                    + "INNER JOIN countries ON countries.Country_ID = first_level_divisions.COUNTRY_ID";


            ResultSet rs = statement.executeQuery(y);
            while (rs.next()) {
                Customers customer = new Customers(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getString("Division")

                );
                allCustomers.add(customer);
            }
            statement.close();
            return allCustomers;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }


    // Return all Customers in db
    public static ObservableList<Integer> getAllCustomerIDs() {
        allCustomersID.clear();
        try {
            Statement statement = DB.startConnection().createStatement();


            String y = "SELECT Customer_ID  FROM customers ORDER BY Customer_ID ASC;";


            ResultSet rs = statement.executeQuery(y);
            while (rs.next()) {
                Customers customer = new Customers(
                        rs.getInt("Customer_ID")


                );
                allCustomersID.add(customer.getCustomerID());
            }
            statement.close();
            return allCustomersID;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }


    // Update Customer DB
    public static void updateCustomer(int customerID, String name, String address, String zip, String phone, int divID) throws SQLException {
        Statement statement = DB.startConnection().createStatement();
        String x = "UPDATE customers SET Customer_Name='" + name + "', Address='" + address + "', Postal_Code='" + zip + "', Last_Updated_By='" + UsersDB.getActiveUser().getUserName() + "', Phone='" + phone + "', Division_ID=" + divID + " WHERE Customer_ID=" + customerID;

        statement.execute(x);
    }


    public static boolean deleteCustomer(int id) {

        try {
            Statement statement = DB.startConnection().createStatement();
            String queryOne = "DELETE FROM customers WHERE Customer_ID=" + id;
            String secQ = "DELETE FROM appointments WHERE Customer_ID=" + id;
            statement.executeUpdate(secQ);
            int deleteC = statement.executeUpdate(queryOne);
            if (deleteC == 1) {

                return true;

            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }


    public static void saveNewCustomer(String name, String address, String zipCode, String phoneNum, int divID) {


        try {


            Statement stment = DB.startConnection().createStatement();
            String x = "INSERT INTO customers SET Customer_Name='" + name + "', Address='" + address + "', Postal_Code='" + zipCode + "', Created_By='" + UsersDB.getActiveUser().getUserName() + "', Last_Updated_By='" + UsersDB.getActiveUser().getUserName() + "', Phone='" + phoneNum + "', Division_ID=" + divID;


            stment.execute(x);


        } catch (SQLException e) {


            System.out.println("SQLException: " + e.getMessage());
        }


    }


}
