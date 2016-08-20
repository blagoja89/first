package by.mavs.railwayservice.command;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import by.mavs.railwayservice.bean.OrderBean;
import by.mavs.railwayservice.entity.ReservedTicket;
import by.mavs.railwayservice.entity.Ticket;
import by.mavs.railwayservice.entity.TrainDate;
import by.mavs.railwayservice.entity.TrainWrapper;
import by.mavs.railwayservice.entity.User;
import by.mavs.railwayservice.util.ConfigurationManager;
import by.mavs.railwayservice.util.MessageManager;
import by.mavs.railwayservice.util.MessageManagerUtil;

public class PaymentTicketCommand implements ActionCommand {
	private static final String SELECTED_TRAIN_WRAPPER_ATRIBUTE = "selectedTrain";
	private static final String SELECTED_USER_ATRIBUTE = "user";
	private static final String SELECTED_CURRENT_TICKET_ATRIBUTE = "currentTicket";
	private static final String STATUS_PAYMENT_TICKET_ATRIBUTE = "statusTicket";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE = "message";

	private OrderBean cb = new OrderBean();

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		User user = (User) request.getSession().getAttribute(
				SELECTED_USER_ATRIBUTE);
		Ticket ticket = (Ticket) request.getSession().getAttribute(
				SELECTED_CURRENT_TICKET_ATRIBUTE);
		TrainWrapper trainWrapper = (TrainWrapper) request.getSession()
				.getAttribute(SELECTED_TRAIN_WRAPPER_ATRIBUTE);
		if (chekBillForPayment(user, ticket)) {
			request.setAttribute(ERROR_MESSAGE, MessageManagerUtil
					.getProperty(MessageManager.ENOUGH_MONEY_ERROR_MESSAGES));
			request.getSession().setAttribute(STATUS_PAYMENT_TICKET_ATRIBUTE,
					false);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.PAYMENT_TICKET_PAGE);
			return page;
		}
		user.getAccount().setBill(
				user.getAccount().getBill() - new Double(ticket.getPrice()));
		ticket.setUser(user);
		cb.saveTicket(ticket);
		ReservedTicket rt = new ReservedTicket();
		rt.setId(Math.abs(Math.abs(UUID.randomUUID().hashCode())));
		rt.setTicket(ticket);
		rt.setTrainDate(new TrainDate(trainWrapper.getTrainDateId()));
		cb.saveReservedTicket(rt);

		request.setAttribute(MESSAGE, MessageManagerUtil
				.getProperty(MessageManager.PAYMENT_TICKET_SUCCESS_MESSAGES));
		request.getSession().setAttribute(STATUS_PAYMENT_TICKET_ATRIBUTE, true);
		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.PAYMENT_TICKET_PAGE);
		return page;
	}

	private boolean chekBillForPayment(User user, Ticket ticket) {
		Double value = Double.valueOf((ticket.getPrice()));
		return (user.getAccount().getBill() < value);
	}
}
