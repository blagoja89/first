package by.mavs.market.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;

import by.mavs.market.exceptions.TechnicalException;
import by.mavs.market.util.StringHolder;
import by.mavs.market.util.XSLManager;

public final class ViewCategoriesCommand implements ICommand {

	private static final String CATEGORIES_XSL = "/categories.xsl";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws TechnicalException {
		PrintWriter resultWriter = null;
		try {
			resultWriter = response.getWriter();
		} catch (IOException e) {
			throw new TechnicalException("IOException: ", e);
		}

		Lock lock = INSTANCE.readLock();
		lock.lock();
		try {
			XSLManager.makeTransform(StringHolder.MARKET_XML, CATEGORIES_XSL, resultWriter);
		} catch (TechnicalException e) {
			throw new TechnicalException("Can not create transformer", e);
		} finally {
			lock.unlock();
		}
	}

}
