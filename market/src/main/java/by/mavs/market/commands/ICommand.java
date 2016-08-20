package by.mavs.market.commands;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.mavs.market.exceptions.LogicException;
import by.mavs.market.exceptions.TechnicalException;


public interface ICommand {

	final ReadWriteLock INSTANCE = new ReentrantReadWriteLock();

	void execute(HttpServletRequest request, HttpServletResponse response)
			throws TechnicalException, LogicException;

}
