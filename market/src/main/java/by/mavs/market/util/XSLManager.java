package by.mavs.market.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import by.mavs.market.exceptions.TechnicalException;

public final class XSLManager {

	
	@SafeVarargs
	public static void makeTransform(String nameXML, String stylesheet,
			Writer resultWriter, Map<String, Object>... transParams) throws TechnicalException {
		InputStream inputData = XSLManager.class.getResourceAsStream(nameXML);
		Transformer t = null;
		try {
			t = TemplatesCache.getTransformer(stylesheet);
		} catch (TransformerConfigurationException e) {
			throw new TechnicalException();
		}
		Source text = new StreamSource(inputData);
		StreamResult streamResult = new StreamResult(resultWriter);
		if (transParams.length != 0) {
			for (String key : transParams[0].keySet()) {
				t.setParameter(key, transParams[0].get(key));
			}
		}
		try {
			t.transform(text, streamResult);
		} catch (TransformerException e) {
			throw new TechnicalException();
		}
	}

}
