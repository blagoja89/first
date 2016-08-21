package by.julia.bankservice.comand;

import by.julia.bankservice.dao.MessageDao;
import by.julia.bankservice.entity.Message;
import by.julia.bankservice.exception.DAOTechnicalException;
import by.julia.bankservice.util.ConfigurationManager;
import by.julia.bankservice.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


public class MessageCommand implements ActionCommand {
	private static final String MESSAGE = "message";
	private static final String NAME = "name";
	private static final String MAIL = "mail";
	private static final String TYPE_MES = "typeMes";

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
		
		request.setAttribute(MESSAGE, MessageManager.getInstance()
				.getProperty(MessageManager.YOU_SENT_MESSAGE));
		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.HOME_PAGE);

		return page;
	}

	private Message createNewMessage(HttpServletRequest request) {
		Message message = new Message();
		message.setId(Math.abs(UUID.randomUUID().hashCode()));
		message.setName(request.getParameter(NAME));
		message.setMail(request.getParameter(MAIL));
		message.setMessage(request.getParameter(MESSAGE));
		message.setType(request.getParameter(TYPE_MES));
		return message;
	}
	
}
