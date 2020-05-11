package it.polito.ezgas.service.impl;

import java.lang.Math;
import it.polito.ezgas.repository.GasStationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class GasStationServiceimpl implements GasStationService{

	@Autowired
	private GasStationRepository gasRepo;

	private final Converter<GasStation, GasStationDto> gasConverter = new GasStationConverter();

	// TODO: the db could return empty lists or null "pointers", so to every call the return must be checked. If it corresponds to one of the two conditions described before we should act as the method specification requires

	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		if (gasStationId == null || gasStationId < 0)
			throw new InvalidGasStationException("No gas station corresponds to Id");
		
		GasStation station = gasRepo.findById(gasStationId);
		if (station == null) {
			throw new InvalidGasStationException("No gas station could be found");
		}

		return gasConverter.convertToDto(station);
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		if (gasStationDto == null || gasStationDto.getDieselPrice() < 0|| gasStationDto.getGasPrice() < 0 ||
				gasStationDto.getMethanePrice() < 0 || gasStationDto.getSuperPrice() < 0 || gasStationDto.getSuperPlusPrice() < 0)
			throw new PriceException("Invalid prices in gas station");
		if ((gasStationDto.getLat() < -90 && gasStationDto.getLat() > 90) ||
				(gasStationDto.getLon() < -180 && gasStationDto.getLon() >= 180))
			throw new GPSDataException("Invalid gas station position");
		
		GasStation gasStation = gasRepo.save(gasConverter.convertFromDto(gasStationDto));
		return gasConverter.convertToDto(gasStation);
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		
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
		return true;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		if (gasolinetype == null || gasolinetype.isEmpty()) {
			throw new InvalidGasTypeException("Invalid gasoline type");
		}

		List<GasStation> gasList;

		switch (gasolinetype) {
			case "Diesel":
				gasList = gasRepo.findByGasolineType(true, false, false, false, false);
				break;
			case "Super":
				gasList = gasRepo.findByGasolineType(false, true, false, false, false);
				break;
			case "SuperPlus":
				gasList = gasRepo.findByGasolineType(false, false, true, false, false);
				break;
			case "Gas":
				gasList = gasRepo.findByGasolineType(false, false, false, true, false);
				break;
			case "Methane":
				gasList = gasRepo.findByGasolineType(false, false, false, false, true);
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
		if ((lat< -90 && lat > 90) || (lon< -180 && lon >= 180))
			throw new GPSDataException("Invalid Coordinates");

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
		if ((lat < -90 && lat > 90) || (lon < -180 && lon >= 180))
			throw new GPSDataException("Invalid Coordinates");

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
		switch (gasolinetype) {
			case "Diesel":
				gasList = gasRepo.findWithoutCoordinates(true, false, false, false, false, carsharing);
				break;
			case "Super":
				gasList = gasRepo.findWithoutCoordinates(false, true, false, false, false, carsharing);
				break;
			case "SuperPlus":
				gasList = gasRepo.findWithoutCoordinates(false, false, true, false, false, carsharing);
				break;
			case "Gas":
				gasList = gasRepo.findWithoutCoordinates(false, false, false, true, false, carsharing);
				break;
			case "Methane":
				gasList = gasRepo.findWithoutCoordinates(false, false, false, false, true, carsharing);
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
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		if (gasStationId == null || gasStationId < 0)
			throw new InvalidGasStationException("No gas station corresponds to Id");
		if (dieselPrice < 0 || superPrice < 0 || superPlusPrice < 0 || gasPrice < 0 || methanePrice < 0)
			throw new PriceException("Invalid prices in gas station");
		if (userId == null || userId < 0)
			throw new InvalidUserException("Invalid user id");
		
		gasRepo.updateReport(dieselPrice, gasPrice, methanePrice, superPrice, superPlusPrice, userId, gasStationId);
	}

	//What to DO?.
	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		// Aggiungere List<GasStationDto> findCarSharing(double radious, String carsharing)
		if (carSharing == null || carSharing.isEmpty()) {
			return new ArrayList<>();
		}

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
}
