package by.mavs.railwayservice.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import by.mavs.railwayservice.dao.ReservedTicketDao;
import by.mavs.railwayservice.dao.TicketDao;
import by.mavs.railwayservice.dao.TrainDao;
import by.mavs.railwayservice.dao.TrainDateDao;
import by.mavs.railwayservice.dao.WagonDao;
import by.mavs.railwayservice.entity.ReservedTicket;
import by.mavs.railwayservice.entity.Ticket;
import by.mavs.railwayservice.entity.Train;
import by.mavs.railwayservice.entity.TrainDate;
import by.mavs.railwayservice.entity.TrainWrapper;
import by.mavs.railwayservice.entity.Wagon;
import by.mavs.railwayservice.exception.DAOTechnicalException;

public class OrderBean {
	private static final Logger LOG = Logger.getLogger(OrderBean.class);
	private TrainDao tDao = new TrainDao();
	private TicketDao ttDao = new TicketDao();
	private TrainDateDao tdDao = new TrainDateDao();
	private ReservedTicketDao rtDao = new ReservedTicketDao();
	private WagonDao wDao = new WagonDao();

	public List<Train> getTrainList(String depStation, String arStation,
			Date date) {
		List<Train> trainList = new ArrayList<Train>();

		try {
			trainList = tDao.findTrainsByOrder(depStation.toUpperCase(),
					arStation.toUpperCase(), date);
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		}

		return trainList;
	}

	public List<TrainWrapper> getTrainWrapperList(String depStation, String arStation, Date date) {
		System.out.println();
		List<TrainWrapper> trainWrapperList = new ArrayList<TrainWrapper>();
		List<TrainDate> trainDateList = new ArrayList<>();
		List<ReservedTicket> reservedTickets = new ArrayList<>();
		try {
			trainDateList = tdDao.findTrainDatesByOrder(
					depStation.toUpperCase(), arStation.toUpperCase(), date);
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		}
		TrainWrapper trainWrapper = null;
		for (TrainDate td : trainDateList) {
			reservedTickets = rtDao.findFilledPlace(td.getId());
			trainWrapper = new TrainWrapper(td, date, depStation, arStation,
					reservedTickets);
			trainWrapperList.add(trainWrapper);
		}

		return trainWrapperList;
	}

	public List<Wagon> getWagonList(int trainId) {
		List<Wagon> wagonList = new ArrayList<Wagon>();
		Wagon w = new Wagon();
		w.setTrain(new Train(trainId));
		try {
			wagonList = wDao.findByExample(w);
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		}
		return wagonList;
	}
	
	public void saveTicket(Ticket ticket){
		try {
			ttDao.save(ticket);
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		}
	}

	public void saveReservedTicket(ReservedTicket rt) {
		try {
			rtDao.save(rt);
		} catch (DAOTechnicalException e) {
			LOG.debug(e.getMessage());
		}
	}

}
