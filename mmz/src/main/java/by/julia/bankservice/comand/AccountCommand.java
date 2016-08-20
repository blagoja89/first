package by.julia.bankservice.comand;

import by.julia.bankservice.dao.AccountDao;
import by.julia.bankservice.entity.Account;
import by.julia.bankservice.entity.User;
import by.julia.bankservice.exception.DAOTechnicalException;
import by.julia.bankservice.util.ConfigurationManager;
import by.julia.bankservice.util.MessageManager;
import by.julia.bankservice.util.MessageManagerUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AccountCommand implements ActionCommand {
	private static Logger LOG = Logger.getLogger(AccountCommand.class);
	private static final String SELECTED_USER_ATRIBUTE = "user";
	private static final String CASH_ATRIBUTE = "userBill";
	private static final String MESSAGE = "message";
	private static final String ERROR_MESSAGE = "errorMessage";

	private AccountDao ad = new AccountDao();

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.ACCOUNT_PAGE);

		String cash = (String) request.getParameter(CASH_ATRIBUTE);
		Double d = 0d;
		try {
			d = new Double(cash);
		} catch (NumberFormatException e) {
			request.setAttribute(ERROR_MESSAGE, MessageManagerUtil
					.getProperty(MessageManager.ENTER_ONLY_NUMBERS));
			return page;
		}
		User user = (User) request.getSession().getAttribute(
				SELECTED_USER_ATRIBUTE);
		Account account = user.getAccount();
		account.setBill(account.getBill() + d);
		try {
			ad.update(account);
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		}
	//	request.setAttribute(MESSAGE, MessageManagerUtil
	//			.getProperty(MessageManager.ENOUGH_MONEY_ERROR_MESSAGES));
		return page;
	}

}
