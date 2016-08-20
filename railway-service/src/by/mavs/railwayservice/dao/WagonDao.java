package by.mavs.railwayservice.dao;

import by.mavs.railwayservice.entity.Wagon;

public class WagonDao extends AbstractDao<Wagon> {

	@Override
	public Class<Wagon> getEntityClass() {
		return Wagon.class;
	}
}
