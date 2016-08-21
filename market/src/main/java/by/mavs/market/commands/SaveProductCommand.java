package by.mavs.market.commands;

import static by.mavs.market.util.StringHolder.CAT_NAME;
import static by.mavs.market.util.StringHolder.COLOR;
import static by.mavs.market.util.StringHolder.DATE_OF_ISSUE;
import static by.mavs.market.util.StringHolder.MODEL;
import static by.mavs.market.util.StringHolder.NOT_IN_STOCK;
import static by.mavs.market.util.StringHolder.PRICE;
import static by.mavs.market.util.StringHolder.PRODUCER;
import static by.mavs.market.util.StringHolder.SUBCAT_NAME;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.mavs.market.exceptions.LogicException;
import by.mavs.market.exceptions.TechnicalException;
import by.mavs.market.util.StringHolder;
import by.mavs.market.util.XSLManager;
import by.mavs.market.util.Validator;

/**
 * Validates new product's params and adds to the stock. If validation is'n
 * successful, then generates adding form
 */

public final class SaveProductCommand implements ICommand {

	private static final String SAVE_PRODUCT_XSL = "/saveProduct.xsl";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws TechnicalException, LogicException {
		
		String catName = request.getParameter("catName");
		String subcatName = request.getParameter("subcatName");
	
		Writer buffer = new StringWriter();
	
		Map<String, String[]> requestParamMap = request.getParameterMap();
		Map<String, Object> paramsMap = fillParamsMap(requestParamMap);
		Validator validator = new Validator();
		paramsMap.put("validator", validator);
		
		File marketFile = new File(StringHolder.MARKET_REAL_PATH);
		long lastMod = marketFile.lastModified();
	
		Lock lock = INSTANCE.readLock();
		lock.lock();
		try {
			XSLManager.makeTransform(StringHolder.MARKET_XML, SAVE_PRODUCT_XSL, buffer, paramsMap);
		} finally {
			lock.unlock();
		}
		try {
		if (validator.getErrors().isEmpty()) {
			Lock writeLock = INSTANCE.writeLock();
			writeLock.lock();
		
			try {
				if (lastMod != marketFile.lastModified()) {
					XSLManager.makeTransform(StringHolder.MARKET_XML, SAVE_PRODUCT_XSL, buffer, paramsMap);
				}
				Writer fileWriter = new PrintWriter(marketFile, "UTF-8");
				fileWriter.write(buffer.toString());
				fileWriter.flush();
				fileWriter.close();
			} catch (TechnicalException e) {
				throw new TechnicalException("Can not create transformer", e);
			} finally {
				writeLock.unlock();
			}
			response.sendRedirect("Controller?action=products&catName="
					+ catName + "&subcatName=" + subcatName);
	
		} else {
			Writer out = response.getWriter();
			out.write(buffer.toString());
		}
		}catch (IOException e) {
			throw new TechnicalException("IOException: " + e);
		}
	}

	private Map<String, Object> fillParamsMap(Map<String, String[]> requestParamMap) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		for(Entry<String, String[]> setParams: requestParamMap.entrySet()) {
			for(String value: setParams.getValue()) {
				switch (setParams.getKey()) {
				case CAT_NAME: paramsMap.put(CAT_NAME, value); break;
				case SUBCAT_NAME: paramsMap.put(SUBCAT_NAME, value); break;
				case MODEL: paramsMap.put(MODEL, value); break;
				case COLOR: paramsMap.put(COLOR, value); break;
				case DATE_OF_ISSUE: paramsMap.put(DATE_OF_ISSUE, value); break;
				case PRICE: paramsMap.put(PRICE, value == null ? "" : value); break;
				case PRODUCER: paramsMap.put(PRODUCER, value); break;
				case NOT_IN_STOCK: paramsMap.put(NOT_IN_STOCK, "true".equals(value)); break;
				}
			}
		}
		return paramsMap;
	}

}
