package invoice;

import dataContainer.Customer;
import dataContainer.Person;
import products.Product;

import java.util.ArrayList;

public class Invoice {
    private String invoiceCode;
    private String customerCode;
    private String salespersonCode;
    private String invoiceDate;
    private ArrayList<Product> productList;
    private ArrayList<Customer> customerList;
    private ArrayList<Person> personList;
    private ArrayList<Invoice> invoiceList;

    public Invoice(String invoiceCode, String customerCode, String salespersonCode, String invoiceDate, ArrayList<Customer> customerList, ArrayList<Person> personList) {
        this.invoiceCode = invoiceCode;
        this.customerCode = customerCode;
        this.salespersonCode = salespersonCode;
        this.invoiceDate = invoiceDate;
        this.productList = productList;
        this.customerList = customerList;
        this.personList = personList;
    }

    public Invoice(ArrayList<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }



    public String getCustomerName() {
        for(Customer c : this.customerList) {
            if(c.getCustomerCode().equals(this.customerCode)) {
                return c.getName();

            }
        }
        return "This is not a customer";
    }

    public String getSalesPersonName() {
        for(Person p: this.personList) {
            if(p.getPersonCode().equals(this.salespersonCode)) {
                return p.getLastName() + ", "  + p.getFirstName();
            }
        }

        return "This is not a person ";
    }

    public void getSummaryReport() {
        System.out.println("INVOICE NUMBER - CUSTOMER NAME - SALESPERSON");
        for(Invoice i : this.invoiceList) {
            System.out.println(i.getInvoiceCode() + " - " + i.getCustomerName() + " - " + i.getSalesPersonName());
        }
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

    public ArrayList<Product> getProductList() {
        return productList;
    }
    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }


}
