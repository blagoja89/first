package by.mavs.news.exception;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;


public final class MyExceptionHandler extends ExceptionHandler {
	private static final Logger logger = Logger
			.getLogger(MyExceptionHandler.class);
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE = "Enter correct data!";

	@Override
	public ActionForward execute(Exception ex, ExceptionConfig ae,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

//		if(ex.getClass().equals(new ServletException().getClass())){
//			request.setAttribute(ERROR_MESSAGE, MESSAGE);
//				return mapping.findForward("successEr");
//		}
		
		logger.log(Level.ERROR, ex);
		ex.printStackTrace();
		return super.execute(ex, ae, mapping, formInstance, request, response);
	}
}
