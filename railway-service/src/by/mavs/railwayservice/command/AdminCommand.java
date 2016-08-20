package by.mavs.railwayservice.command;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import by.mavs.railwayservice.bean.MessageBean;
import by.mavs.railwayservice.bean.UserBean;
import by.mavs.railwayservice.dao.TrainDao;
import by.mavs.railwayservice.dao.TrainDateDao;
import by.mavs.railwayservice.dao.UserDao;
import by.mavs.railwayservice.entity.Message;
import by.mavs.railwayservice.entity.Train;
import by.mavs.railwayservice.entity.TrainDate;
import by.mavs.railwayservice.entity.User;
import by.mavs.railwayservice.exception.DAOTechnicalException;
import by.mavs.railwayservice.util.ConfigurationManager;
import by.mavs.railwayservice.util.DateUtil;
import by.mavs.railwayservice.util.MessageManager;
import by.mavs.railwayservice.util.MessageManagerUtil;

public class AdminCommand implements ActionCommand {

	private static final String SELECTED_CURRENT_TRAIN_ATRIBUTE = "trainList";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE = "message";
	private static final String TYPE = "type";
	private static final String SELECT = "select";
	private static final String USER_LIST = "userList";
	private static final String CHANGE = "change";
	private static final String LEVEL = "level";
	private static final String DATE = "date";
	private static final String TIME = "time";
	private static final String TRAIN_ITEM = "trainItem";
	private static final String ADMIN = "admin";
	private static final String MESSAGES = "messages";
	
	private static Logger LOG = Logger.getLogger(AdminCommand.class);
	private UserDao userDao = new UserDao();
	private User user = null;

	private String passwordCommand(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(LOGIN);
		String pass = request.getParameter(PASSWORD);

		user = userDao.findByLogin(login);
		if (user != null) {
			user.setPassword(pass);
			try {
				userDao.update(user);
			} catch (DAOTechnicalException e) {
				LOG.debug(e.getMessage());
			}
			LOG.debug("password was changed");
			request.setAttribute(MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.PASSWORD_WAS_CHANGED));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);

		} else {
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.NULL_LOGIN_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);
		}

		return page;
	}

	private String userCommand(HttpServletRequest request) {
		String page = null;
		String type = request.getParameter(TYPE);

		if (type.equals(SELECT)) {

			UserBean userBean = new UserBean();
			List<User> userList = new ArrayList<>();
				userList = userBean.getUserList();

			request.setAttribute(USER_LIST, userList);
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_USER_PAGE_PATH);
			return page;
			// *****
		} else if (type.equals(CHANGE)) {
			String login = request.getParameter(LOGIN);
			String role = request.getParameter(LEVEL);
			User userChange = userDao.findByLogin(login);
			userChange.setRole(Integer.parseInt(role));
			try {
				userDao.update(userChange);
			} catch (DAOTechnicalException e) {
				LOG.debug(e.getMessage());
			}

			request.setAttribute(MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.LEVEL_WAS_CHANGED));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);
			return page;
		} else {
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.BAD_COMMAND_ERROR_MESSAGES));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);
			return page;
		}
	}

	private String messageCommand(HttpServletRequest request) {
		String page = null;
		MessageBean mesBean = new MessageBean();
		List<Message> messageList = new ArrayList<Message>();
			messageList = mesBean.getMessageList();

		request.setAttribute(MESSAGES, messageList);
		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.ADMIN_MESSAGE_PAGE_PATH);
		return page;
	}

	private String trainCommand(HttpServletRequest request) {

		String page = null;
		String date = request.getParameter(DATE);
		String time = request.getParameter(TIME);
		String trainId = request.getParameter(TRAIN_ITEM);

		Date dateDep = null;
		try {
			dateDep = DateUtil.parseToDate(date);
		} catch (ParseException e) {
			LOG.error("not parse");
			request.setAttribute(ERROR_MESSAGE, MessageManagerUtil
					.getProperty(MessageManager.ENTER_DATE_CORRECT_FORMAT));
			return page;
		}

		TrainDate trainDate = new TrainDate();
		trainDate.setId(Math.abs(UUID.randomUUID().hashCode()));
		trainDate.setDepartureDate(dateDep);
		trainDate.setDepartureTime(DateUtil.parseToTime(time));
		Train train = new Train();
		train.setId(Integer.parseInt(trainId));
		trainDate.setTrain(train);

		TrainDateDao trainDateDao = new TrainDateDao();
		try {
			trainDateDao.save(trainDate);
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		}

		if (trainDate != null) {

			request.setAttribute(MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.TRAIN_ADD));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);
		}

		return page;
	}

	private String initTrainCommand(HttpServletRequest request) {
		TrainDao trainDao = new TrainDao();
		List<Train> trainList = null;
		try {
			trainList = trainDao.findAll();
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		}

		request.getSession().setAttribute(SELECTED_CURRENT_TRAIN_ATRIBUTE, trainList);
		String page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.ADMIN_PAGE_PATH);

		return page;
	}

	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
		String command = request.getParameter(ADMIN);
		AdminCommandEnum commandEnum = AdminCommandEnum.valueOf(command
				.toUpperCase());

		switch (commandEnum) {
		case PASSWORD:
			page = passwordCommand(request);
			break;
		case USER:
			page = userCommand(request);
			break;
		case MESSAGE:
			page = messageCommand(request);
			break;
		case TRAIN:
			page = trainCommand(request);
			break;
		case INIT:
			page = initTrainCommand(request);
			break;
		default:
			request.setAttribute(ERROR_MESSAGE, MessageManager.getInstance()
					.getProperty(MessageManager.BAD_COMMAND_ERROR_MESSAGES));
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.ADMIN_PAGE_PATH);
		}
		return page;

	}
}
