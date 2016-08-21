package by.mavs.railwayservice.dao;

import by.mavs.railwayservice.entity.Station;

public class StationDao extends AbstractDao<Station> {

	@Override
	public Class<Station> getEntityClass() {
		return Station.class;
	}
		
}
