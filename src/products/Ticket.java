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
		if(this.productType.equals("M")) {
			return "MovieTicket";
		} else if(this.productType.equals("S")) {
			return "SeasonPass";
		}
		return productType;
	}

	public String getProduct() {
		return "Ticket";
	}

}
