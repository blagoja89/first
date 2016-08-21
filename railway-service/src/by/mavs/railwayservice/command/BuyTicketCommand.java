package by.mavs.railwayservice.command;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.mavs.railwayservice.entity.Ticket;
import by.mavs.railwayservice.entity.TrainWrapper;
import by.mavs.railwayservice.entity.WagonWrapper;
import by.mavs.railwayservice.util.ConfigurationManager;
import by.mavs.railwayservice.util.MessageManager;

public class BuyTicketCommand implements ActionCommand {
	private static final String SELECTED_TRAIN_WRAPPER_ATRIBUTE = "selectedTrain";
	private static final String SELECTED_PLACE_ATRIBUTE = "placeItem";
	private static final String SELECTED_WAGON_ID_ATRIBUTE = "select_wagon";
	private static final String SELECTED_CURRENT_TICKET_ATRIBUTE = "currentTicket";
	private static final String STATUS_PAYMENT_TICKET_ATRIBUTE = "statusTicket";
	private static final String SEQ_NO = "seqNo";
	private static final String ERROR_MESSAGE = "errorMessage";

	private static final Logger LOG = Logger.getLogger(BuyTicketCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		LOG.debug("create object of userBean");
		String[] selectedPlaces = request
				.getParameterValues(SELECTED_PLACE_ATRIBUTE);
		String[] selectedWagons = request
				.getParameterValues(SELECTED_WAGON_ID_ATRIBUTE);
		String seqNo = request.getParameter(SEQ_NO);
		
		String selectedPlace = null;
			selectedPlace = selectedPlaces[new Integer(seqNo)];
		
		String selectedWagon = selectedWagons[new Integer(seqNo)];
		TrainWrapper trainWrapper = (TrainWrapper) request.getSession()
				.getAttribute(SELECTED_TRAIN_WRAPPER_ATRIBUTE);
		WagonWrapper ww = trainWrapper.getWagonWrapperById(Integer
				.valueOf(selectedWagon));

		try{
		ww.getFreePlace().remove(Integer.valueOf(selectedPlace));
		}catch(NumberFormatException e){
			LOG.error(e.getMessage());
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance().getProperty(
					MessageManager.ENTER_ONLY_NUMBERS));
			return ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.CHOOSE_TICKET_WAGON_PAGE);
		}
		
		Ticket ticket = new Ticket();
		ticket.setId(Math.abs(UUID.randomUUID().hashCode()));
		ticket.setNumber(UUID.randomUUID().toString());
		ticket.setPrice(ww.getPrice());
		ticket.setPlace(Integer.valueOf(selectedPlace));
		ticket.setStationDeparture(trainWrapper.getStationDeparture());
		ticket.setStationArrival(trainWrapper.getStationArrival());
		ticket.setTimeDeparture(trainWrapper.getDateTimeDeparture());
		ticket.setTimeArrival(trainWrapper.getDateTimeArrival());
		ticket.setTrainName(trainWrapper.getTrainName());
		ticket.setTimeTrevel(trainWrapper.getTimeTrevel());
		ticket.setWagon(ww.getWagon());

		request.getSession().setAttribute(SELECTED_CURRENT_TICKET_ATRIBUTE,
				ticket);
		request.getSession().setAttribute(STATUS_PAYMENT_TICKET_ATRIBUTE, false);
		page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.PAYMENT_TICKET_PAGE);
		return page;
	}

}
