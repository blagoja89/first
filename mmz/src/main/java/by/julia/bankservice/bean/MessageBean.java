package by.julia.bankservice.bean;

import by.julia.bankservice.dao.MessageDao;
import by.julia.bankservice.entity.Message;
import by.julia.bankservice.exception.DAOTechnicalException;
import org.apache.log4j.Logger;

import java.util.List;

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
