package customer;

import dataContainer.Address;
import dataContainer.Person;

public class General extends Customer {

	public General(String customerCode, String customerType, Person contact, String name, Address address) {

		super(customerCode, customerType, contact, name, address);
	}
}
