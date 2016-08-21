package by.mavs.railwayservice.command;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.mavs.railwayservice.bean.OrderBean;
import by.mavs.railwayservice.entity.ReservedTicket;
import by.mavs.railwayservice.entity.Ticket;
import by.mavs.railwayservice.entity.TrainDate;
import by.mavs.railwayservice.entity.TrainWrapper;
import by.mavs.railwayservice.entity.User;
import by.mavs.railwayservice.entity.WagonWrapper;
import by.mavs.railwayservice.util.ConfigurationManager;

public class BuyTicketCommand implements ActionCommand {
	private static final String SELECTED_TRAIN_WRAPPER_ATRIBUTE = "selectedTrain";
	private static final String SELECTED_PLACE_ATRIBUTE = "placeItem";
	private static final String SELECTED_WAGON_ID_ATRIBUTE = "select_wagon";
	private static final String SELECTED_USER_ATRIBUTE = "user";
	private static final String SELECTED_CURRENT_TICKET_ATRIBUTE = "currentTicket";
	private static final String STATUS_PAYMENT_TICKET_ATRIBUTE = "statusTicket";

	private static final Logger LOG = Logger.getLogger(BuyTicketCommand.class);
	private OrderBean cb = new OrderBean();

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		System.out.println();
		LOG.debug("create object of userBean");
		String selectedPlace = request.getParameter(SELECTED_PLACE_ATRIBUTE);
		String selectedWagon = request.getParameter(SELECTED_WAGON_ID_ATRIBUTE);
		User user = (User) request.getSession().getAttribute(
				SELECTED_USER_ATRIBUTE);
		TrainWrapper trainWrapper = (TrainWrapper) request.getSession()
				.getAttribute(SELECTED_TRAIN_WRAPPER_ATRIBUTE);
		WagonWrapper ww = trainWrapper.getWagonWrapperById(Integer
				.valueOf(selectedWagon));
		
		ww.getFreePlace().remove(Integer.valueOf(selectedPlace));
		Ticket ticket = new Ticket();
		ticket.setId(Math.abs(UUID.randomUUID().hashCode()));
		ticket.setNumber(UUID.randomUUID().toString());
		ticket.setPrice(ww.getPrice());
		ticket.setPlace(Integer.valueOf(selectedPlace));
		ticket.setStationDeparture(trainWrapper.getStationDeparture());
		ticket.setStationArrival(trainWrapper.getStationArrival());
		ticket.setTimeDeparture(trainWrapper.getTimeDeparture());
		ticket.setTimeArrival(trainWrapper.getTimeArrival());
		ticket.setTrainName(trainWrapper.getTrainName());
		ticket.setTimeTrevel(trainWrapper.getTimeTrevel());
		ticket.setWagon(ww.getWagon());
		
		request.getSession().setAttribute(SELECTED_CURRENT_TICKET_ATRIBUTE, ticket);
		request.getSession().setAttribute(STATUS_PAYMENT_TICKET_ATRIBUTE, false);
		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.PAYMENT_TICKET_PAGE);
		return page;
	}

}
