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
	GasStationRepository gasRepo;

	Converter<GasStation, GasStationDto> gasConverter = new GasStationConverter();

	// TODO: the db could return empty lists or null "pointers", so to every call the return must be checked. If it corresponds to one of the two conditions described before we should act as the method specification requires

	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		if (gasStationId == null || gasStationId < 0)
			throw new InvalidGasStationException("No gas station corresponds to Id");
		
		GasStation station = gasRepo.findOne(gasStationId);
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
		
		if (gasolinetype.equals("Diesel"))
			gasList = gasRepo.findByGasolineType(true,false,false,false,false);
		else if	(gasolinetype.equals("Super"))
			gasList = gasRepo.findByGasolineType(false,true,false,false,false);
		else if (gasolinetype.equals("SuperPlus"))
			gasList = gasRepo.findByGasolineType(false,false,true,false,false);
		else if (gasolinetype.equals("Gas"))
			gasList = gasRepo.findByGasolineType(false,false,false,true,false);
		else if (gasolinetype.equals("Methane"))
			gasList = gasRepo.findByGasolineType(false,false,false,false,true);
		else
			throw new InvalidGasTypeException ("Invalid gasoline type");
		
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
		// Aggiungere List<GasStationDto> findSquare(double lat, double lon, double radious)
		List<GasStation> gasList = new LinkedList<>(); //GasRepo.findByProximity(lat, lon, 1.0); // TODO: added placeholder, the method must be reworked
		
		List<GasStationDto> DtoGasList= new ArrayList<>();
		for (GasStation gas : gasList) {
			if (Math.sqrt(Math.pow((gas.getLat() - lat), 2) + Math.pow(gas.getLon() - lon, 2)) < 1.0)
				DtoGasList.add(gasConverter.convertToDto(gas));
		}
		return DtoGasList; 
	}
	
	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		if (gasolinetype == null || carsharing == null || gasolinetype.isEmpty() || carsharing.isEmpty()) {
			throw new InvalidGasTypeException("Invalid values"); // TODO: maybe it would be nice to have another exception? (we should open an issue then)
		}
		if ((lat < -90 && lat > 90) || (lon < -180 && lon >= 180))
			throw new GPSDataException("Invalid Coordinates");
		if (!gasolinetype.equals("Methane") && !gasolinetype.equals("Super") && !gasolinetype.equals("Diesel") && !gasolinetype.equals("SuperPlus"))
			throw new InvalidGasTypeException ("Invalid gasoline type");
		
		// Aggiungere List<GasStationDto> findCoordinates(double lat, double lon, double radious, String gasolinetype, String carsharing)
		List<GasStation> gasList = new LinkedList<>();//GasRepo.findByCoordinates(lat, lon, 1.0, gasolinetype, carsharing); // TODO: added placeholder, the method must be reworked
		List<GasStationDto> DtoGasList= new ArrayList<>();
		for (GasStation gas : gasList) {
			if (Math.sqrt(Math.pow((gas.getLat() - lat), 2) + Math.pow(gas.getLon() - lon, 2)) < 1.0)
				DtoGasList.add(gasConverter.convertToDto(gas));
		}
		return DtoGasList; 
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		/*if (gasolinetype != "Methane" && gasolinetype != "Super" && gasolinetype != "Diesel" && gasolinetype !="SuperPlus")
			throw new InvalidGasTypeException ("Invalid gasoline type");*/
		
		// Aggiungere List<GasStationDto> findWithoutCoordinatesProx(String gasolinetype, String carsharing)
		List<GasStation> gasList = new ArrayList<>();
		if (gasolinetype == null || gasolinetype.isEmpty())
			throw new InvalidGasTypeException("Invalid gasoline type");
		else if (gasolinetype.equals("Diesel"))
			gasList = gasRepo.findWithoutCoordinates(true,false,false,false,false, carsharing);
		else if	(gasolinetype.equals("Super"))
			gasList = gasRepo.findWithoutCoordinates(false,false,false,true,false, carsharing);
		else if (gasolinetype.equals("SuperPlus"))
			gasList = gasRepo.findWithoutCoordinates(false,false,false,false,true, carsharing);
		else if (gasolinetype.equals("Gas"))
			gasList = gasRepo.findWithoutCoordinates(false,true,false,false,false, carsharing);
		else if (gasolinetype.equals("Methane"))
			gasList = gasRepo.findWithoutCoordinates(false,false,true,false,false, carsharing);
		else
			throw new InvalidGasTypeException ("Invalid gasoline type");
		
		//GasRepo.findWithoutCoordinates(gasolinetype, carsharing);
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
		
		GasStationDto station = getGasStationById(gasStationId);
		if (station.getHasDiesel())
			station.setDieselPrice(dieselPrice);
		if (station.getHasMethane())
			station.setMethanePrice(methanePrice);
		if (station.getHasGas())
			station.setGasPrice(gasPrice);
		if (station.getHasSuper())
			station.setSuperPrice(superPrice);
		if (station.getHasSuperPlus())
			station.setSuperPlusPrice(superPlusPrice);
		station.setReportUser(userId);

		// TODO: add a method in GasStationRepository to perform report's updates
	}

	//What to DO?.
	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		// Aggiungere List<GasStationDto> findCarSharing(double radious, String carsharing)
		List<GasStation> gasList = new LinkedList<>();//GasRepo.findByCarSharing(1.0 carsharing); // TODO: added placeholder, the method must be reworked
		List<GasStationDto> DtoGasList = new ArrayList<>();
		for (GasStation gas : gasList) {
			//if (Math.sqrt(Math.pow((gas.getLat()-lat), 2) + Math.pow(gas.getLon()-lon , 2)) < 1.0)
			DtoGasList.add(gasConverter.convertToDto(gas));
		}
		return DtoGasList;
	}
}
