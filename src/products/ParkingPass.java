package products;

public class ParkingPass extends Service {

	private String code;
	private double parkingFee;

	public ParkingPass(String productCode,String productType, double parkingFee) {
		super(productCode,productType);
		this.parkingFee = parkingFee;
	}

	public String getCode() {
		return code;
	}

	public double getParkingFee() {
		return parkingFee;
	}
}
