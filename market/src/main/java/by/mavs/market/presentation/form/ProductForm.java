package by.mavs.market.presentation.form;

import org.apache.struts.action.ActionForm;
import org.jdom2.Document;

public final class ProductForm extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4199508027986990036L;
	private Document doc;
	private int catIndex;
	private int subcatIndex;
	private long lastMod;
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	public int getCatIndex() {
		return catIndex;
	}
	public void setCatIndex(int catIndex) {
		this.catIndex = catIndex;
	}
	public int getSubcatIndex() {
		return subcatIndex;
	}
	public void setSubcatIndex(int subcatIndex) {
		this.subcatIndex = subcatIndex;
	}
	public long getLastMod() {
		return lastMod;
	}
	public void setLastMod(long lastMod2) {
		this.lastMod = lastMod2;
	}


	
}
