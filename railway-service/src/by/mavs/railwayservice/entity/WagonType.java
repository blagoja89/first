package by.mavs.railwayservice.entity;

public enum WagonType {
	GENERAL(1, 81, 1.0), PLACCARD(2, 56, 1.2), CUPE(3, 36, 1.4), SV(4, 18, 1.6);

	int id;
	int numberPlace;
	double coeff;

	private WagonType(int id, int size, double coeff) {
		numberPlace = size;
		this.id = id;
		this.coeff = coeff;
	}

	public int getNumberPlace() {
		return numberPlace;
	}

	public int getId() {
		return id;
	}

	public double getCoeff() {
		return coeff;
	}

	public static WagonType getWagonTypeById(int id) {
		for (WagonType wt : values()) {
			if (wt.getId() == id)
				return wt;
		}
		return null;
	}
}
