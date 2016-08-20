package by.mavs.railwayservice.dao;

import by.mavs.railwayservice.entity.Message;

public class MessageDao extends AbstractDao<Message>{

	@Override
	public Class<Message> getEntityClass() {
		return Message.class;
	}

}
