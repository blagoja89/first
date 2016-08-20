package by.julia.bankservice.comand;

import by.julia.bankservice.dao.ClientAdditionalInformationDao;
import by.julia.bankservice.dao.ClientInformationDao;
import by.julia.bankservice.dao.PassportDataDao;
import by.julia.bankservice.dao.UserDao;
import by.julia.bankservice.entity.ClientAdditionalInformation;
import by.julia.bankservice.entity.ClientInformation;
import by.julia.bankservice.entity.PassportData;
import by.julia.bankservice.entity.User;
import by.julia.bankservice.exception.DAOTechnicalException;
import by.julia.bankservice.util.ConfigurationManager;
import by.julia.bankservice.util.DateUtil;
import by.julia.bankservice.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class ChangeProfileDataCommand implements ActionCommand {

	private static final Logger LOG = Logger
			.getLogger(ChangeProfileDataCommand.class);
	private static final String EMAIL = "email";
	private static final String USER = "user";
	private static final String PASSWORD = "password";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String MESSAGE = "message";

	private UserDao userDao = new UserDao();
	private ClientAdditionalInformationDao additionalInformationDao = new ClientAdditionalInformationDao();
	private ClientInformationDao informationDao = new ClientInformationDao();
	private PassportDataDao passportDataDao = new PassportDataDao();

	@Override
	public String execute(HttpServletRequest request) {
		try {
			User oldUser = (User) request.getSession().getAttribute(USER);

			PassportData passportData = getPassportData(request);
			passportData.setId(oldUser.getUserInf().getPassportData().getId());

			ClientAdditionalInformation additionalInformation = getaAditionalInformation(request);
			additionalInformation.setId(oldUser.getUserInf().getClientAdditionalInformation().getId());

			ClientInformation clientInformation = new ClientInformation();
			clientInformation.setPassportData(passportData);
			clientInformation.setClientAdditionalInformation(additionalInformation);
			clientInformation.setBirthday(DateUtil.parseToDate(request.getParameter("birthday")));
			clientInformation.setId(oldUser.getUserInf().getId());

			User newUser = getUser(request, oldUser);
			newUser.setUserInf(clientInformation);

			passportDataDao.update(passportData);
			additionalInformationDao.update(additionalInformation);
			informationDao.update(clientInformation);
			userDao.update(newUser);

			LOG.debug(newUser.toString());
			request.getSession().setAttribute(USER, newUser);
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		request.setAttribute(
				MESSAGE,
				MessageManager.getInstance().getProperty(
						MessageManager.CHANGE_PROFILE_DATA_WAS_SUCCESS));

		String page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.PROFILE_PAGE_PATH);
		return page;
	}

	private PassportData getPassportData(HttpServletRequest request) throws ParseException {
		PassportData passportData = new PassportData();
		passportData.setPlaceOfIssue(request.getParameter("place_of_issue"));
		passportData.setDateOfIssue(DateUtil.parseToDate(request.getParameter("date_of_issue")));
		passportData.setIdentificationNo(request.getParameter("identification_no"));
		passportData.setNo(request.getParameter("passno"));
		passportData.setSeries(request.getParameter("series"));
		return passportData;
	}

	private ClientAdditionalInformation getaAditionalInformation(HttpServletRequest request){
		ClientAdditionalInformation additionalInformation = new ClientAdditionalInformation();
		additionalInformation.setReservist(Boolean.getBoolean(request.getParameter("reservist")));
		additionalInformation.setPlaceOfBirth(request.getParameter("place_of_birth"));
		additionalInformation.setAddress(request.getParameter("address"));
		additionalInformation.setCity(request.getParameter("city"));
		additionalInformation.setNationality(request.getParameter("nationality"));
		additionalInformation.setDisability(request.getParameter("disability"));
		additionalInformation.setEmail(request.getParameter("email"));
		additionalInformation.setFamilyStatus(request.getParameter("familystatus"));
		additionalInformation.setHomePhone(request.getParameter("home_phone"));
		additionalInformation.setPosition(request.getParameter("position"));
		additionalInformation.setPensioner(Boolean.getBoolean(request.getParameter("pensioner")));
		additionalInformation.setMobilePhone(request.getParameter("mobile_phone"));
		additionalInformation.setJob(request.getParameter("job"));
		additionalInformation.setIncome(request.getParameter("income"));
		return additionalInformation;
	}

	private User getUser(HttpServletRequest request, User oldUser) throws ParseException {
		User user = new User();
		user.setEmail(request.getParameter(EMAIL));
		user.setFirstName(request.getParameter("first_name"));
		user.setLastName(request.getParameter("last_name"));
		user.setLogin(request.getParameter("login"));
		user.setId(oldUser.getId());
		user.setRole(oldUser.getRole());
		user.setPassword(oldUser.getPassword());
		user.setAccount(oldUser.getAccount());
		return user;
	}

}
