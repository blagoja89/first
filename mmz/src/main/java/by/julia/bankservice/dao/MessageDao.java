package by.julia.bankservice.dao;


import by.julia.bankservice.entity.Message;

public class MessageDao extends AbstractDao<Message>{

	@Override
	public Class<Message> getEntityClass() {
		return Message.class;
	}

}
