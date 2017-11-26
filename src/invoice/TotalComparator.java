package invoice;

import java.util.Comparator;

public class TotalComparator implements Comparator<Invoice> {

	@Override
	public int compare(Invoice inv1, Invoice inv2) {

		if (inv1.getTotalSubTotal() < inv2.getTotalSubTotal()) {
			return -1;
		} else if (inv1.getTotalSubTotal() > inv2.getTotalSubTotal()) {
			return 1;
		} else {
			return 0;

		}

	}

}
