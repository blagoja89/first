package by.mavs.railwayservice.command;


import javax.servlet.http.HttpServletRequest;

import by.mavs.railwayservice.util.MessageManagerUtil;



public class ActionFactory {
	public ActionCommand defineCommand(HttpServletRequest request) {
		String action = request.getParameter("command");
		ActionCommand command = new EmptyCommand();
		if (action == null || action.isEmpty()) {
			return command;
		}
		try {
			CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
			command = commandEnum.getCurrentCommand();

		} catch (IllegalArgumentException e) {
			request.setAttribute(
					"wrongAction",
					action
							+ MessageManagerUtil
									.getProperty("message.wrongaction"));
		}
		return command;
	}
}
