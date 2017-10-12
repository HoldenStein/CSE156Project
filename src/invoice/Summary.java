package invoice;

import dataContainer.Customer;
import dataContainer.Person;
import products.Product;

import java.util.ArrayList;

public class Summary extends Invoice {

    private String customerName;
    private String salesPersonName;
    private double subtotal;
    private double fees;
    private double taxes;
    private double discount;
    private double total;
    private ArrayList<Invoice> invoiceList;

    public Summary(String invoiceCode, String customerCode, String salespersonCode, String invoiceDate, ArrayList<String> productList, ArrayList<Customer> customerList, ArrayList<Person> personList) {
        super(invoiceCode, customerCode, salespersonCode, invoiceDate, productList, customerList, personList);
        this.customerName = customerName;
        this.salesPersonName = salesPersonName;
        this.subtotal = subtotal;
        this.fees = fees;
        this.taxes = taxes;
        this.discount = discount;
        this.total = total;
        this.invoiceList = invoiceList;
    }

    public Summary(ArrayList<Invoice> invoiceList) {
        super(invoiceList);

    }




}
