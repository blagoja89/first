package by.julia.bankservice.comand;


import by.julia.bankservice.dao.UserDao;
import by.julia.bankservice.entity.User;
import by.julia.bankservice.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class PageCommand implements ActionCommand{

	private static Logger LOG = Logger.getLogger(PageCommand.class);
	private static String PARAM_NAME_PAGE = "pageName";

		
	@Override
	public String execute(HttpServletRequest request){
		
		String page, pg = null;

		//Get from request name page
		pg = request.getParameter(PARAM_NAME_PAGE);
		LOG.debug("Name of page from request = " + pg);
		// definition way by page
		page = ConfigurationManager.getInstance().getProperty(pg);
	
		return page;
	}
}
