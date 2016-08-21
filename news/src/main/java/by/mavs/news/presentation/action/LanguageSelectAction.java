package by.mavs.news.presentation.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class LanguageSelectAction extends Action {
	private final static String LOCALE = "locale";
	private final static String LOC = "loc";
	private final static String REFERER = "referer";
	
	
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.getSession().setAttribute( Globals.LOCALE_KEY, new Locale(request.getParameter(LOCALE)));
        request.getSession().setAttribute(LOC , new Locale(request.getParameter(LOCALE)));
        String referer = request.getHeader(REFERER);
        return new ActionForward(referer, true);
    }
}

