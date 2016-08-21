//package by.mavs.railwayservice.bean;
//
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.log4j.Logger;
//
//public class TicketBean {
//	private static final Logger LOG = Logger.getLogger(TicketBean.class);
//	private List<Ticket> ticketList = new ArrayList<Ticket>();
//
//	public List<Ticket> getTicketList() {
//		return ticketList;
//	}
//
//	public void setTicketList(List<Ticket> ticketList) {
//		this.ticketList = ticketList;
//	}
//
//	public List<Ticket> getTicketList(HttpServletRequest request) {
//
//		
////		LOG.debug("type of command = " + type);
////		if (type != null) {
////			if (type.equals("select")) {
////				String select = request.getParameter("select");
//				TicketDao tDao = new TicketDao();
//				List<Ticket> ticketList = new ArrayList<Ticket>();
//
////				if (select.equals("columnName")) {
//
//				ticketList = tDao.findByColumnName(request);
//
//					if (ticketList != null) {
//						return ticketList;
//					} else {
//						request.setAttribute(
//								"errorMessage",
//								MessageManager
//										.getInstance()
//										.getProperty(
//												MessageManager.EMPTY_SEARCH_ERROR_MESSAGES));
//						return null;
//					}
//				}
////				else if (select.equals("all")) {
////					productList = pDao.selectAll();
////
////					if (productList != null) {
////
////						return productList;
////					} else {
////						request.setAttribute(
////								"errorMessage",
////								MessageManager
////										.getInstance()
////										.getProperty(
////												MessageManager.EMPTY_SEARCH_ERROR_MESSAGES));
////						return null;
////					}
////				} 
//				
//				else if (select.equals("discount")) {
//
//					productList = pDao.findDiscountProduct(request);
//
//					if (productList != null) {
//						return productList;
//					} else {
//						request.setAttribute(
//								"errorMessage",
//								MessageManager
//										.getInstance()
//										.getProperty(
//												MessageManager.EMPTY_SEARCH_ERROR_MESSAGES));
//						return null;
//					}
//				} else if (select.equals("type")) {
//
//					productList = pDao.findByType(request);
//
//					if (productList != null && !productList.isEmpty()) {
//
//						LOG.debug("list is not null");
//						return productList;
//
//					} else {
//						request.setAttribute(
//								"errorMessage",
//								MessageManager
//										.getInstance()
//										.getProperty(
//												MessageManager.EMPTY_SEARCH_ERROR_MESSAGES));
//						return null;
//
//					}
//				}
//
//			}
//
//			if (type.equals("sort")) {
//				
//				//����� ������� ���������� ��������� � ����������� ���������� Comparator. ������ ������� � ��� �������� � ������.
//				String sorted = request.getParameter("sort");
//				List<Product> newList = new ArrayList<Product>();
//
//				newList = (List<Product>) request.getSession().getAttribute(
//						"results");
//
//				if (sorted != null) {
//
//					if (sorted.equals("name")) {
//						Collections.sort(newList, new SortedByName());
//						return newList;
//					} else if (sorted.equals("price")) {
//						Collections.sort(newList, new SortedByPrice());
//						return newList;
//					} else if (sorted.equals("rating")) {
//						Collections.sort(newList, new SortedByRating());
//						return newList;
//					} else {
//						request.setAttribute(
//								"errorMessage",
//								MessageManager
//										.getInstance()
//										.getProperty(
//												MessageManager.EMPTY_SEARCH_ERROR_MESSAGES));
//						return null;
//					}
//				} else {
//					request.setAttribute(
//							"errorMessage",
//							MessageManager.getInstance().getProperty(
//									MessageManager.BAD_COMMAND_ERROR_MESSAGES));
//					return null;
//				}
//
//			} else {
//
//				request.setAttribute(
//						"errorMessage",
//						MessageManager.getInstance().getProperty(
//								MessageManager.BAD_COMMAND_ERROR_MESSAGES));
//				return null;
//			}
//		} else {
//			request.setAttribute("errorMessage", MessageManager.getInstance()
//					.getProperty(MessageManager.BAD_COMMAND_ERROR_MESSAGES));
//			return null;
//		}
//	}
//
//}
