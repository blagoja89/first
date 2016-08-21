package by.mavs.railwayservice.command;

import javax.servlet.http.HttpServletRequest;

import by.mavs.railwayservice.util.ConfigurationManager;

public class EmptyCommand implements ActionCommand {
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.HOME_PAGE);
		return page;
	}
}
