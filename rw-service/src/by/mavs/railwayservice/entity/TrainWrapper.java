package by.mavs.railwayservice.entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.mavs.railwayservice.util.CalculationTicketPrice;
import by.mavs.railwayservice.util.DateUtil;

public class TrainWrapper {

	private int trainId;
	private int trainDateId;
	private String trainName;
	private String stationArrival;
	private String stationDeparture;
	private String timeDeparture;
	private String timeArrival;
	private String timeTrevel;
	private Integer distance = 0;
	private String dateDeparture;
	private long ticketPrice;
	private int sizePlaceGeneral;
	private int sizePlacePlac;
	private int sizePlaceCupe;
	private int sizePlaceSv;
	private String pricePlaceGeneral;
	private String pricePlacePlac;
	private String pricePlaceCupe;
	private String pricePlaceSv;
	private Map<Integer, WagonWrapper> wagonWrappers = new HashMap<>();

	public TrainWrapper(TrainDate trainDate, Date departureDate,
			String depStation, String arStation,
			List<ReservedTicket> listTickets) {
		System.out.println();
		trainId = trainDate.getTrain().getId();
		trainDateId = trainDate.getId();
		initTrainName(trainDate);
		initDateDepAndArrival(trainDate, depStation, arStation);
		initDistanceAndTimeBetweenStation(trainDate, depStation, arStation);
		initSizePlaceAndPrice(trainDate, listTickets);
	}

	public int getTrainDateId() {
		return trainDateId;
	}

	public int getTrainId() {
		return trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public String getStationArrival() {
		return stationArrival;
	}

	public String getStationDeparture() {
		return stationDeparture;
	}

	public String getTimeDeparture() {
		return timeDeparture;
	}

	public String getTimeArrival() {
		return timeArrival;
	}

	public String getDateDeparture() {
		return dateDeparture;
	}

	public String getPricePlaceGeneral() {
		return pricePlaceGeneral;
	}

	public String getPricePlacePlac() {
		return pricePlacePlac;
	}

	public String getPricePlaceCupe() {
		return pricePlaceCupe;
	}

	public String getPricePlaceSv() {
		return pricePlaceSv;
	}

	public String getTimeTrevel() {
		return timeTrevel;
	}

	public int getSizePlaceGeneral() {
		return sizePlaceGeneral;
	}

	public int getSizePlacePlac() {
		return sizePlacePlac;
	}

	public int getSizePlaceCupe() {
		return sizePlaceCupe;
	}

	public int getSizePlaceSv() {
		return sizePlaceSv;
	}

	public List<WagonWrapper> getWagonWrappers() {
		return new ArrayList<>(wagonWrappers.values());
	}

	private void initTrainName(TrainDate trainDate) {
		String name = " ";
		if (trainDate.getTrain().getTrainMapStations() != null
				&& !trainDate.getTrain().getTrainMapStations().isEmpty()) {
			int size = trainDate.getTrain().getTrainMapStations().size();
			String departureName = "";
			String arrivalName = "";
			for (TrainMapStation tms : trainDate.getTrain()
					.getTrainMapStations()) {
				if (tms.getSeqNo() == 1)
					departureName = tms.getMapStation().getStationDeparture()
							.getName();
				if (tms.getSeqNo() == size)
					arrivalName = tms.getMapStation().getStationArrival()
							.getName();
			}
			name = departureName + " - " + arrivalName;
		}
		trainName = trainDate.getTrain().getNumber() + " " + name;
	}

	private void initDateDepAndArrival(TrainDate trainDate, String depStat,
			String arStat) {
		for (TrainMapStation tms : trainDate.getTrain().getTrainMapStations()) {
			if (tms.getMapStation().getStationDeparture().getName()
					.equalsIgnoreCase(depStat))
				stationDeparture = tms.getMapStation().getStationDeparture()
						.getName();
			if (tms.getMapStation().getStationArrival().getName()
					.equalsIgnoreCase(arStat))
				stationArrival = tms.getMapStation().getStationArrival()
						.getName();
		}
	}

	private void initDistanceAndTimeBetweenStation(TrainDate trainDate,
			String depStat, String arStat) {
		boolean flag = false;
		int dist = 0;
		System.out.println();
		List<Date> aDate = new ArrayList<>();
		for (TrainMapStation tms : trainDate.getTrain().getTrainMapStations()) {
			if (tms.getMapStation().getStationDeparture().getName()
					.equalsIgnoreCase(depStat))
				flag = true;

			if (flag) {
				dist = tms.getMapStation().getDistance();
				distance += dist;
				aDate.add(tms.getMapStation().getTime());
			}

			if (tms.getMapStation().getStationArrival().getName()
					.equalsIgnoreCase(arStat)) {
				flag = false;
				break;
			}
		}
		Time time = new Time(654645l);
		System.out.println();
		Date dateDep = DateUtil.concatDate(trainDate.getDepartureDate(),
				trainDate.getDepartureTime());
		Date dateTravel = DateUtil.concatDate(aDate);
		Date dateArr = DateUtil.concatDate(dateDep, aDate);
		timeDeparture = DateUtil.getDateWithTime(dateDep);
		timeTrevel = DateUtil.getTime(dateTravel);
		timeArrival = DateUtil.getDateWithTime(dateArr);
		dateDeparture = DateUtil.getDateWithTime(dateDep);
		ticketPrice = CalculationTicketPrice.basicPrice(distance,
				CalculationTicketPrice.PRICE_KM);
	}

	private void initSizePlaceAndPrice(TrainDate trainDate,
			List<ReservedTicket> listTickets) {
		pricePlaceGeneral = String.valueOf(ticketPrice
				* WagonType.GENERAL.getCoeff());
		pricePlacePlac = String.valueOf(ticketPrice
				* WagonType.PLACCARD.getCoeff());
		pricePlaceCupe = String.valueOf(ticketPrice
				* WagonType.CUPE.getCoeff());
		pricePlaceSv = String
				.valueOf(ticketPrice * WagonType.SV.getCoeff());
		
		
		if (trainDate.getTrain().getWagons() != null) {
			for (Wagon wagon : trainDate.getTrain().getWagons()) {
				WagonType wagonType = WagonType.getWagonTypeById(wagon
						.getType());
				String price = "";
				if (wagonType != null)
					switch (wagonType) {
					case GENERAL:
						sizePlaceGeneral += WagonType.GENERAL.getNumberPlace();
						price = pricePlaceGeneral;
						break;
					case PLACCARD:
						sizePlacePlac += WagonType.PLACCARD.getNumberPlace();
						price = pricePlacePlac;
						break;
					case CUPE:
						sizePlaceCupe += WagonType.CUPE.getNumberPlace();
						price = pricePlaceCupe;
						break;
					case SV:
						sizePlaceSv += WagonType.SV.getNumberPlace();
						price = pricePlaceSv;
						break;

					default:
						break;
					}
				wagonWrappers.put(wagon.getId(), new WagonWrapper(wagon,
						getListPlace(wagonType.getNumberPlace()), price));
			}
			System.out.println();
			if (listTickets != null && !listTickets.isEmpty()) {
				for (ReservedTicket rt : listTickets) {
					WagonType wagonType = WagonType.getWagonTypeById(rt
							.getTicket().getWagon().getType());
					if (wagonType != null)
						switch (wagonType) {
						case GENERAL:
							sizePlaceGeneral--;
							break;
						case PLACCARD:
							sizePlacePlac--;
							break;
						case CUPE:
							sizePlaceCupe--;
							break;
						case SV:
							sizePlaceSv--;
							break;

						default:
							break;
						}
					WagonWrapper wrapper = wagonWrappers.get(rt.getTicket().getWagon()
							.getId());
					wrapper.getFreePlace().remove(new Integer(rt.getTicket().getPlace()));
				}
			}
		}

	}

	private List<Integer> getListPlace(int len) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= len; i++) {
			list.add(i);
		}
		return list;
	}
	
	public WagonWrapper getWagonWrapperById(int id){
		return wagonWrappers.get(id);
	}

}
