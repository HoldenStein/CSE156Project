package products;

public class ParkingPass extends Product {

	private String code;
	private double parkingFee;

	public ParkingPass(String productCode, double parkingFee) {
		super(productCode);
		this.parkingFee = parkingFee;
	}

	public String getCode() {
		return code;
	}

	public double getParkingFee() {
		return parkingFee;
	}

}
