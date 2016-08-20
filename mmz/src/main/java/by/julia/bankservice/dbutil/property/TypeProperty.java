package by.julia.bankservice.dbutil.property;

import java.util.Date;

public enum TypeProperty {
	INT(Integer.TYPE), DATE(Date.class), STRING(String.class), BOOLEAN(Boolean.class);

	private Class type;

	private TypeProperty(Class type) {
		this.type = type;
	}

	public Class getType() {
		return type;
	}

}
