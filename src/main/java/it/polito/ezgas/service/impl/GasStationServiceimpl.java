package it.polito.ezgas.service.impl;

import java.lang.Math;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.time.temporal.ChronoUnit.*;
import java.util.ListIterator;

import org.springframework.stereotype.Service;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.converter.*;
import it.polito.ezgas.converter.impl.GasStationConverter;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class GasStationServiceimpl implements GasStationService{

	@Autowired
	private GasStationRepository gasRepo;

	@Autowired
	private UserRepository userRepo;

	private Converter<GasStation, GasStationDto> gasConverter = new GasStationConverter();

	// TODO: the db could return empty lists or null "pointers", so to every call the return must be checked. If it corresponds to one of the two conditions described before we should act as the method specification requires
	
	/**
	 *  Standard constructor
	 */
	public GasStationServiceimpl() {	}


	/**
	 * constructor for Mockito
	 * @param gasRepository
	 * @param userRepository
	 */
	public GasStationServiceimpl(GasStationRepository gasRepository, Converter<GasStation, GasStationDto> gc, UserRepository userRepository) {
		this.gasRepo = gasRepository;
		this.gasConverter = gc;
		this.userRepo = userRepository;
	}
	
	/**
	 * constructor for Mockito
	 * @param gasRepository
	 * @param userRepository
	 */
	public GasStationServiceimpl(GasStationRepository gasRepository, UserRepository userRepository) {
		this.gasRepo = gasRepository;
		this.userRepo = userRepository;
	}
	
	
	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		if (gasStationId == null || gasStationId < 0)
			throw new InvalidGasStationException("No gas station corresponds to Id");

		refreshDependability();
		GasStation station = gasRepo.findById(gasStationId);
		if (station == null) {
			throw new InvalidGasStationException("No gas station could be found");
		}

		return gasConverter.convertToDto(station);
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		if (gasStationDto == null)
			throw new PriceException("Invalid prices in gas station");
		if ((gasStationDto.getLat() < -90 || gasStationDto.getLat() > 90) ||
				(gasStationDto.getLon() < -180 || gasStationDto.getLon() >= 180))
			throw new GPSDataException("Invalid gas station position");

		if (gasStationDto.getDieselPrice() < -1 || (gasStationDto.getDieselPrice() > -1 && gasStationDto.getDieselPrice() < 0)) {
			throw new PriceException("Invalid Diesel price");
		} else if (gasStationDto.getDieselPrice() == -1) {
			gasStationDto.setDieselPrice(0); // Use 0 as a placeholder (no one gives free stuff)
		}

		if (gasStationDto.getGasPrice() < -1 || (gasStationDto.getGasPrice() > -1 && gasStationDto.getGasPrice() < 0)) {
			throw new PriceException("Invalid Diesel price");
		} else if (gasStationDto.getGasPrice() == -1) {
			gasStationDto.setGasPrice(0); // Use 0 as a placeholder (no one gives free stuff)
		}

		if (gasStationDto.getMethanePrice() < -1 || (gasStationDto.getMethanePrice() > -1 && gasStationDto.getMethanePrice() < 0)) {
			throw new PriceException("Invalid Diesel price");
		} else if (gasStationDto.getMethanePrice() == -1) {
			gasStationDto.setMethanePrice(0); // Use 0 as a placeholder (no one gives free stuff)
		}

		if (gasStationDto.getSuperPrice() < -1 || (gasStationDto.getSuperPrice() > -1 && gasStationDto.getSuperPrice() < 0)) {
			throw new PriceException("Invalid Diesel price");
		} else if (gasStationDto.getSuperPrice() == -1) {
			gasStationDto.setSuperPrice(0); // Use 0 as a placeholder (no one gives free stuff)
		}

		if (gasStationDto.getSuperPlusPrice() < -1 || (gasStationDto.getSuperPlusPrice() > -1 && gasStationDto.getSuperPlusPrice() < 0)) {
			throw new PriceException("Invalid Diesel price");
		} else if (gasStationDto.getSuperPlusPrice() == -1) {
			gasStationDto.setSuperPlusPrice(0); // Use 0 as a placeholder (no one gives free stuff)
		}
		
		if (gasStationDto.getCarSharing().equals("null")) 
			gasStationDto.setCarSharing(null);
		
		/*
		The internal representation of the report timestamp is different from the one used in the frontend.
		Before saving the gas station in the db in case of an update load the correct timestamp from the db.
		This is fine since the only moment in which the timestamp is updated is when a new report is done with the
		appropriate method call
		*/
		GasStation gs = gasRepo.findById(gasStationDto.getGasStationId());
		if (gs != null && gs.getReportTimestamp() != null && !gs.getReportTimestamp().isEmpty()) {
			gasStationDto.setReportTimestamp(gs.getReportTimestamp());
		}

		GasStation gasStation = gasRepo.save(gasConverter.convertFromDto(gasStationDto));
		refreshDependability();
		return gasConverter.convertToDto(gasStation);
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		refreshDependability();
		List<GasStation> gasList = gasRepo.findAll();
		if (gasList == null || gasList.isEmpty()) {
			return new ArrayList<>();
		}

		List<GasStationDto> DtoGasList= new ArrayList<>();
		for (GasStation gasStation : gasList) {
			DtoGasList.add(gasConverter.convertToDto(gasStation));
		}
		return DtoGasList;
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		if (gasStationId == null || gasStationId < 0)
			throw new InvalidGasStationException("Invalid Id");
		if (getGasStationById(gasStationId) == null)
			return false;
		gasRepo.delete(gasStationId);
		refreshDependability();
		return true;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		if (gasolinetype == null || gasolinetype.isEmpty() || gasolinetype.equals("null")) {
			throw new InvalidGasTypeException("Invalid gasoline type");
		}

		List<GasStation> gasList;

		refreshDependability();

		switch (gasolinetype) {
			case "Diesel":
				gasList = gasRepo.findByDiesel();
				break;
			case "Super":
				gasList = gasRepo.findBySuper();
				break;
			case "SuperPlus":
				gasList = gasRepo.findBySuperPlus();
				break;
			case "Gas":
				gasList = gasRepo.findByGas();
				break;
			case "Methane":
				gasList = gasRepo.findByMethane();
				break;
			default:
				throw new InvalidGasTypeException("Invalid gasoline type");
		}

		if (gasList == null || gasList.isEmpty()) {
			return new LinkedList<GasStationDto>();
		}

		List<GasStationDto> DtoGasList = new ArrayList<>();
		for (GasStation gasStation : gasList) {
			DtoGasList.add(gasConverter.convertToDto(gasStation));
		}
		return DtoGasList; 
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		if ((lat< -90 || lat > 90) || (lon< -180 || lon >= 180))
			throw new GPSDataException("Invalid Coordinates");

		refreshDependability();
		List<GasStation> gasList = gasRepo.findAll();
		if (gasList == null || gasList.isEmpty()) {
			return new LinkedList<GasStationDto>();
		}
		
		List<GasStationDto> DtoGasList= new ArrayList<>();
		for (GasStation gas : gasList) {
			if (Haversine.distance(lat, lon, gas.getLat(), gas.getLon()) < 1.0) {
				DtoGasList.add(gasConverter.convertToDto(gas));
			}
		}
		return DtoGasList; 
	}
	
	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		if ((lat < -90 || lat > 90) || (lon < -180 || lon >= 180))
			throw new GPSDataException("Invalid Coordinates");

		refreshDependability();
		List<GasStationDto> dtoGasList = getGasStationsWithoutCoordinates(gasolinetype, carsharing);
		if (dtoGasList == null || dtoGasList.isEmpty()) {
			return new ArrayList<>();
		}

		List<GasStationDto> toReturn = new ArrayList<>();
		for (GasStationDto dtoGas : dtoGasList) {
			if (Haversine.distance(lat, lon, dtoGas.getLat(), dtoGas.getLon()) < 1.0) {
				toReturn.add(dtoGas);
			}
		}

		return toReturn;
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		if (gasolinetype == null || carsharing == null || gasolinetype.isEmpty() || carsharing.isEmpty()) {
			throw new InvalidGasTypeException("Invalid values"); // TODO: maybe it would be nice to have another exception? (we should open an issue then)
		}

		List<GasStation> gasList = null;
		if (!gasolinetype.equals("null") && !carsharing.equals("null")) {
			refreshDependability();
			switch (gasolinetype) {
				case "Diesel":
					gasList = gasRepo.findByHasDieselAndCarSharing(carsharing);
					break;
				case "Super":
					gasList = gasRepo.findBySuperAndCarSharing(carsharing);
					break;
				case "SuperPlus":
					gasList = gasRepo.findBySuperPlusAndCarSharing(carsharing);
					break;
				case "Gas":
					gasList = gasRepo.findByGasAndCarSharing(carsharing);
					break;
				case "Methane":
					gasList = gasRepo.findByMethaneAndCarSharing(carsharing);
					break;
				default:
					throw new InvalidGasTypeException("Invalid gasoline type");
			}
		} else if (carsharing.equals("null") && !gasolinetype.equals("null")) {
			return getGasStationsByGasolineType(gasolinetype);
		} else if (gasolinetype.equals("null") && !carsharing.equals("null")) {
			return getGasStationByCarSharing(carsharing);
		} else {
			return getAllGasStations();
		}

		if (gasList == null || gasList.isEmpty()) {
			return new LinkedList<GasStationDto>();
		}

		List<GasStationDto> DtoGasList = new ArrayList<>();
		for (GasStation gasStation : gasList) {
			DtoGasList.add(gasConverter.convertToDto(gasStation));
		}

		return DtoGasList; 
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		if (gasStationId == null || gasStationId < 0)
			throw new InvalidGasStationException("No gas station corresponds to Id");

		GasStation gasStation = gasRepo.findById(gasStationId);
		if (gasStation == null) {
			throw new InvalidGasStationException("No gas station corresponds to id");
		}

		if (dieselPrice < -1 || (dieselPrice > -1 && dieselPrice < 0)) {
			throw new PriceException("Invalid Diesel price");
		} else {
			gasStation.setDieselPrice(dieselPrice);
		}

		if (superPrice < -1 || (superPrice > -1 && superPrice < 0)) {
			throw new PriceException("Invalid Diesel price");
		} else {
			gasStation.setSuperPrice(superPrice);
		}

		if (superPlusPrice < -1 || (superPlusPrice > -1 && superPlusPrice < 0)) {
			throw new PriceException("Invalid Diesel price");
		} else {
			gasStation.setSuperPlusPrice(superPlusPrice);
		}

		if (gasPrice < -1 || (gasPrice > -1 && gasPrice < 0)) {
			throw new PriceException("Invalid Diesel price");
		} else {
			gasStation.setGasPrice(gasPrice);
		}

		if (methanePrice < -1 || (methanePrice > -1 && methanePrice < 0)) {
			throw new PriceException("Invalid Diesel price");
		} else {
			gasStation.setMethanePrice(methanePrice);
		}

		if (userId == null || userId < 0) {
			throw new InvalidUserException("Invalid user id");
		} else {
			gasStation.setReportUser(userId);
			gasStation.setUser(userRepo.findById(userId));
		}
		
		
		gasStation.setReportTimestamp(LocalDateTime.now().toString());
		gasRepo.save(gasStation);
		//gasRepo.updateReport(dieselPrice, gasPrice, methanePrice, superPrice, superPlusPrice, userId, gasStationId);
		refreshDependability();
	}

	//What to DO?.
	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		// Aggiungere List<GasStationDto> findCarSharing(double radious, String carsharing)
		if (carSharing == null || carSharing.isEmpty() || carSharing.equals("null")) {
			return new ArrayList<>();
		}

		refreshDependability();
		List<GasStation> gasList = gasRepo.findByCarSharing(carSharing);
		if (gasList == null || gasList.isEmpty()) {
			return new ArrayList<>();
		}

		List<GasStationDto> DtoGasList = new ArrayList<>();
		for (GasStation gas : gasList) {
			DtoGasList.add(gasConverter.convertToDto(gas));
		}

		return DtoGasList;
	}

	/**
	 * Refresh the dependability value of the gas stations inside the db
	 */
	private void refreshDependability() {
		List<GasStation> gasStations = gasRepo.findAll();
		for (GasStation gasStation : gasStations) {
			if (gasStation.getReportTimestamp() == null || gasStation.getReportTimestamp().isEmpty()) {
				continue;
			}

			User reportUser = userRepo.findById(gasStation.getReportUser());
			if (reportUser == null) {
				continue;
			}

			long days = DAYS.between(LocalDateTime.parse(gasStation.getReportTimestamp()), LocalDateTime.now());
			double obsolescence = 0.0;
			if (days <= 7) {
				obsolescence = 1.0 - (double)days/7.0;
			}

			double trustLevel = 50.0 * ((double)reportUser.getReputation() + 5.0)/10.0 + 50.0 * obsolescence;
			gasStation.setReportDependability(trustLevel);
			gasRepo.save(gasStation);
		}
	}
}
