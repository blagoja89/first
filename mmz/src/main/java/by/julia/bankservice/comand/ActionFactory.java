package by.julia.bankservice.comand;


import by.julia.bankservice.util.MessageManagerUtil;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
	private static final String COMMAND = "command";
	private static final String WRONG_ACTION = "wrongAction";
	private static final String MESSAGE_WRONG_ACTION = "message.wrongaction";

	/**
	 * 
	 * @param request
	 * @return
	 */
	public ActionCommand defineCommand(HttpServletRequest request) {
		String action = request.getParameter(COMMAND);
		ActionCommand command = new EmptyCommand();
		if (action == null || action.isEmpty()) {
			return command;
		}
		try {
			CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
			command = commandEnum.getCurrentCommand();

		} catch (IllegalArgumentException e) {
			request.setAttribute(WRONG_ACTION, action + MessageManagerUtil.getProperty(MESSAGE_WRONG_ACTION));
		}
		return command;
	}
}
