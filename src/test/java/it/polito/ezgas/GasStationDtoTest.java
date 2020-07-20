package it.polito.ezgas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.PriceReportDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;

public class GasStationDtoTest {
	private Integer id = new Integer(1);
	private String gasStationName = "GasStationTest";
	private String gasStationAddress = "AddressTest";
	private boolean hasDiesel = true;
    private boolean hasSuper = true;
    private boolean hasSuperPlus = true;
    private boolean hasGas = true;
    private boolean hasMethane = true;
    private boolean hasPremiumDiesel = true;
    private String carSharing = "TestCarSharing";
    private double lat = 45.0;
    private double lon = 45.0;
    private double dieselPrice = 1.2;
    private double superPrice = 1.7;
    private double superPlusPrice = 1.8;
    private double gasPrice = 1.6;
    private double methanePrice = 1.3;
    private double premiumDieselPrice = 1.4;
    private Integer reportUser = 1;
    private String reportTimestamp = (new Timestamp(System.currentTimeMillis())).toString();
    private double reportDependability = 1.0;
    private GasStationDto dut;
    
    @Test
    public void testUserDto() {
    	this.dut = new GasStationDto (id, gasStationName, gasStationAddress, 
				hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, 
				lat, lon,  dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice,
			    reportUser, reportTimestamp, reportDependability);
    	UserDto testUser = new UserDto();
    	testUser.setUserId(1);
    	this.dut.setUserDto(testUser);
    	assertEquals(testUser.getUserId().intValue(), this.dut.getUserDto().getUserId().intValue(), "Error: GasStation dto - userDto"); 
    }//EndTest.
    
    /*@Test 
    public void testPriceReport() {
    	fail("TODO : fix this test (?)");
    	this.dut = new GasStationDto (id, gasStationName, gasStationAddress, 
				hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, 
				lat, lon,  dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice, 
			    reportUser, reportTimestamp, reportDependability);
    	List<PriceReportDto> priceReportDtos = new ArrayList<PriceReportDto>();
    	/* They deleted the list of price report in the GasStationDto */
    	//this.dut.setPriceReportDtos(priceReportDtos);
    	//assertEquals(priceReportDtos, this.dut.getPriceReportDtos(), "Error: GasStation dto - price report"); 
    //}//EndTest*/
    
    @Test
	public void testReportDependability() {
		this.dut = new GasStationDto (id, gasStationName, gasStationAddress, 
				hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, 
				lat, lon,  dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice,
			    reportUser, reportTimestamp, reportDependability);
		
		assertEquals(this.reportDependability, dut.getReportDependability(), "Error: GasStation dto - get Dependability issue"); 
		
		double newReportDependability = this.reportDependability*2;
		this.dut.setReportDependability(newReportDependability);
		
		assertEquals(newReportDependability, this.dut.getReportDependability(), "Error: GasStation dto - set Dependability issue");
	}//EndCase.
    
    @Test
	public void testReportUser() {
    	this.dut = new GasStationDto (id, gasStationName, gasStationAddress, 
				hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, 
				lat, lon,  dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice,
			    reportUser, reportTimestamp, reportDependability);
		
		Integer newUser = new Integer(this.reportUser.intValue()*2);
		this.dut.setReportUser(newUser);
		assertEquals(newUser.intValue(), this.dut.getReportUser().intValue(), "Error: GasStation dto - set reportUser issue");
	}
	
	@Test
	public void testReportTimeStamp() {
		this.dut = new GasStationDto (id, gasStationName, gasStationAddress, 
				hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, 
				lat, lon,  dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice, 
			    reportUser, reportTimestamp, reportDependability);
		assertEquals(this.reportTimestamp, dut.getReportTimestamp(), "Error: GasStation Entity - get timestamp issue");
		String newTimestamp = (new Timestamp(System.currentTimeMillis())).toString();
		this.dut.setReportTimestamp(newTimestamp);
		
		assertEquals(newTimestamp, this.dut.getReportTimestamp() , "Error: GasStation dto - set timestamp issue");
	}
	
	@Test
	public void testCarSharing() {
		this.dut = new GasStationDto (id, gasStationName, gasStationAddress, 
				hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, 
				lat, lon,  dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice,
			    reportUser, reportTimestamp, reportDependability);
		assertEquals(this.carSharing, this.dut.getCarSharing(), "Error: GasStation dto - get carSharing issue");
		
		String newCarsharing = "NewCarSharing";
		this.dut.setCarSharing(newCarsharing);
		
		assertEquals(newCarsharing, this.dut.getCarSharing(), "Error: GasStation dto - set carSharing issue");
	}
	
	@Test
	public void testFuelTypes() {
		this.dut = new GasStationDto (id, gasStationName, gasStationAddress, 
				hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, 
				lat, lon,  dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice, 
			    reportUser, reportTimestamp, reportDependability);
		 assertTrue(this.dut.getHasDiesel(), "Error: GasStation dto - set fuel issue");
		 assertTrue(this.dut.getHasGas(), "Error: GasStation dto - set fuel issue");
		 assertTrue(this.dut.getHasMethane(), "Error: GasStation dto - set fuel issue");
		 assertTrue(this.dut.getHasSuper(), "Error: GasStation dto - set fuel issue");
		 assertTrue(this.dut.getHasSuperPlus(), "Error: GasStation dto - set fuel issue");
		 
		 this.dut.setHasDiesel(false);
		 this.dut.setHasGas(false);
		 this.dut.setHasMethane(false);
		 this.dut.setHasSuper(false);
		 this.dut.setHasSuperPlus(false);
		 
		 assertFalse(this.dut.getHasDiesel(), "Error: GasStation dto - get fuel issue");
		 assertFalse(this.dut.getHasGas(), "Error: GasStation dto - get fuel issue");
		 assertFalse(this.dut.getHasMethane(), "Error: GasStation dto - get fuel issue");
		 assertFalse(this.dut.getHasSuper(), "Error: GasStation dto - get fuel issue");
		 assertFalse(this.dut.getHasSuperPlus(), "Error: GasStation dto - get fuel issue");
	}
	
	@Test
	public void testCoordinates() {
		this.dut = new GasStationDto (id, gasStationName, gasStationAddress, 
				hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, 
				lat, lon,  dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice, 
			    reportUser, reportTimestamp, reportDependability);
		assertEquals(this.lat, this.dut.getLat(), "Error: GasStation dto - get lat issue");
		assertEquals(this.lon, this.dut.getLon(), "Error: GasStation dto - get lon issue");
		
		double newLat = this.lat*2;
		double newLon = this.lon*2;
		
		this.dut.setLat(newLat);
		this.dut.setLon(newLon);
		
		assertEquals(newLat, this.dut.getLat(), "Error: GasStation dto - set lat issue");
		assertEquals(newLon, this.dut.getLon(), "Error: GasStation dto - set lon issue");
	}
	
	@Test
	public void testPrices() {
		this.dut = new GasStationDto (id, gasStationName, gasStationAddress, 
				hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, 
				lat, lon,  dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice, 
			    reportUser, reportTimestamp, reportDependability);
		assertEquals(this.dieselPrice, this.dut.getDieselPrice().doubleValue(), "Error: GasStation dto - get price issue");
		assertEquals(this.superPrice, this.dut.getSuperPrice().doubleValue(), "Error: GasStation dto - get price issue");
		assertEquals(this.methanePrice, this.dut.getMethanePrice().doubleValue(), "Error: GasStation dto - get price issue");
		assertEquals(this.gasPrice, this.dut.getGasPrice().doubleValue(), "Error: GasStation dto - get price issue");
		assertEquals(this.superPlusPrice, this.dut.getSuperPlusPrice().doubleValue(), "Error: GasStation dto - get price issue");
		assertEquals(this.premiumDieselPrice, this.dut.getPremiumDieselPrice().doubleValue(), "Error: GasStation dto - get price issue");
		
		double newDieselPrice = this.dieselPrice*2;
		double newSuperPrice = this.superPrice*2;
		double newMethanePrice = this.methanePrice*2;
		double newGasPrice = this.gasPrice*2;
		double newSuperPlusPrice = this.superPlusPrice*2;
		double newPremiumDieselPrice = this.premiumDieselPrice*2;
		
		this.dut.setDieselPrice(newDieselPrice);
		this.dut.setSuperPrice(newSuperPrice);
		this.dut.setMethanePrice(newMethanePrice);
		this.dut.setGasPrice(newGasPrice);
		this.dut.setSuperPlusPrice(newSuperPlusPrice);
		this.dut.setPremiumDieselPrice(newPremiumDieselPrice);
		
		assertEquals(newDieselPrice, this.dut.getDieselPrice().doubleValue(), "Error: GasStation dto - set price issue");
		assertEquals(newSuperPrice, this.dut.getSuperPrice().doubleValue(), "Error: GasStation dto - set price issue");
		assertEquals(newMethanePrice, this.dut.getMethanePrice().doubleValue(), "Error: GasStation dto - set price issue");
		assertEquals(newGasPrice, this.dut.getGasPrice().doubleValue(), "Error: GasStation dto - set price issue");
		assertEquals(newSuperPlusPrice, this.dut.getSuperPlusPrice().doubleValue(), "Error: GasStation dto - set price issue");
		assertEquals(newPremiumDieselPrice, this.dut.getPremiumDieselPrice().doubleValue(), "Error: GasStation dto - get price issue");
	}
	
	@Test
	public void testAddress() {
		this.dut = new GasStationDto (id, gasStationName, gasStationAddress, 
				hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, 
				lat, lon,  dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice,  premiumDieselPrice,
			    reportUser, reportTimestamp, reportDependability);
		assertEquals(this.gasStationAddress, this.dut.getGasStationAddress(), "Error: GasStation dto - get address issue");
		
		String newAddr = "New Address";
		this.dut.setGasStationAddress(newAddr);
		assertEquals(newAddr, this.dut.getGasStationAddress(), "Error: GasStation dto - set address issue");
	}
	@Test
	public void testId() {
		this.dut = new GasStationDto (id, gasStationName, gasStationAddress, 
				hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, 
				lat, lon,  dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice, 
			    reportUser, reportTimestamp, reportDependability);
		Integer ID = 2;
		this.dut.setGasStationId(ID);
		assertEquals(ID.intValue(), this.dut.getGasStationId().intValue(), "Error: GasStation dto - set/get id issue");
	}
	
	@Test 
	public void testName() {
		this.dut = new GasStationDto (id, gasStationName, gasStationAddress, 
				hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, hasPremiumDiesel, carSharing, 
				lat, lon,  dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice, 
			    reportUser, reportTimestamp, reportDependability);
		assertEquals(this.gasStationName, this.dut.getGasStationName(),  "Error: GasStation dto - get name issue");
		
		String newName = "New name";
		
		this.dut.setGasStationName(newName);
		assertEquals(newName, this.dut.getGasStationName(),  "Error: GasStation dto - set name issue");
	}
}
