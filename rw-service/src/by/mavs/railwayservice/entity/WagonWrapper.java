package by.mavs.railwayservice.entity;

import java.util.List;

public class WagonWrapper {
	private Wagon wagon;
	private List<Integer> freePlace;
	private String price;

	public WagonWrapper(Wagon wagon, List<Integer> freePlace, String price) {
		super();
		this.wagon = wagon;
		this.freePlace = freePlace;
		this.price = price;
	}

	public Wagon getWagon() {
		return wagon;
	}

	public void setWagon(Wagon wagon) {
		this.wagon = wagon;
	}

	public List<Integer> getFreePlace() {
		return freePlace;
	}

	public void setFreePlace(List<Integer> freePlace) {
		this.freePlace = freePlace;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
