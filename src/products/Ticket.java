package products;

public abstract class Ticket implements Product {

	private String productCode;
	private String productType;

	public Ticket(String productCode, String productType) {
		this.productCode = productCode;
		this.productType = productType;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getProductType() {
		return productType;
	}

}
