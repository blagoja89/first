package by.mavs.market.commands;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.mavs.market.exceptions.TechnicalException;
import static by.mavs.market.util.StringHolder.*;
import by.mavs.market.util.XSLManager;

/**
 * Generates adding form
 */
public final class AddProductCommand implements ICommand {
	private static final String ADD_PRODUCT_XSL = "/addProductForm.xsl";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws TechnicalException {
		PrintWriter resultWriter = null;
		try {
			resultWriter = response.getWriter();
		} catch (IOException e) {
			throw new TechnicalException("IOException: " + e);
		}
		String catName = request.getParameter(CAT_NAME);
		String subcatName = request.getParameter(SUBCAT_NAME);
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put(CAT_NAME, catName);
		paramsMap.put(SUBCAT_NAME, subcatName);

		Lock lock = INSTANCE.readLock();
		lock.lock();
		try {
			XSLManager.makeTransform(MARKET_XML, ADD_PRODUCT_XSL, resultWriter,
					paramsMap);
		} catch (TechnicalException e) {
			throw new TechnicalException("Can not create transformer", e);
		} finally {
			lock.unlock();
		}
	}
}
