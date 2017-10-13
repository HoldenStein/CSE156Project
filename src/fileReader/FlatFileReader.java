package fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import customer.Customer;
import dataContainer.Address;
import dataContainer.Name;
import dataContainer.Person;
import invoice.Invoice;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import products.MovieTicket;
import products.ParkingPass;
import products.Product;
import products.Refreshment;
import products.SeasonPass;

import static org.joda.time.format.DateTimeFormat.forPattern;

public class FlatFileReader {

//	Should put these within there respective methods, or made private

//	Scanner personScanner = null;
//	Scanner productScanner = null;
//	Scanner customerScanner = null;
//
//
//	String personFile = "data/Persons.dat";
//	String productFile = "data/Products.dat";
//	String customerFile = "data/Customers.dat";

	public ArrayList<Invoice> readInvoice(ArrayList<Person> personList, ArrayList<Customer> customerList, ArrayList<Product> productList) {
		Scanner invoiceScanner = null;
		String invoiceFile = "data/Invoices.dat";

		try {
			invoiceScanner = new Scanner(new File(invoiceFile));
			invoiceScanner.nextLine();

			ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();

			while(invoiceScanner.hasNext()) {
				String line = invoiceScanner.nextLine();
				String data[] = line.trim().split(";");

				String invoiceCode = data[0];
				String customerCode = data[1];
				String personCode = data[2];
				String invoiceDate = data[3];

				ArrayList<String> productCodes = new ArrayList<String>();
				String products[] = data[4].split(",");
				for(int i = 0; i < products.length; i++) {
					productCodes.add(products[i]);
				}

				Invoice invoice = new Invoice(invoiceCode, customerCode, personCode, invoiceDate, customerList, personList, productList, productCodes);
				invoiceList.add(invoice);
			}

			return invoiceList;


		} catch(FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Person> readPersons() {

//		I moved the personScanner, and personFile to inside the method
		Scanner personScanner = null;
		String personFile = "data/Persons.dat";

		try {

			personScanner = new Scanner(new File(personFile));

			personScanner.nextLine(); // reads the number of records from the first line

			// This Person ArrayList stores the Person objects
			ArrayList<Person> personList = new ArrayList<Person>();

			while (personScanner.hasNext()) {
				String line = personScanner.nextLine(); // reads each line starting from 2nd line
				String data[] = line.trim().split(";"); // tokenizes the line and stores in a string array

				// Stores the 4 array elements of each line into strings
				String personCode = data[0];

				// Create an Name object
				Name name = new Name(data[1]);

				String lastName = name.getlName();
				String firstName = name.getfName();

				// Creates an Address object
				Address address = new Address(data[2]);

				if (data.length == 4) {

					ArrayList<String> email;
					String emails = data[3];
					String[] emailArray = emails.split(",");
					email = new ArrayList<String>();

					for (int i = 0; i < emailArray.length; i++) {

						email.add(emailArray[i]);
					}

					// Creates a Person object with email
					Person person = new Person(personCode, firstName, lastName, address, email);

					// Adds the Person object into Person ArrayList
					personList.add(person);

				} else {
					Person person = new Person(personCode, firstName, lastName, address);

					// Adds the Person object into Person ArrayList
					personList.add(person);
				}

			}

			personScanner.close();

			return personList;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Customer> readCustomers(ArrayList<Person> personsList) {

//		Moved the customerScanner, and customer file to the method
		Scanner customerScanner = null;
		String customerFile = "data/Customers.dat";
		try {

			customerScanner = new Scanner(new File(customerFile));

			customerScanner.nextLine(); // reads the number of records from the first line

			// This Customer ArrayList stores the Customer objects
			ArrayList<Customer> customerList = new ArrayList<Customer>();

			while (customerScanner.hasNext()) {
				Customer customer = null;
				String line = customerScanner.nextLine(); // reads each line starting from 2nd line
				String data[] = line.trim().split(";"); // tokenizes the line and stores in a string array

				// Stores the 4 array elements of each line into strings
				String customerCode = data[0];
				String customerType = data[1];
				String contactCode = data[2];
				String customerName = data[3];

				// Creates an Address object
				Address address = new Address(data[4]);

				// Person contact
				Person contactPerson = null;
				for (Person person : personsList) {
					if (person.getPersonCode().equals(contactCode)) {
						contactPerson = person;
						break;
					}
				}
				if (contactPerson != null) {
					customer = new Customer(customerCode, customerType, contactPerson, customerName, address);
				} else {
					System.out.println("For some reason there is no contact person -_- " + contactCode);
				}

				// Adds the customer object into customer ArrayList
				customerList.add(customer);

			}
			customerScanner.close();
			return customerList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<Product> readProducts() {

		Scanner productScanner = null;
		String productFile = "data/Products.dat";
		try {

			productScanner = new Scanner(new File(productFile));

			productScanner.nextLine(); // reads the number of records from the first line

			// This product ArrayList stores the product objects
			ArrayList<Product> productList = new ArrayList<Product>();

			while (productScanner.hasNext()) {
				String line = productScanner.nextLine(); // reads each line starting from 2nd line
				String data[] = line.trim().split(";"); // tokenizes the line and stores in a string array

				// Stores the 4 array elements of each line into strings
				String productCode = data[0];
				String productType = data[1];

				if (productType.equals("M")) {

//					Parses the String to date time
					DateTime movieDateTime = DateTime.parse(data[2], DateTimeFormat.forPattern("yyyy-MM-dd HH:mm"));

					String movieName = data[3];

					Address address = new Address(data[4]);
					String screenNumber = data[5];
					double pricePerUnit = Double.parseDouble(data[6]);

					MovieTicket movieTicket = new MovieTicket(productCode,productType, movieDateTime, movieName, address,
							screenNumber, pricePerUnit);

					productList.add(movieTicket);

				}

				if (productType.equals("S")) {

					String name = data[2];
					DateTime startDate = DateTime.parse(data[3], DateTimeFormat.forPattern("yyyy-MM-dd"));
					DateTime endDate = DateTime.parse(data[4], DateTimeFormat.forPattern("yyyy-MM-dd"));
					double cost = Double.parseDouble(data[5]);

					SeasonPass seasonPass = new SeasonPass(productCode, productType, name, startDate, endDate, cost);

					productList.add(seasonPass);

				}

				if (productType.equals("R")) {

					String name = data[2];
					double cost = Double.parseDouble(data[3]);
					Refreshment refreshment = new Refreshment(productCode,productType, name, cost);
					productList.add(refreshment);

				}

				if (productType.equals("P")) {

					double parkingFee = Double.parseDouble(data[2]);
					ParkingPass parkingPass = new ParkingPass(productCode,productType, parkingFee);
					productList.add(parkingPass);

				}

			}

			productScanner.close();

			return productList;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
