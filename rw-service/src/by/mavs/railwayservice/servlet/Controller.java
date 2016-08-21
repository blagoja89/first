package by.mavs.railwayservice.servlet;




import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.mavs.railwayservice.command.ActionCommand;
import by.mavs.railwayservice.command.ActionFactory;
import by.mavs.railwayservice.util.ConfigurationManager;
import by.mavs.railwayservice.util.ConnectLog4jWithWeb;
import by.mavs.railwayservice.util.MessageManagerUtil;
import by.mavs.railwayservice.util.PropertyConst;



/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		ConnectLog4jWithWeb.connectLog4j(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String page = null;
		ActionFactory action = new ActionFactory();
		ActionCommand command = action.defineCommand(request);
		page = command.execute(request);
		if (page != null) {
			request.getRequestDispatcher(page).forward(request, response);
		} else {
			page = ConfigurationManager.getInstance()
					.getProperty(PropertyConst.PAGE_INDEX);
			request.getSession().setAttribute(
					PropertyConst.ATTRIBUTE_NULLPAGE,
					MessageManagerUtil
							.getProperty(PropertyConst.MESSAGE_NULLPAGE));
			response.sendRedirect(request.getContextPath() + page);
		}

	}

}
