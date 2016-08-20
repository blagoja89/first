package by.mavs.railwayservice.util;

public class CalculationTicketPrice {

	public static final int BASIC_PRICE = 1000;
	public static double PRICE_KM = 15;

	public static long ticketPrice(long distance, double priceKM) {
		return (long) ((estimatedDistance(distance)) * priceKM * 2 + BASIC_PRICE);

	}

	private static double estimatedDistance(long distance) {
		double estimatedDistance = (((distance / zoneNumber(distance)) - (zoneInitialDistance(distance) / zoneNumber(distance)))
				* zoneInitialDistance(distance) / 2 + distance);
		return estimatedDistance;
	}

	private static int zoneInitialDistance(long distance) {
		int initialDistanceZone = 0;
		if (distance > 0 && distance <= 200) {
			initialDistanceZone = 0;
		}
		if (distance > 200 && distance <= 700) {
			initialDistanceZone = 200;
		}

		if (distance > 700 && distance <= 1700) {
			initialDistanceZone = 700;
		}

		if (distance > 1700 && distance <= 3700) {
			initialDistanceZone = 1700;
		}

		if (distance > 3700 && distance <= 6700) {
			initialDistanceZone = 3700;
		}

		if (distance > 6700) {
			initialDistanceZone = 6700;
		}
		return initialDistanceZone;
	}

	private static int zoneNumber(long distance) {
		int numberZone = 0;
		if (distance > 0 && distance <= 200) {
			numberZone = 10;
		}
		if (distance > 200 && distance <= 700) {
			numberZone = 50;
		}

		if (distance > 700 && distance <= 1700) {
			numberZone = 100;
		}

		if (distance > 1700 && distance <= 3700) {
			numberZone = 200;
		}

		if (distance > 3700 && distance <= 6700) {
			numberZone = 300;
		}

		if (distance > 6700) {
			numberZone = 400;
		}
		return numberZone;
	}
}
