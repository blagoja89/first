package by.mavs.railwayservice.dao;

import by.mavs.railwayservice.entity.MapStation;

public class MapStationDao extends AbstractDao<MapStation> {

	@Override
	public Class<MapStation> getEntityClass() {
		return MapStation.class;
	}
		
}
