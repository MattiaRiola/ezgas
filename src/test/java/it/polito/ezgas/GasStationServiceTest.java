package it.polito.ezgas;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.service.impl.GasStationServiceimpl;
//import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import exception.GPSDataException;
import exception.PriceException;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GasStationServiceTest {
	
	@Autowired
    private GasStationRepository gasStationRepository;
	@Autowired
	private UserRepository userRepository;
	
	private GasStationService dut;
	private GasStation gs;
	
	@Before
	public void setUp() {
		//The @Autowired in GasStationServiceimpl should be commented in order to execute correctly this class.
		//The fail should be decommented in your code (have been commented due to problem with my eclipse settings).
		this.dut = new GasStationServiceimpl(this.gasStationRepository, this.userRepository);
		gs = new GasStation("Not so much eco friendly", "Via",
                true, false, true, false, true,true, "Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, LocalDateTime.now().toString(), 76);
		gasStationRepository.save(gs);
	}
	
	
	@Test
	public void TestGetGasStationById() {

		try {
			this.dut.getGasStationById(-1);
			fail("No exception has been generated");
		}catch (Exception e) {		
			assertEquals(0,0);
		}
		List<GasStation> l = gasStationRepository.findAll();
        Integer id = l.get(0).getGasStationId();
		try {
			GasStationDto testGs = this.dut.getGasStationById(id);
			assertEquals(gs.getGasStationId(),testGs.getGasStationId(), "Error id");
		}catch (Exception e) {
			fail("Exception has been generated");
		}
	}//EndTest.
	
	@Test
	public void TestsaveGasStation() {
		GasStationDto gsDto = new GasStationDto();
		gsDto.setGasStationId(1);
		gsDto.setGasStationName("TestGas");
		
		//The exception was tested in the mockito test.
		
		try {
			GasStationDto resGas = this.dut.saveGasStation(gsDto);
			assertEquals(gsDto.getGasStationName(),resGas.getGasStationName(), "Error saving gas station");
		}catch (Exception e) {
			fail("Exception has been generated");
		}
	}//EndTest.
	
	@Test 
	public void TestGetAllGasStation() {
		List<GasStationDto> resList;
		resList = dut.getAllGasStations();
		assertEquals(resList.size(), 1,"Error");
	}//EndTest.
	
	@Test
	public void TestdeleteGasStation() {
		List<GasStationDto> resList;
		resList = dut.getAllGasStations();
		GasStationDto gs = resList.get(0);
		try {
			Boolean res = this.dut.deleteGasStation(gs.getGasStationId());
			assertTrue(res, "Error delating gas station");
		}catch (Exception e) {
			e.printStackTrace();
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
			assertEquals(resList.size(), 1, "Error get gasoline by type");
			resList = this.dut.getGasStationsByGasolineType("Super");
			assertEquals(resList.size(), 0, "Error get gasoline by type");
			resList = this.dut.getGasStationsByGasolineType("SuperPlus");
			assertEquals(resList.size(), 1, "Error get gasoline by type");
			resList = this.dut.getGasStationsByGasolineType("Gas");
			assertEquals(resList.size(), 0, "Error get gasoline by type");
			resList = this.dut.getGasStationsByGasolineType("Methane");
			assertEquals(resList.size(), 1, "Error get gasoline by type");
			resList = this.dut.getGasStationsByGasolineType("PremiumDiesel");
			assertEquals(resList.size(),1,"Error get gasoline by type");
			
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
			resList = this.dut.getGasStationsByProximity(10.32, -26.11);
			assertEquals(resList.size(), 1);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
	}//EndTest.
	
	@Test
	public void TestGetGasStationsWithCoordinates() {
		try {
			this.dut.getGasStationsWithCoordinates(-100, 0, 0, "Diesel","Car2Go");
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsWithCoordinates(100, 0, 0, "Diesel","Car2Go");
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsWithCoordinates(0, -200, 0,"Diesel","Car2Go");
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsWithCoordinates(0, 200, 0, "Diesel","Car2Go");
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsWithCoordinates(0, 0, 0, null ,"Car2Go");
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		try {
			this.dut.getGasStationsWithCoordinates(0, 0, 0, "Diesel" ,null);
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		List<GasStationDto> resList;
		
		try {
			resList = this.dut.getGasStationsWithCoordinates(10.32, -26.11, 0,"Diesel","Car2Go");
			assertEquals(resList.size(), 1);
		}catch (Exception e) {
			fail("Exception has been generated");
			
		}
		
		try {
			resList = this.dut.getGasStationsWithCoordinates(10.32, -26.11, -1,"Diesel","Car2Go");
			assertEquals(resList.size(), 1);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		try {
			resList = this.dut.getGasStationsWithCoordinates(10.32, -26.11, -1000,"Diesel","Car2Go");
			assertEquals(resList.size(), 1);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		
		//Distance from 10.32, -26.11 to 10.33, -26.11 = 1.112km
		
		try { //Distance from 10.32, -26.11 to 10.33, -26.11 = 1.112km
			resList = this.dut.getGasStationsWithCoordinates(10.33, -26.11, 2,"Diesel","Car2Go");
			assertEquals(resList.size(), 1);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		
		try { //Distance from 10.32, -26.11 to 10.33, -26.11 = 1.112km
			resList = this.dut.getGasStationsWithCoordinates(10.33, -26.11, 500,"Diesel","Car2Go");
			assertEquals(resList.size(), 1);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		
		try { //Distance from 10.32, -26.11 to 10.33, -26.11 = 1.112km
			resList = this.dut.getGasStationsWithCoordinates(10.33, -26.11, 1,"Diesel","Car2Go");
			assertEquals(resList.size(), 0);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		
		try { //Distance from 10.32, -26.11 to 10.33, -26.11 = 1.112km
			resList = this.dut.getGasStationsWithCoordinates(10.33, -26.11, -100,"Diesel","Car2Go");
			assertEquals(resList.size(), 0);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		
		try { //Distance from 10.32, -26.11 to 10.33, -26.11 = 1.112km
			resList = this.dut.getGasStationsWithCoordinates(10.33, -26.11, 0,"Diesel","Car2Go");
			assertEquals(resList.size(), 0);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		
		//Distance from 10.32, -26.11 to 10.40, -26.50 = 43.577km
		
		try { 
			resList = this.dut.getGasStationsWithCoordinates(10.40, -26.50, 0,"Diesel","Car2Go");
			assertEquals(resList.size(), 0);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		try { 
			resList = this.dut.getGasStationsWithCoordinates(10.40, -26.50, 1,"Diesel","Car2Go");
			assertEquals(resList.size(), 0);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		try { 
			resList = this.dut.getGasStationsWithCoordinates(10.40, -26.50, 41,"Diesel","Car2Go");
			assertEquals(resList.size(), 0);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		try { 
			resList = this.dut.getGasStationsWithCoordinates(10.40, -26.50, 43,"Diesel","Car2Go");
			assertEquals(resList.size(), 0);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		try { 
			resList = this.dut.getGasStationsWithCoordinates(10.40, -26.50, 44,"Diesel","Car2Go");
			assertEquals(resList.size(), 1);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
		try { 
			resList = this.dut.getGasStationsWithCoordinates(10.40, -26.50, 200,"Diesel","Car2Go");
			assertEquals(resList.size(), 1);
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
			this.dut.getGasStationsWithoutCoordinates("","Car2Go");
			fail("No exception has been generated");
		}catch (Exception e) {
			assertEquals(0,0);
		}
		List<GasStationDto> resList;
		try {
			resList = this.dut.getGasStationsWithoutCoordinates("Diesel", "Car2Go");
			assertEquals(resList.size(),1);
		}catch (Exception e) {
			fail("Exception has been generated");
		}
	}//EndTest.
	
	@Test
	public void setReport() {
		//All the exception tested in mockito test.
		
		List<GasStationDto> resList;
		resList = dut.getAllGasStations();
		GasStationDto gs = resList.get(0);
		
		try {
			this.dut.setReport(gs.getGasStationId(), 1.0, 1.0, 1.0, 1.0, 1.0,1.0, 0);
			GasStationDto resDto = this.dut.getGasStationById(gs.getGasStationId());
			assertEquals(resDto.getMethanePrice(), 1.0, "Error report");
			assertEquals(resDto.getGasPrice(), 1.0, "Error report");
			assertEquals(resDto.getSuperPrice(), 1.0, "Error report");
			assertEquals(resDto.getSuperPlusPrice(), 1.0, "Error report");
			assertEquals(resDto.getDieselPrice(), 1.0, "Error report");
			assertEquals(resDto.getPremiumDieselPrice(), 1.0, "Error report");
		}catch (Exception e) {
			fail("Exception has been generated");
		}
	}//EndTest.
	
	@Test
	public void TestGetGasStationByCarSharing() {
		List<GasStationDto> resList;
		
		try {
			resList = this.dut.getGasStationByCarSharing("Car2Go");
			assertEquals(resList.size(),1);
		}catch (Exception e) {
			fail("No exception has been generated");
		}
		
	}//EndTest.
	
}
//EndClass.