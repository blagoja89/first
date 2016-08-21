package by.mavs.news.presentation.action;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import by.mavs.news.dao.IDao;
import by.mavs.news.exception.ActionException;
import by.mavs.news.exception.DaoException;
import by.mavs.news.model.News;
import by.mavs.news.presentation.form.NewsForm;

public final class NewsAction extends DispatchAction {
	
	private final static String SUCCESS_LIST = "successList";
	private final static String SUCCESS_DELETE = "successDelete";
	private final static String SUCCESS_EDIT = "successEdit";
	private final static String SUCCESS_SAVE = "successSave";
	private final static String SUCCESS_VIEW = "successView";
	private final static String SUCCESS_ADD = "successAdd";
	
	private final static String EXCEPTION = "Exception in NewsAction.";
	private final static String LIST = "GetList ";
	private final static String DELETE = "Delete ";
	private final static String EDIT = "Edit ";
	private final static String SAVE = "Save ";
	private final static String VIEW = "View ";
	
	static {
		Locale.setDefault(Locale.ENGLISH);
	}

	private IDao<News> newsDao;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ActionException {
		List<News> newsList = null;
		try {
			newsList = newsDao.findAll();
		} catch (DaoException e) {
			throw new ActionException(EXCEPTION.concat(LIST), e);
		}
		NewsForm newsForm = (NewsForm) form;
		newsForm.setNewsList(newsList);
		return mapping.findForward(SUCCESS_LIST);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ActionException {
		int[] newsSelected = ((NewsForm) form).getNewsSelected();
		if (newsSelected == null) {
			return mapping.findForward(SUCCESS_LIST);
		}
		try {
			newsDao.deleteList(newsSelected);
		} catch (DaoException e) {
			throw new ActionException(EXCEPTION.concat(DELETE), e);
		}

		return mapping.findForward(SUCCESS_DELETE);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ActionException {

		NewsForm newsForm = (NewsForm) form;
		int id = newsForm.getNews().getId();
		News news = null;
		try {
			news = newsDao.findById(id);
		} catch (DaoException e) {
			throw new ActionException(EXCEPTION.concat(EDIT), e);
		}
		newsForm.setNews(news);

		return mapping.findForward(SUCCESS_EDIT);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ActionException {

		NewsForm newsForm = (NewsForm) form;
		News news = newsForm.getNews();
		int id = news.getId();
		try {
			if (id != 0) {
				news.setId(id);
				newsDao.update(news);

			} else {
//				news.setId(Math.abs(UUID.randomUUID().hashCode()));
				newsDao.save(news);
			}
		} catch (DaoException e) {
			throw new ActionException(EXCEPTION.concat(SAVE), e);
		}
		form.reset(mapping, request);
		newsForm.getNews().setId(news.getId());

		return mapping.findForward(SUCCESS_SAVE);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ActionException {

		NewsForm newsForm = (NewsForm) form;
		int id = newsForm.getNews().getId();
		News news = null;
		try {
			news = newsDao.findById(id);
		} catch (DaoException e) {
			throw new ActionException(EXCEPTION.concat(VIEW), e);
		}
		newsForm.setNews(news);
		return mapping.findForward(SUCCESS_VIEW);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		News news = new News();
		NewsForm newsForm = (NewsForm) form;
		newsForm.setNews(news);
		return mapping.findForward(SUCCESS_ADD);
	}

	public IDao<News> getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(IDao<News> newsDao) {
		this.newsDao = newsDao;
	}

	
}
