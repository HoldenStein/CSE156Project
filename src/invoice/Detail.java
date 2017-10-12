package invoice;

import dataContainer.Customer;
import dataContainer.Person;

import java.util.ArrayList;

public class Detail extends Invoice {
    public Detail(String invoiceCode, String customerCode, String salespersonCode, String invoiceDate, ArrayList<Customer> customerList, ArrayList<Person> personList) {
        super(invoiceCode, customerCode, salespersonCode, invoiceDate, customerList, personList);
    }

    public Detail(ArrayList<Invoice> invoiceList) {
        super(invoiceList);
    }
}
