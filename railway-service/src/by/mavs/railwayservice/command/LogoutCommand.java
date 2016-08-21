package by.mavs.railwayservice.command;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import by.mavs.railwayservice.util.ConfigurationManager;
import by.mavs.railwayservice.util.MessageManager;


public class LogoutCommand implements ActionCommand {
	private static final String MESSAGE = "message";
	
	@Override
	public String execute(HttpServletRequest request) {
		
		final Logger LOG = Logger.getLogger(LogoutCommand.class);
			String page = null;
		
			request.getSession(true).invalidate();
			LOG.debug("Session invalidate success");
			
			page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.HOME_PAGE);
			request.setAttribute(MESSAGE, MessageManager.getInstance().getProperty(MessageManager.LOGOUT_MESSAGE));
	
			LOG.debug("Logout success");
		
			return page;
		
	}
}
