package by.mavs.market.beans;

public final class Product {

	private String model;
	private String producer;
	private String color;
	private double price;
	private boolean notInStock;
	private String dateOfIssue;

	public Product(String model, String color, String dateOfIssue,
			double price, String producer, boolean notInStock) {
		this.model = model;
		this.producer = producer;
		this.dateOfIssue = dateOfIssue;
		this.notInStock = notInStock;
		this.price = price;
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isNotInStock() {
		return notInStock;
	}

	public void setNotInStock(boolean notInStock) {
		this.notInStock = notInStock;
	}

	public String getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result
				+ ((dateOfIssue == null) ? 0 : dateOfIssue.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + (notInStock ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((producer == null) ? 0 : producer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (dateOfIssue == null) {
			if (other.dateOfIssue != null)
				return false;
		} else if (!dateOfIssue.equals(other.dateOfIssue))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (notInStock != other.notInStock)
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (producer == null) {
			if (other.producer != null)
				return false;
		} else if (!producer.equals(other.producer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [model=");
		builder.append(model);
		builder.append(", producer=");
		builder.append(producer);
		builder.append(", color=");
		builder.append(color);
		builder.append(", price=");
		builder.append(price);
		builder.append(", notInStock=");
		builder.append(notInStock);
		builder.append(", dateOfIssue=");
		builder.append(dateOfIssue);
		builder.append("]");
		return builder.toString();
	}
	
	

}
