package by.mavs.railwayservice.entity.enums;

public enum AccessLevel {

	GUEST(0, "Guest"), USER(1, "User"), ADMIN(2, "Admin");

	private int level;
	private String name;

	private AccessLevel(int level, String name) {
		this.level = level;
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static AccessLevel getByName(String name) {

		for (AccessLevel al : values()) {
			if (al.getName() == name)
				return al;
		}
		return null;
	}

	public static AccessLevel getByLevel(int level) {

		for (AccessLevel al : values()) {
			if (al.getLevel() == level)
				return al;
		}
		return null;
	}

}
