package it.polito.ezgas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import it.polito.ezgas.entity.GasStation;


public class GasStationEntityTest {
	
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
    
	private GasStation dut;
	//= new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.reportUser,this.reportTimestamp,this.reportDependability);
	
	@Test
	public void testReportDependability() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,this.hasPremiumDiesel,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.premiumDieselPrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		
		assertEquals(this.reportDependability, dut.getReportDependability(), "Error: GasStation Entity - get Dependability issue"); 
		
		double newReportDependability = this.reportDependability*2;
		this.dut.setReportDependability(newReportDependability);
		
		assertEquals(newReportDependability, this.dut.getReportDependability(), "Error: GasStation Entity - set Dependability issue");
	}//EndCase.
	
	@Test
	public void testReportUser() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,this.hasPremiumDiesel,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.premiumDieselPrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		assertEquals(this.reportUser.intValue(), dut.getReportUser().intValue(), "Error: GasStation Entity - get reportUser issue");
		
		Integer newUser = new Integer(this.reportUser.intValue()*2);
		this.dut.setReportUser(newUser);
		assertEquals(newUser.intValue(), this.dut.getReportUser().intValue(), "Error: GasStation Entity - set reportUser issue");
	}
	
	@Test
	public void testReportTimeStamp() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,this.hasPremiumDiesel,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.premiumDieselPrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		assertEquals(this.reportTimestamp, dut.getReportTimestamp(), "Error: GasStation Entity - get timestamp issue");
		
		String newTimestamp = (new Timestamp(System.currentTimeMillis())).toString();
		this.dut.setReportTimestamp(newTimestamp);
		
		assertEquals(newTimestamp, this.dut.getReportTimestamp() , "Error: GasStation Entity - set timestamp issue");
	}
	
	@Test
	public void testCarSharing() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,this.hasPremiumDiesel,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.premiumDieselPrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		
		assertEquals(this.carSharing, this.dut.getCarSharing(), "Error: GasStation Entity - get carSharing issue");
		
		String newCarsharing = "NewCarSharing";
		this.dut.setCarSharing(newCarsharing);
		
		assertEquals(newCarsharing, this.dut.getCarSharing(), "Error: GasStation Entity - set carSharing issue");
	}
	
	@Test
	public void testFuelTypes() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,this.hasPremiumDiesel,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.premiumDieselPrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		
		 assertTrue(this.dut.getHasDiesel(), "Error: GasStation Entity - set fuel issue");
		 assertTrue(this.dut.getHasGas(), "Error: GasStation Entity - set fuel issue");
		 assertTrue(this.dut.getHasMethane(), "Error: GasStation Entity - set fuel issue");
		 assertTrue(this.dut.getHasSuper(), "Error: GasStation Entity - set fuel issue");
		 assertTrue(this.dut.getHasSuperPlus(), "Error: GasStation Entity - set fuel issue");
		 
		 this.dut.setHasDiesel(false);
		 this.dut.setHasGas(false);
		 this.dut.setHasMethane(false);
		 this.dut.setHasSuper(false);
		 this.dut.setHasSuperPlus(false);
		 
		 assertFalse(this.dut.getHasDiesel(), "Error: GasStation Entity - get fuel issue");
		 assertFalse(this.dut.getHasGas(), "Error: GasStation Entity - get fuel issue");
		 assertFalse(this.dut.getHasMethane(), "Error: GasStation Entity - get fuel issue");
		 assertFalse(this.dut.getHasSuper(), "Error: GasStation Entity - get fuel issue");
		 assertFalse(this.dut.getHasSuperPlus(), "Error: GasStation Entity - get fuel issue");
	}
	
	@Test
	public void testCoordinates() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,this.hasPremiumDiesel,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.premiumDieselPrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		
		assertEquals(this.lat, this.dut.getLat(), "Error: GasStation Entity - get lat issue");
		assertEquals(this.lon, this.dut.getLon(), "Error: GasStation Entity - get lon issue");
		
		double newLat = this.lat*2;
		double newLon = this.lon*2;
		
		this.dut.setLat(newLat);
		this.dut.setLon(newLon);
		
		assertEquals(newLat, this.dut.getLat(), "Error: GasStation Entity - set lat issue");
		assertEquals(newLon, this.dut.getLon(), "Error: GasStation Entity - set lon issue");
	}
	
	@Test
	public void testPrices() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,this.hasPremiumDiesel,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.premiumDieselPrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		
		assertEquals(this.dieselPrice, this.dut.getDieselPrice().doubleValue(), "Error: GasStation Entity - get price issue");
		assertEquals(this.superPrice, this.dut.getSuperPrice().doubleValue(), "Error: GasStation Entity - get price issue");
		assertEquals(this.methanePrice, this.dut.getMethanePrice().doubleValue(), "Error: GasStation Entity - get price issue");
		assertEquals(this.gasPrice, this.dut.getGasPrice().doubleValue(), "Error: GasStation Entity - get price issue");
		assertEquals(this.superPlusPrice, this.dut.getSuperPlusPrice().doubleValue(), "Error: GasStation Entity - get price issue");
		assertEquals(this.premiumDieselPrice, this.dut.getPremiumDieselPrice().doubleValue(), "Error: GasStation Entity - get price issue");
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
		assertEquals(newDieselPrice, this.dut.getDieselPrice().doubleValue(), "Error: GasStation Entity - set price issue");
		assertEquals(newSuperPrice, this.dut.getSuperPrice().doubleValue(), "Error: GasStation Entity - set price issue");
		assertEquals(newMethanePrice, this.dut.getMethanePrice().doubleValue(), "Error: GasStation Entity - set price issue");
		assertEquals(newGasPrice, this.dut.getGasPrice().doubleValue(), "Error: GasStation Entity - set price issue");
		assertEquals(newSuperPlusPrice, this.dut.getSuperPlusPrice().doubleValue(), "Error: GasStation Entity - set price issue");
		assertEquals(newPremiumDieselPrice, this.dut.getPremiumDieselPrice().doubleValue(), "Error: GasStation Entity - set price issue");
	}
	
	@Test
	public void testAddress() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,this.hasPremiumDiesel,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.premiumDieselPrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		assertEquals(this.gasStationAddress, this.dut.getGasStationAddress(), "Error: GasStation Entity - get address issue");
		
		String newAddr = "New Address";
		this.dut.setGasStationAddress(newAddr);
		assertEquals(newAddr, this.dut.getGasStationAddress(), "Error: GasStation Entity - set address issue");
	}
	@Test
	public void testId() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,this.hasPremiumDiesel,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.premiumDieselPrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		Integer ID = 1;
		this.dut.setGasStationId(ID);
		assertEquals(ID.intValue(), this.dut.getGasStationId().intValue(), "Error: GasStation Entity - set/get id issue");
	}
	
	@Test 
	public void testName() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,this.hasPremiumDiesel,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.premiumDieselPrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		assertEquals(this.gasStationName, this.dut.getGasStationName(),  "Error: GasStation Entity - get name issue");
		
		String newName = "New name";
		
		this.dut.setGasStationName(newName);
		assertEquals(newName, this.dut.getGasStationName(),  "Error: GasStation Entity - set name issue");
	}
}
