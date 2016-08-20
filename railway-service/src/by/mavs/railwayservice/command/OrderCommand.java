package by.mavs.railwayservice.command;

import javax.servlet.http.HttpServletRequest;


import by.mavs.railwayservice.util.ConfigurationManager;

public class OrderCommand implements ActionCommand{
	private static final String LIST_TRAIN_ATRIBUTE = "trainList";
	private static final String STAT_DEP_ATRIBUTE = "stationDep";
	private static final String STAT_AR_ATRIBUTE = "stationArr";
	private static final String DATE_ATRIBUTE = "timeDep";
	
	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		resetData(request);
		page = ConfigurationManager.getInstance().getProperty(
		ConfigurationManager.ORDER_PAGE);
		return page;
	}
	
	private void resetData(HttpServletRequest request){
		request.getSession().setAttribute(LIST_TRAIN_ATRIBUTE, null);
		request.getSession().setAttribute(STAT_DEP_ATRIBUTE,null);
		request.getSession().setAttribute(STAT_AR_ATRIBUTE, null);
		request.getSession().setAttribute(DATE_ATRIBUTE, null);
	}

}
