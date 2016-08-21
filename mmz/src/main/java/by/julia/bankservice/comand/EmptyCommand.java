package by.julia.bankservice.comand;


import by.julia.bankservice.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.HOME_PAGE);
		return page;
	}
}
