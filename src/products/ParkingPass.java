package products;

public class ParkingPass extends Service {

	private String code;
	private double parkingFee;

	public ParkingPass(String productCode,String productType, double parkingFee) {
		super(productCode,productType);
		this.parkingFee = parkingFee;
	}

	@Override
	public String getProduct() {
		return this.getProductType();
	}

	@Override
	public double getCost() {
		return this.parkingFee;
	}

	@Override
	public double getTax() {
		return 0.04;
	}


}
