package by.mavs.railwayservice.bean;

import by.mavs.railwayservice.bean.CalculationTicketPrice;
import junit.framework.Assert;
import junit.framework.TestCase;


public class CalculationTicketPriceTest extends TestCase{

	public void testTicketPrice(){
		CalculationTicketPrice ticketPrice = new CalculationTicketPrice();
		int basicPrice = CalculationTicketPrice.BASIC_PRICE;
		long distance = 500;
		double priceKM = 1;
		double estimatedDistance = 1100;
		long expected = (long) (estimatedDistance * priceKM * 2 + basicPrice);
		long actual = ticketPrice.ticketPrice(distance, priceKM);
		Assert.assertEquals(expected, actual, 0.01);
	}
}
