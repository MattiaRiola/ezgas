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
    private String carSharing = "TestCarSharing";
    private double lat = 45.0;
    private double lon = 45.0;
    private double dieselPrice = 1.2;
    private double superPrice = 1.7;
    private double superPlusPrice = 1.8;
    private double gasPrice = 1.6;
    private double methanePrice = 1.3;
    private Integer reportUser = 1;
    private String reportTimestamp = (new Timestamp(System.currentTimeMillis())).toString();
    private double reportDependability = 1.0;
    
	private GasStation dut;
	//= new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.reportUser,this.reportTimestamp,this.reportDependability);
	
	@Test
	public void testReportDependability() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		
		assertEquals(this.reportDependability, dut.getReportDependability()); 
		
		double newReportDependability = this.reportDependability*2;
		this.dut.setReportDependability(newReportDependability);
		
		assertEquals(newReportDependability, this.dut.getReportDependability());
	}//EndCase.
	
	@Test
	public void testReportUser() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		assertEquals(this.reportUser, dut.getReportUser());
		
		Integer newUser = new Integer(this.reportUser.intValue()*2);
		this.dut.setReportUser(newUser);
		assertEquals(newUser, this.dut.getReportUser());
	}
	
	@Test
	public void testReportTimeStamp() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		assertEquals(this.reportTimestamp, dut.getReportTimestamp());
		
		String newTimestamp = (new Timestamp(System.currentTimeMillis())).toString();
		this.dut.setReportTimestamp(newTimestamp);
		
		assertEquals(newTimestamp, this.dut.getReportTimestamp());
	}
	
	@Test
	public void testCarSharing() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		
		assertEquals(this.carSharing, this.dut.getCarSharing());
		
		String newCarsharing = "NewCarSharing";
		this.dut.setCarSharing(newCarsharing);
		
		assertEquals(newCarsharing, this.dut.getCarSharing());
	}
	
	@Test
	public void testFuelTypes() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		
		 assertTrue(this.dut.getHasDiesel());
		 assertTrue(this.dut.getHasGas());
		 assertTrue(this.dut.getHasMethane());
		 assertTrue(this.dut.getHasSuper());
		 assertTrue(this.dut.getHasSuperPlus());
		 
		 this.dut.setHasDiesel(false);
		 this.dut.setHasGas(false);
		 this.dut.setHasMethane(false);
		 this.dut.setHasSuper(false);
		 this.dut.setHasSuperPlus(false);
		 
		 assertFalse(this.dut.getHasDiesel());
		 assertFalse(this.dut.getHasGas());
		 assertFalse(this.dut.getHasMethane());
		 assertFalse(this.dut.getHasSuper());
		 assertFalse(this.dut.getHasSuperPlus());
	}
	
	@Test
	public void testCoordinates() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		
		assertEquals(this.lat, this.dut.getLat());
		assertEquals(this.lon, this.dut.getLon());
		
		double newLat = this.lat*2;
		double newLon = this.lon*2;
		
		this.dut.setLat(newLat);
		this.dut.setLon(newLon);
		
		assertEquals(newLat, this.dut.getLat());
		assertEquals(newLon, this.dut.getLon());
	}
	
	@Test
	public void testPrices() {
		this.dut = new GasStation (this.gasStationName,this.gasStationAddress,this.hasDiesel,this.hasSuper,this.hasSuperPlus,this.hasGas,this.hasMethane,carSharing,this.lat,this.lon,this.dieselPrice,this.superPrice,this.superPlusPrice,this.gasPrice,this.methanePrice,this.reportUser,this.reportTimestamp,this.reportDependability);
		
		assertEquals(this.dieselPrice, this.dut.getDieselPrice());
		assertEquals(this.superPrice, this.dut.getSuperPrice());
		assertEquals(this.methanePrice, this.dut.getMethanePrice());
		assertEquals(this.gasPrice, this.dut.getGasPrice());
		assertEquals(this.superPlusPrice, this.dut.getSuperPlusPrice());
		
		double newDieselPrice = this.dieselPrice*2;
		double newSuperPrice = this.superPrice*2;
		double newMethanePrice = this.methanePrice*2;
		double newGasPrice = this.gasPrice*2;
		double newSuperPlusPrice = this.superPlusPrice*2;
		
		this.dut.setDieselPrice(newDieselPrice);
		this.dut.setSuperPrice(newSuperPrice);
		this.dut.setMethanePrice(newMethanePrice);
		this.dut.setGasPrice(newGasPrice);
		this.dut.setSuperPlusPrice(newSuperPlusPrice);
		
		assertEquals(newDieselPrice, this.dut.getDieselPrice());
		assertEquals(newSuperPrice, this.dut.getSuperPrice());
		assertEquals(newMethanePrice, this.dut.getMethanePrice());
		assertEquals(newGasPrice, this.dut.getGasPrice());
		assertEquals(newSuperPlusPrice, this.dut.getSuperPlusPrice());
	}
}
