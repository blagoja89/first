package by.mavs.railwayservice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

public class ChangeLanguageCommand implements ActionCommand {

	private String local;

	private static final Logger LOG = Logger
			.getLogger(ChangeLanguageCommand.class);

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		local = (String) request.getParameter("language");
		if (local != null) {
			if (local.compareTo("RU") == 0) {
				Config.set(request.getSession(), Config.FMT_LOCALE,
						new java.util.Locale("ru"));
			}

			else if (local.compareTo("EN") == 0) {
				Config.set(request.getSession(), Config.FMT_LOCALE,
						new java.util.Locale("en"));
			}
		}
		page = request.getParameter("pageName");
		LOG.debug("local =  " + local + " page = " + page);

		request.getSession().setAttribute("language", local);
		return page.substring(page.lastIndexOf("/jsp/"));

	}
}
