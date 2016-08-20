package by.mavs.railwayservice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.mavs.railwayservice.bean.OrderBean;
import by.mavs.railwayservice.entity.Train;
import by.mavs.railwayservice.entity.TrainWrapper;
import by.mavs.railwayservice.util.ConfigurationManager;
import by.mavs.railwayservice.util.DateUtil;
import by.mavs.railwayservice.util.MessageManager;
import by.mavs.railwayservice.util.MessageManagerUtil;

public class FindByOrderCommand implements ActionCommand{
	private static final String LIST_TRAIN_ATRIBUTE = "trainList";
	private static final String STAT_DEP_ATRIBUTE = "stationDep";
	private static final String STAT_AR_ATRIBUTE = "stationArr";
	private static final String DATE_ATRIBUTE = "timeDep";
	
	private static final Logger LOG = Logger
			.getLogger(FindByOrderCommand.class);
	private OrderBean cb = new OrderBean();

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		LOG.debug("create object of userBean");
		String departureStation = request.getParameter(STAT_DEP_ATRIBUTE);
		String arrivalStation = request.getParameter(STAT_AR_ATRIBUTE);
		String date = request.getParameter(DATE_ATRIBUTE);
		List<TrainWrapper> trainWrapperList = null;
		if(departureStation != null && arrivalStation != null && date != null && !date.isEmpty()){
			trainWrapperList = cb.getTrainWrapperList(departureStation, arrivalStation, DateUtil.parseToDate(date));
		}
		request.getSession().setAttribute(LIST_TRAIN_ATRIBUTE, trainWrapperList);
		request.getSession().setAttribute(STAT_DEP_ATRIBUTE, departureStation.toUpperCase());
		request.getSession().setAttribute(STAT_AR_ATRIBUTE, arrivalStation.toUpperCase());
		request.getSession().setAttribute(DATE_ATRIBUTE, date);
		
		if (trainWrapperList == null) {
			request.setAttribute("errorMessage", MessageManagerUtil
					.getProperty(MessageManager.EMPTY_SEARCH_ERROR_MESSAGES));
		}
		
		page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.ORDER_PAGE);
		return page;
	}
	

}
