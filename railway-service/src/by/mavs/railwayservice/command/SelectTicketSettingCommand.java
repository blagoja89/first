package by.mavs.railwayservice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.mavs.railwayservice.bean.OrderBean;
import by.mavs.railwayservice.entity.TrainWrapper;
import by.mavs.railwayservice.util.ConfigurationManager;

public class SelectTicketSettingCommand implements ActionCommand {
	private static final String SELECT_TRAIN_ID_ATRIBUTE = "select_train_id";
	private static final String STAT_DEP_ATRIBUTE = "stationDep";
	private static final String STAT_AR_ATRIBUTE = "stationArr";
	private static final String DATE_ATRIBUTE = "timeDep";

	private static final String SELECTED_TRAIN_WRAPPER_ATRIBUTE = "selectedTrain";
	private static final String TRAIN_LIST_ATRIBUTE = "trainList";

	private static final Logger LOG = Logger
			.getLogger(SelectTicketSettingCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		LOG.debug("create object of userBean");

		String selectTrainId = request.getParameter(SELECT_TRAIN_ID_ATRIBUTE);
		TrainWrapper selectedTrain = null;
		List<TrainWrapper> trainList = (List<TrainWrapper>) request
				.getSession().getAttribute(TRAIN_LIST_ATRIBUTE);
		if (trainList != null)
			for (TrainWrapper trainWrapper : trainList) {
				if (trainWrapper.getTrainId() == Integer.valueOf(selectTrainId))
					selectedTrain = trainWrapper;
			}
		request.getSession().setAttribute(TRAIN_LIST_ATRIBUTE, null);
		request.getSession().setAttribute(STAT_DEP_ATRIBUTE, null);
		request.getSession().setAttribute(STAT_AR_ATRIBUTE, null);
		request.getSession().setAttribute(DATE_ATRIBUTE, null);
		request.getSession().setAttribute(SELECTED_TRAIN_WRAPPER_ATRIBUTE, selectedTrain);

		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.CHOOSE_TICKET_WAGON_PAGE);
		return page;
	}

}
