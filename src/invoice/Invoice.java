package invoice;

import customer.Customer;
import dataContainer.Person;
import products.MovieTicket;
import products.ParkingPass;
import products.Product;
import products.Refreshment;
import products.SeasonPass;

import java.util.ArrayList;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class Invoice {

	private String invoiceCode;
	private String customerCode;
	private String salespersonCode;
	private DateTime invoiceDate;
	private ArrayList<Product> productList;
	private ArrayList<Customer> customerList;
	private ArrayList<Person> personList;
	private ArrayList<Invoice> invoiceList;
	private ArrayList<String> productPurchased;

	private double totalTaxAmount;
	private double totalSubtotal;
	private double totalDiscountAmount;
	private double totalDetailCost;
	private double finalTotal;

	public Invoice(String invoiceCode, String customerCode, String salespersonCode, DateTime invoiceDate,
			ArrayList<Customer> customerList, ArrayList<Person> personList, ArrayList<Product> productList,
			ArrayList<String> productPurchased) {

		this.invoiceCode = invoiceCode;
		this.customerCode = customerCode;
		this.salespersonCode = salespersonCode;
		this.invoiceDate = invoiceDate;
		this.productList = productList;
		this.customerList = customerList;
		this.personList = personList;
		this.productPurchased = productPurchased;

		invoiceProductInfo();

	}

	public Invoice(ArrayList<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

	// Returns a populated data products list
	public ArrayList<Product> invoiceProductInfo() {

		// Get a list of product type to apply discounts
		ArrayList<String> productTypeList = new ArrayList<String>();

		productTypeList = getProductTypeList();

		ArrayList<Product> invoiceProductInfo = new ArrayList<Product>();

		double totalDetailCost = 0;
		double totalTaxAmount = 0;
		double totalDiscountAmount = 0;

		for (String s : this.productPurchased) {

			String data[] = s.trim().split(":");
			// Get productCount
			int itemCount = Integer.parseInt(data[1]);

			for (Product product : this.productList) {

				if (data[0].equals(product.getProductCode())) {

					// Get the product type and populate data

					if (product.getProductType().equals("M")) {

						// Cast type product to a MovieTicket

						MovieTicket movieTicket = (MovieTicket) product;

						int dayOfWeek = movieTicket.getMovieDateTime().getDayOfWeek();
						// Check if the movie is on a Tuesday or Thursday to apply discount
						if (dayOfWeek == 2 || dayOfWeek == 4) {

							movieTicket.setHasDiscount(true);

							double detailSubTotal = movieTicket.getPricePerUnit() * itemCount
									* (1 - movieTicket.getDiscount());

							double discountAmount = movieTicket.getPricePerUnit() * itemCount
									* (movieTicket.getDiscount());

							double tax = detailSubTotal * movieTicket.getTicketTax();

							totalTaxAmount += tax;
							totalDetailCost += detailSubTotal;
							totalDiscountAmount += discountAmount;

							movieTicket.setHasDiscount(true);
							movieTicket.setDetailSubTotal(detailSubTotal);
							movieTicket.setItemCount(itemCount);
							invoiceProductInfo.add(movieTicket);

						} else {

							movieTicket.setHasDiscount(false);

							double detailSubTotal = movieTicket.getPricePerUnit() * itemCount;
							double discountAmount = movieTicket.getPricePerUnit() * itemCount
									* (movieTicket.getDiscount());
							double tax = detailSubTotal * movieTicket.getTicketTax();

							totalDetailCost += detailSubTotal;
							totalTaxAmount += tax;
							totalDiscountAmount += discountAmount;

							movieTicket.setHasDiscount(false);
							movieTicket.setItemCount(itemCount);
							movieTicket.setDetailSubTotal(detailSubTotal);

							invoiceProductInfo.add(movieTicket);

						}

					}

					if (product.getProductType().equals("S")) {

						// Cast type product to a SeasonPass

						SeasonPass seasonPass = (SeasonPass) product;

						// check to see if there is more than one seasonPass (Check calculation)

						// Check if the invoice date falls after the season start Date
						if (Days.daysBetween(getInvoiceDate().withTimeAtStartOfDay(),
								seasonPass.getStartDate().withTimeAtStartOfDay()).getDays() < 0) {

							int totalSeasonDays = Days.daysBetween(seasonPass.getStartDate().withTimeAtStartOfDay(),
									seasonPass.getEndDate().withTimeAtStartOfDay()).getDays();
							int daysAfterSeason = Days.daysBetween(getInvoiceDate().withTimeAtStartOfDay(),
									seasonPass.getStartDate().withTimeAtStartOfDay()).getDays();

							int daysRemaining = totalSeasonDays + daysAfterSeason;

							double prorated = (double) daysRemaining / totalSeasonDays;

							double detailSubTotal = ((seasonPass.getCost() * prorated) + seasonPass.getFee())
									* itemCount;

							double discountAmount = 0;

							double tax = detailSubTotal * seasonPass.getTicketTax();

							totalDiscountAmount += discountAmount;
							totalDetailCost += detailSubTotal;
							totalTaxAmount += tax;

							seasonPass.setDaysRemaining(daysRemaining);
							seasonPass.setTotalSeasonDays(totalSeasonDays);
							seasonPass.setHasFee(true);
							seasonPass.setHasProtrated(true);
							seasonPass.setProrated(prorated);
							seasonPass.setItemCount(itemCount);

							seasonPass.setDetailSubTotal(detailSubTotal);

							invoiceProductInfo.add(seasonPass);

							// The invoice Date is before startDate
						} else if (Days.daysBetween(getInvoiceDate().withTimeAtStartOfDay(),
								seasonPass.getStartDate().withTimeAtStartOfDay()).getDays() > 0) {

							double detailSubTotal = (seasonPass.getCost() + seasonPass.getFee()) * itemCount;
							double discountAmount = 0;
							totalDiscountAmount += discountAmount;

							double tax = detailSubTotal * seasonPass.getTicketTax();

							totalDetailCost += detailSubTotal;
							totalTaxAmount += tax;

							seasonPass.setHasFee(true);
							seasonPass.setHasProtrated(false);

							seasonPass.setItemCount(itemCount);
							seasonPass.setDetailSubTotal(detailSubTotal);

							invoiceProductInfo.add(seasonPass);

						}
					}

					if (product.getProductType().equals("P")) {

						// Cast type product to a ParkingPass

						ParkingPass parkingPass = (ParkingPass) product;

						// Check to see if there's ticket with parking pass

						if (data.length >= 3) {

							for (String pp : this.productPurchased) {

								String invoiceData[] = pp.trim().split(":");

								// Free parking with per Ticket
								if (data[2].equals(invoiceData[0])) {

									int numOfTicket = Integer.parseInt(invoiceData[1]);
									int numOfFreeParking = Math.max(0, itemCount - numOfTicket);

									// calculate numOfFreeparking

									if (itemCount > numOfTicket) {

										numOfFreeParking = numOfTicket;

									} else if (itemCount == numOfTicket) {
										numOfFreeParking = numOfTicket;
									} else {
										numOfFreeParking = itemCount;
									}

									double detailSubTotal = parkingPass.getParkingFee()
											* Math.max(0, itemCount - numOfTicket);
									double discountAmount = 0;
									double tax = detailSubTotal * parkingPass.getServiceTax();
									totalDiscountAmount += discountAmount;

									totalDetailCost += detailSubTotal;

									totalTaxAmount += tax;

									parkingPass.setHasTicket(true);
									parkingPass.setNumOfTicket(numOfTicket);
									parkingPass.setTicketCode(data[2]);
									parkingPass.setDetailSubTotal(detailSubTotal);
									parkingPass.setNumOfFreeParking(numOfFreeParking);
									parkingPass.setItemCount(itemCount);

									invoiceProductInfo.add(parkingPass);
								}
							}
							// No ticket with parking pass
						} else {

							double detailSubTotal = parkingPass.getParkingFee() * itemCount;

							parkingPass.setHasTicket(false);
							parkingPass.setDetailSubTotal(detailSubTotal);
							parkingPass.setItemCount(itemCount);

							double discountAmount = 0;
							double tax = detailSubTotal * parkingPass.getServiceTax();

							totalDiscountAmount += discountAmount;
							totalDetailCost += detailSubTotal;
							totalTaxAmount += tax;

							invoiceProductInfo.add(parkingPass);

						}

					}

					if (product.getProductType().equals("R")) {

						// Cast type product to a Refreshment

						Refreshment refreshment = (Refreshment) product;

						// Find if the invoice has a Ticket product, then apply discount

						if ((productTypeList).contains("M") || (productTypeList).contains("S")) {

							double detailSubTotal = refreshment.getCost() * itemCount * (1 - refreshment.getDiscount());

							double discountAmount = refreshment.getCost() * itemCount * (refreshment.getDiscount());
							double tax = detailSubTotal * refreshment.getServiceTax();

							totalDetailCost += detailSubTotal;
							totalTaxAmount += tax;
							totalDiscountAmount += discountAmount;

							refreshment.setHasDiscount(true);
							refreshment.setDetailSubTotal(detailSubTotal);
							refreshment.setItemCount(itemCount);

							invoiceProductInfo.add(refreshment);

						} else {

							double detailSubTotal = refreshment.getCost() * itemCount;
							double discountAmount = refreshment.getCost() * itemCount * (refreshment.getDiscount());
							double tax = detailSubTotal * refreshment.getServiceTax();

							totalDiscountAmount += discountAmount;

							totalDetailCost += detailSubTotal;
							totalTaxAmount += tax;

							refreshment.setHasDiscount(false);

							refreshment.setDetailSubTotal(detailSubTotal);
							refreshment.setItemCount(itemCount);

							invoiceProductInfo.add(refreshment);

						}

					}

				}

			}

			// check to see if the customer is a student or general and apply discount

			if (getCustomerTypeName() == "Student") {

				double studentTotalSubTotal = (totalDetailCost * (1 - getStudentDiscount())) + getStudentFee();
				double studentDiscountAmount = (totalDetailCost * (getStudentDiscount()));

				setFinalTotal(studentTotalSubTotal);
				setTotalDetailCost(totalDetailCost);
				setTotalTaxAmount(totalTaxAmount);

				setTotalSubTotal(totalDetailCost + totalTaxAmount);
				setTotalDiscountAmount((studentDiscountAmount + totalTaxAmount) * -1);

			} else if (getCustomerTypeName() == "General") {

				setTotalDetailCost(totalDetailCost);
				setTotalTaxAmount(totalTaxAmount);
				setTotalSubTotal(totalDetailCost + totalTaxAmount);
				setFinalTotal(getTotalSubTotal());
				setTotalDiscountAmount(0.00);

			} else {
				System.out.println("Invalid Customer");
			}

		}

		return invoiceProductInfo;

	}

	// Report Methods

	private ArrayList<String> getProductTypeList() {

		ArrayList<String> productTypeList = new ArrayList<String>();

		for (String s : this.productPurchased) {

			String data[] = s.trim().split(":");

			for (Product product : this.productList) {
				if (data[0].equals(product.getProductCode())) {

					if (product.getProductType().equals("M")) {
						productTypeList.add("M");
					}
					if (product.getProductType().equals("P")) {
						productTypeList.add("P");
					}
					if (product.getProductType().equals("S")) {
						productTypeList.add("S");
					}
					if (product.getProductType().equals("R")) {
						productTypeList.add("R");
					}
				}
			}
		}
		return productTypeList;
	}

	public String getProductCode() {

		for (String s : this.productPurchased) {

			String data[] = s.trim().split(":");

			return data[0];

		}
		return null;

	}

	public String getProductName() {

		for (String s : this.productPurchased) {

			String data[] = s.trim().split(":");

			for (Product product : this.productList) {

				if (data[0].equals(product.getProductCode())) {

					if (product.getProductType().equals("M")) {
						MovieTicket movieTicket = (MovieTicket) product;
						return movieTicket.getMovieName();
					}
					if (product.getProductType().equals("P")) {
						ParkingPass parkingPass = (ParkingPass) product;
						return parkingPass.getName();
					}
					if (product.getProductType().equals("S")) {
						SeasonPass seasonPass = (SeasonPass) product;
						return seasonPass.getName();
					}
					if (product.getProductType().equals("R")) {
						Refreshment refreshment = (Refreshment) product;
						return refreshment.getName();
					}
				}
			}
		}
		return "This product does not exisit";
	}

	public String getCustomerName() {

		for (Customer c : this.customerList) {
			if (c.getCustomerCode().equals(this.customerCode)) {
				return c.getName();

			}
		}
		return "This is not a customer";
	}

	public String getSalesPersonName() {
		for (Person p : this.personList) {
			if (p.getPersonCode().equals(this.salespersonCode)) {
				return p.getLastName() + ", " + p.getFirstName();
			}
		}

		return "This is not a person ";
	}

	public String getCustomerFullName() {
		for (Customer c : this.getCustomerList()) {
			if (c.getCustomerCode().equals(this.getCustomerCode())) {
				return c.getPrimaryContact().getLastName() + ", " + c.getPrimaryContact().getFirstName();
			}
		}

		return "This person does not exist";
	}

	public String getCustomerTypeName() {
		for (Customer c : this.getCustomerList()) {
			if (c.getCustomerCode().equals(this.getCustomerCode())) {
				if (c.getCustomerType().equals("G")) {

					return "General";

				} else if (c.getCustomerType().equals("S")) {
					return "Student";
				}

			}
		}

		return "No Type";
	}

	public String getCustomerAddress() {
		for (Customer c : this.getCustomerList()) {
			if (c.getCustomerCode().equals(this.getCustomerCode())) {
				String fullAddress = String.format("%s %n  %s %s %s %s", c.getAddress().getStreet(),
						c.getAddress().getCity(), c.getAddress().getState(), c.getAddress().getZip(),
						c.getAddress().getCountry());
				return fullAddress;
			}
		}

		return "No Address";
	}

	public double getStudentFee() {

		for (Customer c : this.getCustomerList()) {
			if (c.getCustomerCode().equals(this.getCustomerCode())) {
				return c.getStudentFee();
			}
		}

		return 0;

	}

	public double getStudentDiscount() {

		for (Customer c : this.getCustomerList()) {
			if (c.getCustomerCode().equals(this.getCustomerCode())) {
				return c.getStudentDiscount();
			}
		}
		return 0;

	}

	public double getFees() {
		if (getCustomerTypeName() == "Student") {
			return getStudentFee();
		} else {
			return 0.00;
		}
	}

	// Getter and Setter Methods
	public String getInvoiceCode() {
		return invoiceCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public String getSalespersonCode() {
		return salespersonCode;
	}

	public DateTime getInvoiceDate() {
		return invoiceDate;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public ArrayList<Person> getPersonList() {
		return personList;
	}

	public ArrayList<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public ArrayList<String> getproductPurchased() {
		return productPurchased;
	}

	public void setTotalDetailCost(double totalDetailCost) {

		this.totalDetailCost = totalDetailCost;

	}

	public double getTotalDetailCost() {
		return this.totalDetailCost;
	}

	public void setTotalTaxAmount(double totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	public double getTotalTaxAmount() {
		return this.totalTaxAmount;

	}

	public double getTotalDiscountAmount() {
		return this.totalDiscountAmount;
	}

	public void setTotalDiscountAmount(double totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

	public double getTotalSubTotal() {
		return this.totalSubtotal;
	}

	public void setTotalSubTotal(double totalSubtotal) {
		this.totalSubtotal = totalSubtotal;
	}

	public double getFinalTotal() {
		return finalTotal;
	}

	public void setFinalTotal(double finalTotal) {
		this.finalTotal = finalTotal;
	}

}
