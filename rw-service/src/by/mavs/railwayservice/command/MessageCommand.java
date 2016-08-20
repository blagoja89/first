package by.mavs.railwayservice.command;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import by.mavs.railwayservice.dao.MessageDao;
import by.mavs.railwayservice.entity.Message;
import by.mavs.railwayservice.exception.DAOTechnicalException;
import by.mavs.railwayservice.util.ConfigurationManager;


public class MessageCommand implements ActionCommand {

	private static final Logger LOG = Logger
			.getLogger(MessageCommand.class);
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		MessageDao mesDao = new MessageDao();
		Message message = createNewMessage(request);
		try {
			mesDao.save(message);
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		}
		LOG.debug("You sent message");

		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.HOME_PAGE);

		return page;
	}

	private Message createNewMessage(HttpServletRequest request) {
		Message message = new Message();
		message.setId(UUID.randomUUID().hashCode());
		message.setName(request.getParameter("name"));
		message.setMail(request.getParameter("mail"));
		message.setMessage(request.getParameter("message"));
		message.setType(request.getParameter("typeMes"));
		return message;
	}
	
}
