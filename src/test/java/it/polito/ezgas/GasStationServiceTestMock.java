package it.polito.ezgas;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import it.polito.ezgas.converter.impl.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.service.impl.GasStationServiceimpl;

public class GasStationServiceTestMock {

	private GasStationRepository gasRep;
	private UserRepository userRep;
	private GasStationConverter gasConverter;
	private GasStationService dut;
	private List <GasStation> testList;
	private GasStation gs = new GasStation();
	private GasStationDto gsDto = new GasStationDto();
	private String cs = "CarSharing";
	
	@BeforeEach
	public void setUp() {
		this.testList = new ArrayList <GasStation>();
		this.gasRep = mock(GasStationRepository.class);
		this.userRep = mock(UserRepository.class);
		this.gasConverter = mock(GasStationConverter.class);
		this.gs.setGasStationId(0);
		this.gs.setReportUser(0);
		this.gsDto.setGasStationId(0);
		this.gsDto.setReportUser(0);
		//GasStationRepository Mock.
		when(this.gasRep.findById(0)).thenReturn(this.gs);
		when(this.gasRep.save(this.gs)).thenReturn(this.gs);
		when(this.gasRep.findByDiesel()).thenReturn(this.testList);
		when(this.gasRep.findBySuper()).thenReturn(this.testList);
		when(this.gasRep.findBySuperPlus()).thenReturn(this.testList);
		when(this.gasRep.findByGas()).thenReturn(this.testList);
		when(this.gasRep.findByMethane()).thenReturn(this.testList);
		when(this.gasRep.findByHasDieselAndCarSharing(this.cs)).thenReturn(this.testList);
		when(this.gasRep.findBySuperAndCarSharing(this.cs)).thenReturn(this.testList);
		when(this.gasRep.findBySuperPlusAndCarSharing(this.cs)).thenReturn(this.testList);
		when(this.gasRep.findByGasAndCarSharing(this.cs)).thenReturn(this.testList);
		when(this.gasRep.findByMethaneAndCarSharing(this.cs)).thenReturn(this.testList);
		when(this.gasRep.findByCarSharing(this.cs)).thenReturn(this.testList);
		when(this.gasRep.findAll()).thenReturn(this.testList);
		
		//Converter Mock.
		when(this.gasConverter.convertToDto(this.gs)).thenReturn(this.gsDto); 
		when(this.gasConverter.convertFromDto(this.gsDto)).thenReturn(this.gs);
		
		//UserRepository Mock.
		when(this.userRep.findById(0)).thenReturn(null);
		this.dut = new GasStationServiceimpl(this.gasRep, this.gasConverter, this.userRep);
	}
	
	@Test
	void TestGetGasStationById() {
		try {
			this.dut.getGasStationById(-1);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		
		try {
			GasStationDto resGs = this.dut.getGasStationById(0);
			assertEquals(resGs, this.gsDto, "Error, getGasStationById");
		}catch (Exception e) {
			fail("Exception has been generated");
		}
	}//EndTest.
	
	@Test
	void TestSaveGasStation() {
		//TestExceptions.
		try {
			this.dut.saveGasStation(null);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		GasStationDto testDto = new GasStationDto();
		testDto.setDieselPrice(-2);
		testDto.setGasPrice(1);
		testDto.setMethanePrice(1);
		testDto.setSuperPlusPrice(1);
		testDto.setSuperPrice(1);
		try {
			this.dut.saveGasStation(testDto);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		testDto.setDieselPrice(1);
		testDto.setGasPrice(-2);
		try {
			this.dut.saveGasStation(testDto);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		testDto.setGasPrice(1);
		testDto.setMethanePrice(-2);
		try {
			this.dut.saveGasStation(testDto);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		testDto.setMethanePrice(1);
		testDto.setSuperPlusPrice(-2);
		try {
			this.dut.saveGasStation(testDto);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		testDto.setSuperPlusPrice(1);
		testDto.setSuperPrice(-2);
		try {
			this.dut.saveGasStation(testDto);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		//Test non-exception method.
		try {
			GasStationDto resGs = this.dut.saveGasStation(this.gsDto);
			assertEquals(resGs, this.gsDto, "Error, getGasStationById");
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		
	}//EndTest.
	
	@Test 
	void TestGetAllGasStation() {
		List<GasStationDto> resList;
		resList = dut.getAllGasStations();
		assertEquals(resList.size(), 0,"Error: non empty list");
		this.testList.add(new GasStation());
		resList = dut.getAllGasStations();
		assertEquals(resList.size(), 1,"Error: empty list");
	}//EndTest.
	
	@Test 
	void TestDeleteGasStation() {
		try {
			this.dut.deleteGasStation(-1);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		
		try {
			assertTrue(this.dut.deleteGasStation(0), "Error delating gas station");
		}catch (Exception e) {
			fail("Exception has been generated");
		}
	}//EndTest.
	
	@Test
	public void TestGetGasStationsByGasolineType() {
		try {
			this.dut.getGasStationsByGasolineType(null);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsByGasolineType("");
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		List<GasStationDto> resList;
		try {
			resList = this.dut.getGasStationsByGasolineType("Diesel");
			assertEquals(resList.size(),this.testList.size(), "Error get gasoline by type");
			resList = this.dut.getGasStationsByGasolineType("Super");
			assertEquals(resList.size(),this.testList.size(), "Error get gasoline by type");
			resList = this.dut.getGasStationsByGasolineType("SuperPlus");
			assertEquals(resList.size(),this.testList.size(), "Error get gasoline by type");
			resList = this.dut.getGasStationsByGasolineType("Gas");
			assertEquals(resList.size(),this.testList.size(), "Error get gasoline by type");
			resList = this.dut.getGasStationsByGasolineType("Methane");
			assertEquals(resList.size(),this.testList.size(), "Error get gasoline by type");
		}catch (Exception e) {
			fail("Exception has been generated");
		}
	}//EndTest.
	
	@Test
	public void TestGetGasStationsByProximity() {
		try {
			this.dut.getGasStationsByProximity(-100, 0);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsByProximity(100, 0);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsByProximity(0, 200);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsByProximity(0, -200);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		
		List<GasStationDto> resList;
		
		try {
			resList = this.dut.getGasStationsByProximity(50, 50);
			assertEquals(resList.size(),this.testList.size());
		}catch (Exception e) {
			fail("Exception has been generated");
			
		}
	}//EndTest.
	
	@Test
	public void TestGetGasStationsWithCoordinates() {
		try {
			this.dut.getGasStationsWithCoordinates(-100, 0, "Diesel",this.cs);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsWithCoordinates(100, 0, "Diesel",this.cs);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsWithCoordinates(0, -200, "Diesel",this.cs);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsWithCoordinates(0, 200, "Diesel",this.cs);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsWithCoordinates(0, 0, null ,this.cs);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsWithCoordinates(0, 0, "Diesel" ,null);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		List<GasStationDto> resList;
		
		try {
			resList = this.dut.getGasStationsWithCoordinates(50, 50, "Diesel",this.cs);
			assertEquals(resList.size(),this.testList.size());
		}catch (Exception e) {
			fail("Exception has been generated");
			
		}
	}//EndTest.
	
	@Test
	public void TestGetGasStationsWithoutCoordinates() {
		try {
			this.dut.getGasStationsWithoutCoordinates("Diesel",null);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsWithoutCoordinates("",this.cs);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		List<GasStationDto> resList;
		try {
			resList = this.dut.getGasStationsWithoutCoordinates("Diesel",this.cs);
			assertEquals(resList.size(),this.testList.size());
		}catch (Exception e) {
			fail("Exception has been generated");
		}
	}//EndTest.
	
	@Test
	public void TestSetReport() {
		try {
			this.dut.setReport(-1, 0, 0, 0, 0, 0, 0);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.setReport(0, -2, 0, 0, 0, 0, 0);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.setReport(0, 0, -2, 0, 0, 0, 0);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.setReport(0, 0, 0, -2, 0, 0, 0);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.setReport(0, 0, 0, 0, -2, 0, 0);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.setReport(0, 0, 0, 0, 0, -2, 0);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.setReport(0, 0, 0, 0, 0, 0, -1);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		
		try {
			this.dut.setReport(0, 1, 1, 1, 1, 1, 0);
			assertEquals(0,0);
		}catch (Exception e) {
			fail("No exception has been generated");
		}
	}//EndTest.
	
	@Test
	public void TestGetGasStationByCarSharing() {
		List<GasStationDto> resList;
		
		try {
			resList = this.dut.getGasStationByCarSharing(this.cs);
			assertEquals(resList.size(),this.testList.size());
		}catch (Exception e) {
			fail("No exception has been generated");
		}
	}//EndTest.
	
}
//EndClass.