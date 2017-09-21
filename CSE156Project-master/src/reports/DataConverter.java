package reports;

import java.util.ArrayList;
import dataContainer.Customer;
import dataContainer.Person;
import fileReader.FlatFileReader;
import fileWriter.JsonWriter;
import fileWriter.XMLWriter;
import products.Product;

public class DataConverter {

	public static void main(String[] args) {

		String personJsonOut = "data/Persons.json";
		String productJsonOut = "data/Products.json";
		String customerJsonOut = "data/Customers.json";

		String personXmlOut = "data/Persons.xml";
		String productXmlOut = "data/Products.xml";
		String customerXmlOut = "data/Customers.xml";

		// Create a FlatFileReader object
		FlatFileReader fr = new FlatFileReader();

		ArrayList<Person> personList = fr.readPersons();
		ArrayList<Customer> customerList = fr.readCustomers(personList);
		ArrayList<Product> productList = fr.readProducts();

		XMLWriter xmlWriter = new XMLWriter();
		JsonWriter jWriter = new JsonWriter();

		// Write Person ArrayList into a Json file
		jWriter.jsonConverter(personList, personJsonOut);

		// Write Person ArrayList into an XML file
		xmlWriter.xmlConverter(personList, personXmlOut, "Person");

		// Write Customer ArrayList into a Json file
		jWriter.jsonConverter(customerList, customerJsonOut);

		// Write Customer ArrayList into an XML file
		xmlWriter.xmlConverter(customerList, customerXmlOut, "Customer");

		// Write Product ArrayList into a Json file
		jWriter.jsonConverter(productList, productJsonOut);

		// Write Product ArrayList into an XML file
		xmlWriter.xmlConverter(productList, productXmlOut, "Product");

	}

}
