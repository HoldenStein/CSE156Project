package products;

public abstract class Service implements Product{

	private String productCode;
	private String productType;
	
	public Service(String productCode,String productType) {
		this.productCode = productCode;
		this.productType = productType;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public String getProductType() {
		if(this.productType.equals("P")) {
			return "ParkingPass";
		} else if(this.productType.equals("R")) {
			return "Refreshment";
		}
		return this.productType;
	}

	public String getProduct() {
		return "Service";
	}

}
