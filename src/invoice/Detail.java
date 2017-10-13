package invoice;

import customer.Customer;
import dataContainer.Person;

import java.util.ArrayList;

public class Detail extends Invoice {
    public Detail(String invoiceCode, String customerCode, String salespersonCode, String invoiceDate, ArrayList<Customer> customerList, ArrayList<Person> personList) {
        super(invoiceCode, customerCode, salespersonCode, invoiceDate, customerList, personList);
    }

    public Detail(ArrayList<Invoice> invoiceList) {
        super(invoiceList);
    }





    public void getDetailReport() {

       for(Invoice i : this.getInvoiceList()) {

           System.out.println();
           System.out.println("Individual Invoice Detail Reports");
               System.out.println("=====================================");
               System.out.println("Invoice: " + i.getInvoiceCode());
               System.out.println("=====================================");
               System.out.println("Salesperson: " + i.getSalesPersonName());
               System.out.println("Customer Info: ");
               System.out.println(i.getCustomerName() + "(" + i.getCustomerCode() + ")");
               System.out.println(i.getCustomerFullName());
               System.out.println(i.getCustomerAddress());
               System.out.println("---------------------------------------");
               System.out.println("Code - Item(s) - SubTotal - Tax - Total");




       }

    }

}
