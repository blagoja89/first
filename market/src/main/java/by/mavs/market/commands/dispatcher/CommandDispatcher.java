package by.mavs.market.commands.dispatcher;

import by.mavs.market.commands.ICommand;
import by.mavs.market.commands.UnknownCommand;
import by.mavs.market.exceptions.TechnicalException;

/**
 * The Class CommandDispathcer provides method for commands management.
 */
public final class CommandDispatcher {
	private static final String MESSAGE_WRONG_ACTION = "Incorrect action!";
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static ICommand getCommand(String action) throws TechnicalException{
		ICommand command = new UnknownCommand();
		if (action == null || action.isEmpty()) {
			return command;
		}
		try {
			CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
			command = commandEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			throw new TechnicalException(MESSAGE_WRONG_ACTION);
		}
		return command;
	}
}
