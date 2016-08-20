package by.mavs.railwayservice.command;


import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import by.mavs.railwayservice.entity.User;
import by.mavs.railwayservice.util.ConfigurationManager;





public class ChangeProfileDataCommand implements ActionCommand{
	
		
	private static final Logger LOG = Logger
			.getLogger(ChangeProfileDataCommand.class);
	
	@Override
	public String execute(HttpServletRequest request){
		
		String page;
		
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String mail = request.getParameter("email");

		User oldUser = (User) request.getSession().getAttribute(
				"user");
		
		User newUser = new User();
		newUser.setLogin(oldUser.getLogin());
		newUser.setRole(oldUser.getRole());
		newUser.setFirstName(oldUser.getFirstName());
		newUser.setLastName(oldUser.getLastName());
		newUser.setPassword(oldUser.getPassword());
		
		
//		if(address != null && address.trim().length() != 0){
//			newUser.setEmail(address);
//		}
//		else {
//			newUser.setAddress(oldUser.getAddress());
//		}
//		if(mail != null && mail.trim().length() != 0){
//			newUser.setMail(mail);
//		}
//		else {
//			newUser.setMail(oldUser.getMail());
//		}
//		if(phone != null && phone.trim().length() != 0) {
//			newUser.setPhone(phone);
//		}
//		else {
//			newUser.setPhone(oldUser.getPhone());
//		}
//		if (password == null || password.trim().length() == 0) {
//			newUser.setPassword(oldUser.getPassword());
//			new UserDao<User>().update(oldUser, newUser);
//						
//		} else {
//			if (password.trim().length() < 8){
//				
//				request.setAttribute("errorMessage", MessageManager.getInstance()
//						.getProperty(MessageManager.PASSWORD_TOO_SMALL_ERROR_MESSAGE));
//				page = ConfigurationManager.getInstance().getProperty(
//						ConfigurationManager.PROFILE_PAGE_PATH);
//				return page;
//			}
//			else{
//			Password oldPass = oldUser.getPassword();
//			String idPass = oldPass.getId();
//			Password newPass = new Password();
//			newPass.setId(idPass);
//			newPass.setPassword(password);
//		
//			Date date = new Date(System.currentTimeMillis());
//			long d = date.getTime();
//			java.sql.Date sqlDate = new java.sql.Date(d);
//			
//			newPass.setWhenChange(sqlDate);
//			newPass.setWhoChange(oldUser.getLogin());
//			newUser.setPassword(newPass);
//			
//			new UserDao<User>().update(oldUser, newUser);
//			new PasswordDao<Password>().update(oldPass, newPass);
//			
//			}
//		}	
//		
//		LOG.debug(oldUser.toString());
//		LOG.debug(newUser.toString());
//		
//			request.setAttribute("message", MessageManager.getInstance()
//					.getProperty(MessageManager.CHANGE_PROFILE_DATA_WAS_SUCCESS));
//			
//			
			page = ConfigurationManager.getInstance().getProperty(
					ConfigurationManager.PROFILE_PAGE_PATH);
//			request.getSession().setAttribute("user", newUser);
//			
			return page;
		}
			
	}
