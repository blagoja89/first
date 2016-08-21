package by.mavs.news.presentation.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import by.mavs.news.model.News;

public final class NewsForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6029392946703509058L;

	private int id;
	private News news = new News();
	private List<News> newsList = new ArrayList<News>();
	private int[] newsSelected;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the news
	 */
	public News getNews() {
		return news;
	}

	/**
	 * @param news
	 *            the news to set
	 */
	public void setNews(News news) {
		this.news = news;
	}

	/**
	 * @return the newsList
	 */
	public List<News> getNewsList() {
		return newsList;
	}

	/**
	 * @param newsList
	 *            the newsList to set
	 */
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	

	public int[] getNewsSelected() {
		return newsSelected;
	}

	public void setNewsSelected(int[] newsSelected) {
		this.newsSelected = newsSelected;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.news.setTitle("");
		this.news.setDate(null);
		this.news.setBrief("");
		this.news.setContent("");
	}

}
