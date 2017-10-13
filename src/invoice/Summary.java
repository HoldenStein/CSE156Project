package invoice;

import java.util.ArrayList;

import customer.Customer;
import dataContainer.Person;
import products.Product;

public class Summary extends Invoice {




    public Summary(String invoiceCode, String customerCode, String salespersonCode, String invoiceDate, ArrayList<Customer> customerList,
                   ArrayList<Person> personList, ArrayList<Product> productList, ArrayList<String> productCodes) {

        super(invoiceCode, customerCode, salespersonCode, invoiceDate, customerList, personList, productList, productCodes);

    }



    public Summary(ArrayList<Invoice> invoiceList) {
        super(invoiceList);

    }

    public void getSummaryReport() {
        System.out.println("INVOICE NUMBER - CUSTOMER NAME - SALESPERSON");
        for(Invoice i : this.getInvoiceList()) {
            System.out.println(i.getInvoiceCode() + " - " + i.getCustomerName() + " - " + i.getSalesPersonName());
        }
    }
}
