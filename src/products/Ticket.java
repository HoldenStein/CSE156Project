package products;

public abstract class Ticket implements Product {

	private String productCode;
	private String productType;

	public Ticket(String productCode, String productType) {
		this.productCode = productCode;
		this.productType = productType;
	}

	public double getTicketTax() {
		return 0.06;
	}

	@Override
	public String getProductCode() {
		return productCode;
	}

	@Override
	public String getProductType() {
		return productType;
	}

	@Override
	public double getProductTax() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalCost() {
		// TODO Auto-generated method stub
		return 0;
	}

}
