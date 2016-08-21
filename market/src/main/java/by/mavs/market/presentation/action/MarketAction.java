package by.mavs.market.presentation.action;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import by.mavs.market.exceptions.TechnicalException;
import by.mavs.market.presentation.form.ProductForm;
import by.mavs.market.util.Validator;
import by.mavs.market.util.XSLManager;
import static by.mavs.market.util.StringHolder.*;


public final class MarketAction extends DispatchAction {
	
	private static final Logger LOGGER = Logger.getLogger(MarketAction.class);

	private static final String CATLIST = "catlist";
	private static final String SUBCATLIST = "subcatlist";
	private static final String PRODUCTS = "productlist";
	private static final String ADD_PRODUCT_XSL = "/addProductForm.xsl";
	private static final String SAVE_PRODUCT_XSL = "/saveProduct.xsl";


	public ActionForward catlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JDOMException, IOException {
		setDocument2Form(form);
		return mapping.findForward(CATLIST);
	}

	public ActionForward subcatlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JDOMException, IOException {
		setDocument2Form(form);
		return mapping.findForward(SUBCATLIST);
	}

	public ActionForward productlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setDocument2Form(form);
		return mapping.findForward(PRODUCTS);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductForm ProductForm = (ProductForm) form;
		Document document = ProductForm.getDoc();
		File catalogFile = new File(MARKET_REAL_PATH);
		Lock writeLock = RWLockSingleton.INSTANCE.writeLock();
		writeLock.lock();
		try {
				Writer fileWrite = new FileWriter(catalogFile);
				new XMLOutputter().output(document, fileWrite);
				long lastModNew = catalogFile.lastModified();
				ProductForm.setLastMod(lastModNew);
		} finally {
			writeLock.unlock();
		}
		return mapping.findForward(PRODUCTS);
	}
	
	

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductForm productForm = (ProductForm) form;
		int catIndex = productForm.getCatIndex();
		int subcatIndex = productForm.getSubcatIndex();

		Document doc = productForm.getDoc();

		String catName = indexToCatName(doc, catIndex);
		String subcatName = indexToSubcatname(doc, catIndex, subcatIndex);

		PrintWriter resultWriter = response.getWriter();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put(CAT_NAME, catName);
		paramsMap.put(SUBCAT_NAME, subcatName);

		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		readLock.lock();
		try {
			XSLManager.makeTransform(MARKET_XML, ADD_PRODUCT_XSL, resultWriter, paramsMap);
		} finally {
			readLock.unlock();
		}
		return null;
	}
	
	public ActionForward saveProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
	
		Writer buffer = new StringWriter();
		
		Map<String, String[]> requestParamMap = request.getParameterMap();
		Map<String, Object> paramsMap = fillParamsMap(requestParamMap);
		Validator validator = new Validator();
		paramsMap.put(VALIDATOR, validator);
		
		File marketFile = new File(MARKET_REAL_PATH);
		long lastMod = marketFile.lastModified();

		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		readLock.lock();
		try {
			XSLManager.makeTransform(MARKET_XML, SAVE_PRODUCT_XSL, buffer, paramsMap);
		} catch (TechnicalException e) {
			LOGGER.error("Can not create transformer", e);
		} finally {
			readLock.unlock();
		}

		if (validator.getErrors().isEmpty()) {
			Lock writeLock = RWLockSingleton.INSTANCE.writeLock();
			writeLock.lock();
			try {
				if (lastMod != marketFile.lastModified()) {
					XSLManager.makeTransform(MARKET_XML, SAVE_PRODUCT_XSL, buffer,
							paramsMap);
				}
				Writer fileWriter = new PrintWriter(marketFile, "UTF-8");
				fileWriter.write(buffer.toString());
				fileWriter.flush();
				fileWriter.close();

			} catch (TechnicalException e) {
				LOGGER.error("Can not create transformer", e);
			}  finally {
				writeLock.unlock();
			}
			setDocument2Form(form);
			return mapping.findForward(PRODUCTS);

		} else {
			Writer out = response.getWriter();
			out.write(buffer.toString());
		}
		return null;
	}
	
	private String indexToCatName(Document document, int catIndex) {
		Element rootElement = document.getRootElement();
		Attribute catNameAttr = rootElement.getChildren().get(catIndex).getAttribute(NAME);
		return catNameAttr.getValue();
	}

	private String indexToSubcatname(Document document, int catIndex,
			int subcatIndex) {
		Element rootElement = document.getRootElement();
		Attribute subcatNameAtr = rootElement.getChildren().get(catIndex)
				.getChildren().get(subcatIndex).getAttribute(NAME);
		return subcatNameAtr.getValue();
	}

	private void setDocument2Form(ActionForm form) throws JDOMException,
			IOException {
		SAXBuilder saxBuilder = new SAXBuilder();
		File catalogFile = new File(MARKET_REAL_PATH);
		Lock readLock = RWLockSingleton.INSTANCE.readLock();
		Document document = null;
		long lastMod;
		readLock.lock();
		try {
			lastMod = catalogFile.lastModified();
			document = saxBuilder.build(catalogFile);
		} finally {
			readLock.unlock();
		}
		ProductForm productsForm = (ProductForm) form;
		productsForm.setDoc(document);
		productsForm.setLastMod(lastMod);
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
	
	private final static class RWLockSingleton {
		final static ReadWriteLock INSTANCE = new ReentrantReadWriteLock();
	}

	
}
