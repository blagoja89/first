package by.mavs.market.commands;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.mavs.market.exceptions.TechnicalException;
import by.mavs.market.util.StringHolder;
import by.mavs.market.util.XSLManager;
import static by.mavs.market.util.StringHolder.*;

public final class ViewProductsCommand implements ICommand {
	private static final String PRODUCT_XSL = "/products.xsl";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws TechnicalException {
		PrintWriter resultWriter = null;
		try {
			resultWriter = response.getWriter();
		} catch (IOException e) {
			throw new TechnicalException("IOException: " + e);
		}

		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put(CAT_NAME, request.getParameter(CAT_NAME));
		paramsMap.put(SUBCAT_NAME, request.getParameter(SUBCAT_NAME));
		Lock lock = INSTANCE.readLock();
		lock.lock();
		try {
			XSLManager.makeTransform(StringHolder.MARKET_XML, PRODUCT_XSL, resultWriter,
					paramsMap);
		} catch (TechnicalException e) {
			throw new TechnicalException("Can not create transformer", e);
		} finally {
			lock.unlock();
		}
	}
}