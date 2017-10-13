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
		return productType;
	}

}
