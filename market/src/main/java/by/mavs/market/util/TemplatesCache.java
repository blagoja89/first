package by.mavs.market.util;

import java.util.concurrent.ConcurrentHashMap;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

final class TemplatesCache {

	private static ConcurrentHashMap<String, Templates> templateCache = new ConcurrentHashMap<String, Templates>();
	private static TransformerFactory transformerFactory = TransformerFactory.newInstance();

	static Transformer getTransformer(String xslPath) throws TransformerConfigurationException {
		Source source = new StreamSource(TemplatesCache.class.getResourceAsStream(xslPath));

		templateCache.putIfAbsent(xslPath, transformerFactory.newTemplates(source));
		
		 if (!templateCache.containsKey(xslPath)) {
			   templateCache.put(xslPath, transformerFactory.newTemplates(source));
		   } 
		return templateCache.get(xslPath).newTransformer();

	}
}
