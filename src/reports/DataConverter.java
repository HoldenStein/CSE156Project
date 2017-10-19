package reports;

import java.util.ArrayList;

import customer.Customer;
import dataContainer.Person;
import fileReader.FlatFileReader;
import invoice.Detail;
import invoice.Invoice;
import invoice.Summary;
import products.Product;

public class DataConverter {

	public static void main(String[] args) {

		// Create a FlatFileReader object
		FlatFileReader fr = new FlatFileReader();

		ArrayList<Person> personList = fr.readPersons();
		ArrayList<Customer> customerList = fr.readCustomers(personList);
		ArrayList<Product> productList = fr.readProducts();
		ArrayList<Invoice> invoiceList = fr.readInvoice(personList, customerList, productList);

		Summary summary = new Summary(invoiceList);

		summary.getSummaryReport();

		Detail detail = new Detail(invoiceList);
		detail.getDetailReport();
	}

}
