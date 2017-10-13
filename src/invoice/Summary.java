package invoice;

import java.util.ArrayList;

import dataContainer.Person;

public class Summary extends Invoice {

    private String invoiceID;
    private String customerName;
    private String salesPersonName;
    private double subTotal;
    private double fees;
    private double taxes;
    private double discount;
    private double total;


    public Summary(String invoiceCode, String customerCode, String salespersonCode, String invoiceDate, ArrayList<Customer> customerList, ArrayList<Person> personList, String invoiceID, String customerName, String salesPersonName, double subTotal, double fees, double taxes, double discount, double total) {
        super(invoiceCode, customerCode, salespersonCode, invoiceDate, customerList, personList);
        this.invoiceID = invoiceID;
        this.customerName = customerName;
        this.salesPersonName = salesPersonName;
        this.subTotal = subTotal;
        this.fees = fees;
        this.taxes = taxes;
        this.discount = discount;
        this.total = total;
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
