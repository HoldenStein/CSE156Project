package invoice;

import customer.Customer;
import dataContainer.Address;
import dataContainer.Person;
import products.MovieTicket;
import products.ParkingPass;
import products.Product;
import products.Refreshment;
import products.SeasonPass;

import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.joda.time.DateTime;

public class Detail extends Invoice {
	public Detail(String invoiceCode, String customerCode, String salespersonCode, DateTime invoiceDate,
			ArrayList<Customer> customerList, ArrayList<Person> personList, ArrayList<Product> productList,
			ArrayList<String> productPurchased) {
		super(invoiceCode, customerCode, salespersonCode, invoiceDate, customerList, personList, productList,
				productPurchased);
	}

	public Detail(ArrayList<Invoice> invoiceList) {
		super(invoiceList);
	}

	public void getDetailReport() {

		System.out.println();
		System.out.println("Individual Invoice Detail Reports");
		System.out.println("==================================================");
		System.out.println();

		for (Invoice i : this.getInvoiceList()) {
			System.out.println();
			System.out.println("Invoice: " + i.getInvoiceCode());
			System.out.println("========================");
			System.out.println("Salesperson: " + i.getSalesPersonName());
			System.out.println("Customer Info: ");
			System.out.println("  " + i.getCustomerName() + "(" + i.getCustomerCode() + ")");
			System.out.println("  " + "[" + i.getCustomerTypeName() + "]");
			System.out.println("  " + i.getCustomerFullName());
			System.out.println("  " + i.getCustomerAddress());
			System.out.println("-------------------------------------------");
			System.out.printf("%-10s %-80s %-16s %-10s %s\n", "Code", "Item(s)", "SubTotal", "Tax", "Total");

			for (String s : i.getproductPurchased()) {

				String data[] = s.trim().split(":");

				for (Product p : i.invoiceProductInfo()) {

					if (data[0].equals(p.getProductCode())) {

						if (p.getProductType().equals("M")) {

							MovieTicket movieTicket = (MovieTicket) p;

							Address address = movieTicket.getAddress();
							DateTime date = movieTicket.getMovieDateTime();

							String itemsString = movieTicket.getProductTypeName() + " " + "'"
									+ movieTicket.getMovieName() + " @ " + address.getStreet();

							System.out.printf("%-11s%-79s%s%8.2f%5s%8.2f%5s%8.2f", movieTicket.getProductCode(),
									itemsString, "$", movieTicket.getDetailSubTotal(), "$", movieTicket.getProductTax(),
									"$", movieTicket.getTotalCost());

							System.out.printf("%n %13s %s,%s %s:%02d (%s units @ $%s/units)%n",
									date.monthOfYear().getAsShortText(), date.getDayOfMonth(), date.getYear(),
									date.getHourOfDay(), date.getMinuteOfHour(), movieTicket.getItemCount(),
									movieTicket.getPricePerUnit());

						}
						if (p.getProductType().equals("P")) {

							ParkingPass parkingPass = (ParkingPass) p;

							if (parkingPass.getHasTicket()) {

								String itemString = parkingPass.getProductTypeName() + " " + parkingPass.getTicketCode()
										+ " (" + parkingPass.getItemCount() + " units @ " + parkingPass.getParkingFee()
										+ " with " + parkingPass.getNumOfFreeParking() + " free)";

								System.out.printf("%-11s%-79s%s%8.2f%5s%8.2f%5s%8.2f%n", parkingPass.getProductCode(),
										itemString, "$", parkingPass.getDetailSubTotal(), "$",
										parkingPass.getProductTax(), "$", parkingPass.getTotalCost());

							} else {

								String itemString = parkingPass.getProductTypeName() + " (" + parkingPass.getItemCount()
										+ " units @ " + parkingPass.getParkingFee() + ")";

								System.out.printf("%-11s%-79s%s%8.2f%5s%8.2f%5s%8.2f%n", parkingPass.getProductCode(),
										itemString, "$", parkingPass.getDetailSubTotal(), "$",
										parkingPass.getProductTax(), "$", parkingPass.getTotalCost());

							}
						}
						if (p.getProductType().equals("S")) {

							SeasonPass seasonPass = (SeasonPass) p;

							String itemString = seasonPass.getProductTypeName() + " - " + seasonPass.getName();

							if (seasonPass.hasProtrated()) {

								System.out.printf("%-11s%-79s%s%8.2f%5s%8.2f%5s%8.2f%n", seasonPass.getProductCode(),
										itemString, "$", seasonPass.getDetailSubTotal(), "$",
										seasonPass.getProductTax(), "$", seasonPass.getTotalCost());

								System.out.printf("%-11s(%s units @ $%s/unit prorated %s/%s days + $8 fee/unit) %n",
										" ", seasonPass.getItemCount(), seasonPass.getCost(),
										seasonPass.getDaysRemaining(), seasonPass.getTotalSeasonDays());
							} else {

								System.out.printf("%-11s%-79s%s%8.2f%5s%8.2f%5s%8.2f%n", seasonPass.getProductCode(),
										itemString, "$", seasonPass.getDetailSubTotal(), "$",
										seasonPass.getProductTax(), "$", seasonPass.getTotalCost());

								System.out.printf("%-11s(%s units @ $%s/unit + $8 fee/unit) %n", " ",
										seasonPass.getItemCount(), seasonPass.getCost());

							}

						}
						if (p.getProductType().equals("R")) {

							Refreshment refreshment = (Refreshment) p;

							if (refreshment.isHasDiscount()) {

								String itemString = refreshment.getName() + " (" + refreshment.getItemCount()
										+ " units @ " + refreshment.getCost() + "/unit with 5% off)";

								System.out.printf("%-11s%-79s%s%8.2f%5s%8.2f%5s%8.2f%n", refreshment.getProductCode(),
										itemString, "$", refreshment.getDetailSubTotal(), "$",
										refreshment.getProductTax(), "$", refreshment.getTotalCost());

							} else {

								String itemString = refreshment.getName() + " (" + refreshment.getItemCount()
										+ " units @ " + refreshment.getCost() + ")";

								System.out.printf("%-11s%-79s%s%8.2f%5s%8.2f%5s%8.2f%n", refreshment.getProductCode(),
										itemString, "$", refreshment.getDetailSubTotal(), "$",
										refreshment.getProductTax(), "$", refreshment.getTotalCost());
							}
						}

					}

					// products
				}

			}
			System.out.printf("%n%126s%n", "====================================");
			System.out.printf("%-10s%81s%8.2f%5s%8.2f%5s%8.2f", "SUB-TOTALS", "$", i.getTotalDetailCost(), "$",
					i.getTotalTaxAmount(), "$", i.getTotalSubTotal());

			if (i.getCustomerTypeName() == "General") {

				System.out.printf("%n%-10s%107s%8.2f", "TOTAL", "$", i.getFinalTotal());

			} else if (i.getCustomerTypeName() == "Student") {
				System.out.printf("%n%-30s%87s%8.2f", "DISCOUNT (8% STUDENT & NO TAX)", "$",
						i.getTotalDiscountAmount());
				System.out.printf("%n%-25s%92s%8.2f", "ADDITIONAL FEE (STUDENT)", "$", i.getStudentFee());
				System.out.printf("%n%-10s%107s%8.2f", "TOTAL", "$", i.getFinalTotal());

			} else {
				System.out.println("Invalid Customer");
			}

			System.out.println();
			System.out.println();
			System.out.println("\t\t" + "Thank you for your purchase!");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();

		}

		System.out.printf(
				"=============================================================================================================================");

	}

}