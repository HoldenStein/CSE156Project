package invoice;

import dataContainer.Customer;
import dataContainer.Person;
import products.Product;

import java.util.ArrayList;

public abstract class Invoice {
    protected String invoiceCode;
    protected String customerCode;
    protected String salespersonCode;
    protected String invoiceDate;
    protected ArrayList<Product> productList;
    protected ArrayList<Customer> customerList;
    protected ArrayList<Person> personList;

    public Invoice(String invoiceCode, String customerCode, String salespersonCode, String invoiceDate, ArrayList<Product> productList, ArrayList<Customer> customerList, ArrayList<Person> personList) {
        this.invoiceCode = invoiceCode;
        this.customerCode = customerCode;
        this.salespersonCode = salespersonCode;
        this.invoiceDate = invoiceDate;
        this.productList = productList;
        this.customerList = customerList;
        this.personList = personList;
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
