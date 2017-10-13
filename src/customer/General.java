package customer;

import dataContainer.Address;
import dataContainer.Person;

public class General extends Customer{

	public General(String customerCode, String type, Person contact, String name, Address address) {

		super(customerCode, type, contact, name, address);
	}
}
