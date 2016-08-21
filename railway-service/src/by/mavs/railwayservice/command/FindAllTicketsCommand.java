package by.mavs.railwayservice.command;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.mavs.railwayservice.dao.TicketDao;
import by.mavs.railwayservice.entity.Ticket;
import by.mavs.railwayservice.entity.User;
import by.mavs.railwayservice.exception.DAOTechnicalException;
import by.mavs.railwayservice.util.ConfigurationManager;
import by.mavs.railwayservice.util.DateUtil;
import by.mavs.railwayservice.util.MessageManager;
import by.mavs.railwayservice.util.MessageManagerUtil;

public class FindAllTicketsCommand implements ActionCommand {
	private static final Logger LOG = Logger
			.getLogger(FindAllTicketsCommand.class);
	private TicketDao ticketDao = new TicketDao();
	private static String TICKET_BETWEEN_DATE_ATRIBUTE = "tiketsBetDate";
	private static final String SELECTED_USER_ATRIBUTE = "user";
	private static final String START_DATE = "startDate";
	private static final String END_DATE = "endDate";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.PROFILE_PAGE_PATH);
		String startDate = request.getParameter(START_DATE);
		String endDate = request.getParameter(END_DATE);
		
		User user = (User) request.getSession().getAttribute(SELECTED_USER_ATRIBUTE);

		List<Ticket> listTickets = null;
		Date dateStart = null;
		Date dateEnd = null;
		try {
			dateStart = DateUtil.parseToDate(startDate);
			dateEnd = DateUtil.parseToDate(endDate);
		} catch (ParseException e) {
		LOG.error("not parse");
		request.setAttribute(ERROR_MESSAGE, MessageManagerUtil
				.getProperty(MessageManager.ENTER_DATE_CORRECT_FORMAT));
		return page;
		}
		
		try {
			listTickets = ticketDao.findBetweenDate(dateStart, dateEnd, user.getId());
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		}
		

		if (listTickets == null) {
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.INCORRECT_DATE_ERROR_MESSAGES));
		} else {
			request.setAttribute(TICKET_BETWEEN_DATE_ATRIBUTE,
					listTickets);
		}

		return page;
	}

}
