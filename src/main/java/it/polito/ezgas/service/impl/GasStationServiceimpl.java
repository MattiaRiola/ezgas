package it.polito.ezgas.service.impl;

import java.lang.Math;
import it.polito.ezgas.repository.GasStationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
	GasStationRepository GasRepo;
	Converter<GasStation, GasStationDto> gasConverter = new GasStationConverter();
	
	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		if (gasStationId.intValue() < 0||gasStationId==null) 
			throw new InvalidGasStationException("No gas station corresponds to Id");
		
		GasStation station = GasRepo.findOne(gasStationId);
		return gasConverter.convertToDto(station);
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		if (gasStationDto.getDieselPrice() < 0|| gasStationDto.getGasPrice()<0|| gasStationDto.getMethanePrice()<0|| gasStationDto.getSuperPrice()<0|| gasStationDto.getSuperPlusPrice()<0)
			throw new PriceException("Invalid prices in gas station");
		if ((gasStationDto.getLat()<-90&&gasStationDto.getLat()>90)||(gasStationDto.getLon()<-180&&gasStationDto.getLon()>=180))
			throw new GPSDataException("Invalid gas station position");
		
		GasStation gasStation = GasRepo.save(gasConverter.convertFromDto(gasStationDto));
		return gasConverter.convertToDto(gasStation);
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		
		List<GasStation> gasList = GasRepo.findAll();
		List<GasStationDto> DtoGasList= new ArrayList<>();
		ListIterator<GasStation> literator = gasList.listIterator();
		while (literator.hasNext()) {
			DtoGasList.add(gasConverter.convertToDto(literator.next()));
		}
		return DtoGasList;
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		if (gasStationId.intValue() < 0)
			throw new InvalidGasStationException("Invalid Id");
		if (getGasStationById(gasStationId)== null)
			return false;
		GasRepo.delete(gasStationId);
		return true;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		
		List<GasStation> gasList = new  ArrayList<>();
		
		if (gasolinetype == "Diesel")
			gasList = GasRepo.findGasoline(1,0,0,0,0);
		else if	(gasolinetype == "Super")
			gasList = GasRepo.findGasoline(0,1,0,0,0);
		else if (gasolinetype == "SuperPlus")
			gasList = GasRepo.findGasoline(0,0,1,0,0);
		else if (gasolinetype == "Gas")
			gasList = GasRepo.findGasoline(0,0,0,1,0);
		else if (gasolinetype == "Methane")
			gasList = GasRepo.findGasoline(0,0,0,0,1);
		else
			throw new InvalidGasTypeException ("Invalid gasoline type");
		
		List<GasStationDto> DtoGasList= new ArrayList<>();
		ListIterator<GasStation> literator = gasList.listIterator();
		while (literator.hasNext()) {
			DtoGasList.add(gasConverter.convertToDto(literator.next()));
		}
		return DtoGasList; 
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		if ((lat<-90&&lat>90)||(lon<-180&&lon>=180))
			throw new GPSDataException("Invalid Coordinates");
		// Aggiungere List<GasStationDto> findSquare(double lat, double lon, double radious)
		List<GasStation> gasList = GasRepo.findProx(lat, lon, 1.0);
		
		List<GasStationDto> DtoGasList= new ArrayList<>();
		ListIterator<GasStation> literator = gasList.listIterator();
		while (literator.hasNext()) {
			GasStation gas = literator.next();	
			if (Math.sqrt(Math.pow((gas.getLat()-lat), 2) + Math.pow(gas.getLon()-lon , 2)) < 1.0)
				DtoGasList.add(gasConverter.convertToDto(gas));
		}
		return DtoGasList; 
	}
	
	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		if ((lat<-90&&lat>90)||(lon<-180&&lon>=180))
			throw new GPSDataException("Invalid Coordinates");
		if (gasolinetype != "Methane" && gasolinetype != "Super" && gasolinetype != "Diesel" && gasolinetype !="SuperPlus")
			throw new InvalidGasTypeException ("Invalid gasoline type");
		
		// Aggiungere List<GasStationDto> findCoordinates(double lat, double lon, double radious, String gasolinetype, String carsharing)
		List<GasStation> gasList = GasRepo.findCoordinates(lat, lon, 1.0, gasolinetype, carsharing);
		List<GasStationDto> DtoGasList= new ArrayList<>();
		ListIterator<GasStation> literator = gasList.listIterator();
		while (literator.hasNext()) {
			GasStation gas = literator.next();	
			if (Math.sqrt(Math.pow((gas.getLat()-lat), 2) + Math.pow(gas.getLon()-lon , 2)) < 1.0)
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
		if (gasolinetype == "Diesel")
			gasList = GasRepo.findWithoutCoordinates(1,0,0,0,0, carsharing);
		else if	(gasolinetype == "Super")
			gasList = GasRepo.findWithoutCoordinates(0,1,0,0,0, carsharing);
		else if (gasolinetype == "SuperPlus")
			gasList = GasRepo.findWithoutCoordinates(0,0,1,0,0, carsharing);
		else if (gasolinetype == "Gas")
			gasList = GasRepo.findWithoutCoordinates(0,0,0,1,0, carsharing);
		else if (gasolinetype == "Methane")
			gasList = GasRepo.findWithoutCoordinates(0,0,0,0,1, carsharing);
		else
			throw new InvalidGasTypeException ("Invalid gasoline type");
		
		//GasRepo.findWithoutCoordinates(gasolinetype, carsharing);
		List<GasStationDto> DtoGasList= new ArrayList<>();
		ListIterator<GasStation> literator = gasList.listIterator();
		while (literator.hasNext()) {
			DtoGasList.add(gasConverter.convertToDto(literator.next()));
		}
		return DtoGasList; 
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		if (gasStationId.intValue() < 0) 
			throw new InvalidGasStationException("No gas station corresponds to Id");
		if (dieselPrice < 0|| superPrice<0|| superPlusPrice<0|| gasPrice<0|| methanePrice<0)
			throw new PriceException("Invalid prices in gas station");
		if (userId.intValue()<0)
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
	}

	//What to DO?.
	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		// Aggiungere List<GasStationDto> findCarSharing(double radious, String carsharing)
		List<GasStation> gasList = GasRepo.findCarSharing(1.0 carsharing);
		List<GasStationDto> DtoGasList= new ArrayList<>();
		ListIterator<GasStation> literator = gasList.listIterator();
		while (literator.hasNext()) {
			GasStation gas = literator.next();	
			//if (Math.sqrt(Math.pow((gas.getLat()-lat), 2) + Math.pow(gas.getLon()-lon , 2)) < 1.0)
				DtoGasList.add(gasConverter.convertToDto(gas));
		}
		return DtoGasList;
	}
	
	
	
	
	

}
