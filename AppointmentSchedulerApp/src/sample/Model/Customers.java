package sample.Model;

import java.sql.Date;

public class Customers {

    private int customerID; //auto incr
    private String customerName;
    private String address;
    private String zipCode;
    private String phoneNumber;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdateBy;
    private String divisionID;


    public Customers(String address) {
        this.address = address;
    }

    public Customers(int customer_id, String customer_name, String address, String postal_code, String phone) {

        this.customerID = customer_id;
        this.customerName = customer_name;
        this.address = address;
        this.zipCode = postal_code;
        this.phoneNumber = phone;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total;


    public Customers(){

    }

    public Customers(int customerID, String customerName, String address, String zipCode, String phoneNumber,
                     Date createDate, String createdBy, Date lastUpdate, String lastUpdateBy, String divisionID) {


        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
        this.divisionID = divisionID;
    }

    public Customers( String customerName, String address, String zipCode, String phoneNumber) {


        this.customerName = customerName;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;


    }





    public Customers(int customerID, String customerName, String address, String zipCode, String phoneNumber, String divisionID) {


        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.divisionID = divisionID;


    }


    public Customers( String customerName, String address, String zipCode, String phoneNumber, String divisionID) {


        this.customerName = customerName;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.divisionID = divisionID;




    }

    public Customers(int customer_id) {
        this.customerID = customer_id;


    }

    public Customers(String customer_name, int total) {
        this.customerName = customer_name;
        this.total = total;





    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(String divisionID) {
        this.divisionID = divisionID;
    }
}
