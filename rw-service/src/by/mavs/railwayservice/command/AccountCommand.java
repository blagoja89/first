package by.mavs.railwayservice.command;

import javax.servlet.http.HttpServletRequest;

import by.mavs.railwayservice.dao.AccountDao;
import by.mavs.railwayservice.entity.Account;
import by.mavs.railwayservice.entity.User;
import by.mavs.railwayservice.exception.DAOTechnicalException;
import by.mavs.railwayservice.util.ConfigurationManager;
import by.mavs.railwayservice.util.MessageManager;
import by.mavs.railwayservice.util.MessageManagerUtil;

public class AccountCommand implements ActionCommand {
	private static final String SELECTED_USER_ATRIBUTE = "user";
	private static final String CASH_ATRIBUTE = "userBill";

	private AccountDao ad = new AccountDao();

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		System.out.println();
		page = ConfigurationManager.getInstance().getProperty(
				ConfigurationManager.ACCOUNT_PAGE);

		String cash = (String) request.getParameter(CASH_ATRIBUTE);
		Double d = 0d;
		try {
			d = new Double(cash);
		} catch (Exception e) {
			// ddjlbnt njkmrj wbahs
			request.setAttribute("errorMessage", MessageManagerUtil
					.getProperty(MessageManager.EMPTY_SEARCH_ERROR_MESSAGES));
			return page;
		}
		User user = (User) request.getSession().getAttribute(
				SELECTED_USER_ATRIBUTE);
		Account account = user.getAccount();
		account.setBill(account.getBill() + d);
		try {
			ad.update(account);
		} catch (DAOTechnicalException e) {
			e.printStackTrace();
		}
		// деньгі успешно добавлены
		request.setAttribute("message", MessageManagerUtil
				.getProperty(MessageManager.ENOUGH_MONEY_ERROR_MESSAGES));
		return page;
	}

}
