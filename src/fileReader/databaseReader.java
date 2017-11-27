package databaseReader;

import java.sql.*;
import dataContainer.*;
import products.*;
import customer.*;
import invoice.*;
import java.util.ArrayList;
import com.ceg.ext.DatabaseInfo;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class databaseReader {

    public ArrayList<Person> readPersons() {
        Connection conn = DatabaseInfo.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        ArrayList<Person> pList = null;
        String query = "SELECT * FROM Persons;";
        try {
            p = conn.prepareStatement(query);
            rs = p.executeQuery(query);
            pList = new ArrayList<Person>();
            rs.beforeFirst();
            Person person = null;
            Address address = null;
            while(rs.next()) {
                person.setPersonCode(rs.getString("personCode"));
                person.setFirstName(rs.getString("personFName"));
                person.setLastName(rs.getString("personLName"));
                address = new Address(rs.getString("personAddress"));
                person.setAddress(address);
                person.setEmail(rs.getString("emailAddresses"));
                pList.add(person);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return pList;
    }

    public ArrayList<Customer> readCustomers(ArrayList<Person> personList) {
        Connection conn = DatabaseInfo.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        ArrayList<Customer> cList = null;
        String query = "SELECT * FROM Customers JOIN Persons ON Customers.personID = Persons.personID;";

        try {
            p = conn.prepareStatement(query);
            rs = p.executeQuery(query);
            rs.beforeFirst();
            Customer c = null;
            Address address = null;
            while(rs.next()) {
                c.setCustomerCode(rs.getString("customerCode"));
                c.setCustomerName(rs.getString("customerName"));
                c.setCustomerType(rs.getString("customerType"));
                address = new Address(rs.getString("address"));
                c.setAddress(address);
                for(Person person : personList) {
                    if(rs.getString("personCode") == rs.getString("primaryContact")) {
                        c.setPrimaryContact(person);
                    }
                }

                cList.add(c);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return cList;

    }

    public ArrayList<Product> readProducts() {
        Connection conn = DatabaseInfo.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        ArrayList<Product> pList = null;
        String query = "SELECT * FROM Products JOIN MovieTickets ON Products.movieTicketID = MovieTickets.movieTicketID" +
                "                              JOIN SeasonPasses ON Products.seasonPassID = SeasonPasses.seasonPassID" +
                "                              JOIN ParkingPasses ON Products.parkingPassID = ParkingPasses.parkingPassID" +
                "                              JOIN Refreshments ON Products.refreshmentID = Refreshement.refreshmentID;";

        try {
            p = conn.prepareStatement(query);
            rs = p.executeQuery(query);
            rs.beforeFirst();
            Product product = null;

            while(rs.next()) {
                if(rs.getString("productType") == "M") {
                    String productCode = rs.getString("productCodeM");
                    String productType = rs.getString("productTypeM");
                    DateTime movieDateTime = DateTime.parse(rs.getString("movieDateTime"), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm"));
                    String movieName = rs.getString("movieName");
                    Address address = new Address(rs.getString("address"));
                    String screenNo = rs.getString("screenNumber");
                    double pricePerUnit = rs.getDouble("pricePerUnit");
                    product = new MovieTicket(productCode, productType, movieDateTime, movieName, address, screenNo, pricePerUnit);
                    pList.add(product);
                } else if(rs.getString("productType") == "S") {
                    String productCode = rs.getString("productCodeS");
                    String productType = rs.getString("productTypeS");
                    String name = rs.getString("seasonPassName");
                    DateTime startDate = DateTime.parse(rs.getString("startDate"), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm") );
                    DateTime endDate = DateTime.parse(rs.getString("endDate"), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm") );
                    double seasonPassCost = rs.getDouble("seasonPassCost");
                    product = new SeasonPass(productCode, productType, name, startDate, endDate, seasonPassCost);
                    pList.add(product);
                } else if(rs.getString("productType") == "R") {
                    String productCode = rs.getString("productCodeR");
                    String productType = rs.getString("productTypeR");
                    String refreshmentName = rs.getString("refreshmentName");
                    double refreshmentCost = rs.getDouble("refreshmentCost");
                    product = new Refreshment(productCode, productType, refreshmentName, refreshmentCost);
                    pList.add(product);
                } else if(rs.getString("productType") == "P") {
                    String productCode = rs.getString("productCodeP");
                    String productType = rs.getString("productTypeP");
                    double parkingFee = rs.getDouble("parkingFee");

                    product = new ParkingPass(productCode, productType, parkingFee);
                    pList.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pList;
    }

    public ArrayList<Invoice> readInvoice(ArrayList<Person> personList, ArrayList<Customer> customerList, ArrayList<Product> productList) {
        Connection conn = DatabaseInfo.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        ArrayList<Invoice> invoiceList = null;
        String query = "SELECT * FROM Invoices JOIN Customers ON Invoices.customerID = Customers.customerID" +
                "                              JOIN Persons ON Invoices.personID = Persons.personID" +
                "                              JOIN Products ON Invoices.productID = Products.productID;";
        try {
            p = conn.prepareStatement(query);
            rs = p.executeQuery(query);
            Invoice invoice = null;
            rs.beforeFirst();
            while(rs.next()) {
                String invoiceCode = rs.getString("invoiceCode");
                String customerCode = rs.getString("customerCode");
                String salesPersonCode = rs.getString("personCode");
                DateTime invoiceDate = DateTime.parse(rs.getString("invoiceDate"), DateTimeFormat.forPattern("yyyy-MM-dd"));
                ArrayList<String> productPurchased = new ArrayList<String>();

                String products[] = rs.getString("productCodes").split(",");
                for(int i = 0; i < products.length; i++) {
                    productPurchased.add(products[i]);
                }
                invoice = new Invoice(invoiceCode, customerCode, salesPersonCode, invoiceDate, customerList, personList, productList, productPurchased);
                invoiceList.add(invoice);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;

    }

}
