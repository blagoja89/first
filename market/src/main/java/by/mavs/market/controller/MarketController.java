package by.mavs.market.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.mavs.market.commands.ICommand;
import by.mavs.market.commands.dispatcher.CommandDispatcher;
import by.mavs.market.exceptions.LogicException;
import by.mavs.market.exceptions.TechnicalException;



public class MarketController extends HttpServlet {
	private static final String ACTION = "action";
	private static final String ERROR_PAGE = "/pages/errors/error.jsp";
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(MarketController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);

	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter(ACTION);
		try {
			ICommand command = CommandDispatcher.getCommand(action);
			command.execute(request, response);
		} catch (TechnicalException | LogicException e ) {
			LOGGER.error("Command execution error" , e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE);		
			dispatcher.forward(request, response);	
		}
	}
}
