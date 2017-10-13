package invoice;

import customer.Customer;
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

    public Invoice(String invoiceCode, String customerCode, String salespersonCode, String invoiceDate, ArrayList<Customer> customerList, ArrayList<Person> personList, ArrayList<Product> productList) {
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


//    Summary Report Methods

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


//      Detailed Report Methods

    public String getCustomerFullName() {
        for(Customer c : this.getCustomerList()) {
            if(c.getCustomerCode().equals(this.getCustomerCode())) {
                return c.getContact().getLastName() + ", " + c.getContact().getFirstName();
            }
        }

        return "This person does not exist";
    }

    public String getCustomerType() {
        for(Customer c : this.getCustomerList()) {
            if(c.getCustomerCode().equals(this.getCustomerCode())) {
                return c.getType();
            }
        }

        return "No Type";
    }

    public String getCustomerAddress() {
        for(Customer c : this.getCustomerList()) {
            if(c.getCustomerCode().equals(this.getCustomerCode())) {
                return c.getAddress().getFullAddress();
            }
        }

        return "No Address";
    }

//    Getter and Setter Methods

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

    public ArrayList<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(ArrayList<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }
}
