package invoice;

import dataContainer.Customer;
import dataContainer.Person;
import products.Product;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Invoice {
    private String invoiceCode;
    private String customerCode;
    private String salespersonCode;
    private String invoiceDate;
    private ArrayList<String> productList;
    private ArrayList<Customer> customerList;
    private ArrayList<Person> personList;
    private ArrayList<Invoice> invoiceList;


    public Invoice(String invoiceCode, String customerCode, String salespersonCode,String invoiceDate, ArrayList<String> productList, ArrayList<Customer> customerList, ArrayList<Person> personList) {
        this.invoiceCode = invoiceCode;
        this.customerCode = customerCode;
        this.salespersonCode = salespersonCode;
        this.invoiceDate = invoiceDate;
        this.productList = productList;
        this.personList = personList;
        this.customerList = customerList;
    }

    public Invoice(ArrayList<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }



    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        for(Customer c : this.customerList) {
            if(c.getCustomerCode().equals(this.customerCode)) {
                return c.getName();
            }
        }

        return "Not a Customer";

    }

    public String getSalesPersonName() {
        for(Person p : this.personList) {
            if(p.getPersonCode().equals(this.salespersonCode)) {
                return p.getLastName() + ", " + p.getFirstName();
            }
        }

        return "This person does not have an invoice";
    }

    public String getSalespersonCode() {
        return salespersonCode;
    }

    public void setSalespersonCode(String salespersonCode) {
        this.salespersonCode = salespersonCode;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public ArrayList<String> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<String> productList) {
        this.productList = productList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }

    public void getSummaryReport() {
            System.out.println("INVOICE CODE - CUSTOMER NAME - SALES PERSON");
            for(Invoice i : this.invoiceList) {
                System.out.println(i.getInvoiceCode() + " - " + i.getCustomerName() + " - " + i.getSalesPersonName());
            }


    }
}
