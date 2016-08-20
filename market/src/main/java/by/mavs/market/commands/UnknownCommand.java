package by.mavs.market.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.mavs.market.exceptions.LogicException;
import by.mavs.market.exceptions.TechnicalException;


public final class UnknownCommand implements ICommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws TechnicalException, LogicException {
		
		throw new LogicException("Unknown command!");
	}

}
