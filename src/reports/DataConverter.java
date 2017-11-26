package reports;

import java.util.ArrayList;
import java.util.Iterator;

import customer.Customer;
import dataContainer.Person;
import fileReader.FlatFileReader;
import invoice.Detail;
import invoice.Invoice;
import invoice.InvoiceList;
import invoice.TotalComparator;
import invoice.Executive;
import products.Product;

public class DataConverter {

	public static void main(String[] args) {

		// Create a FlatFileReader object
		FlatFileReader fr = new FlatFileReader();

		ArrayList<Person> personList = fr.readPersons();
		ArrayList<Customer> customerList = fr.readCustomers(personList);
		ArrayList<Product> productList = fr.readProducts();
		ArrayList<Invoice> invoiceList = fr.readInvoice(personList, customerList, productList);
		
		
		InvoiceList invoiceOrderedList = new InvoiceList(new TotalComparator());

		// For testing adding the invoiceList from flat file reader to the order list
		
		for (Invoice invoice : invoiceList) {
			
			invoiceOrderedList.add(invoice);
			//System.out.println(invoice.getInvoiceCode());		
		}
		for (Invoice invoice : invoiceOrderedList) {	
			//System.out.println(invoice.getInvoiceCode());	
		}

	
		Executive summary = new Executive(invoiceOrderedList);
		Detail detail = new Detail(invoiceOrderedList);
//		
		summary.getSummaryReport();
		detail.getDetailReport();
	}

}
