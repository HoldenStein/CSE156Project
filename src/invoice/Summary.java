package invoice;

import java.util.ArrayList;
import dataContainer.Customer;
import dataContainer.Person;
import products.Product;

public class Summary extends Invoice {

    private String invoiceID;
    private String customerName;
    private String salesPersonName;
    private double subTotal;
    private double fees;
    private double taxes;
    private double discount;
    private double total;


    public Summary(String invoiceCode, String customerCode, String salespersonCode, String invoiceDate, ArrayList<Product> productList, ArrayList<Customer> customerList, ArrayList<Person> personList, String invoiceID, String customerName, String salesPersonName, double subTotal, double fees, double taxes, double discount, double total) {
        super(invoiceCode, customerCode, salespersonCode, invoiceDate, productList, customerList, personList);
        this.invoiceID = invoiceID;
        this.customerName = customerName;
        this.salesPersonName = salesPersonName;
        this.subTotal = subTotal;
        this.fees = fees;
        this.taxes = taxes;
        this.discount = discount;
        this.total = total;
    }

    public String getCustomerName() {
        for(Customer c : this.customerList) {
            if(c.getName())
        }


    }


}
