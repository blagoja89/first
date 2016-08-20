package by.mavs.railwayservice.bean;

import java.util.List;

import org.apache.log4j.Logger;

import by.mavs.railwayservice.dao.MessageDao;
import by.mavs.railwayservice.entity.Message;
import by.mavs.railwayservice.exception.DAOTechnicalException;

public class MessageBean {

	private static final Logger LOG = Logger.getLogger(MessageBean.class);

	private List<Message> messageList;

	public List<Message> getMessageList() {

		MessageDao messageDao = new MessageDao();
		try {
			messageList = messageDao.findAll();
		} catch (DAOTechnicalException e) {
			LOG.error(e.getMessage());
		}

		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

}
