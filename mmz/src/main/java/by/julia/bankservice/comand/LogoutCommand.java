package by.julia.bankservice.comand;

import by.julia.bankservice.util.ConfigurationManager;
import by.julia.bankservice.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


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
