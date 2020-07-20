package it.polito.ezgas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.PriceReportDto;

public class PriceReportDtoTest {
    Integer gasStationId = new Integer(1) ;
    Double dieselPrice = new Double (1.0);
    Double superPrice = new Double (1.0);
    Double superPlusPrice = new Double (1.0);
    Double gasPrice = new Double (1.0);
    Double methanePrice = new Double (1.0);
    Double premiumDieselPrice = 1.0;
    Integer userId = new Integer(2);
    
    PriceReportDto dut;
    
    @Test
	public void testId() {
		this.dut = new PriceReportDto(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice, userId); 
		Integer ID = 2;
		this.dut.setGasStationId(ID);
		assertEquals(ID.intValue(), this.dut.getGasStationId().intValue(), "Error: Price report dto - set/get id issue");
		
		this.dut.setUserId(ID);;
		assertEquals(ID.intValue(), this.dut.getUserId().intValue(), "Error: Price report dto dto - set/get id issue");
	}//EndTest.
    
    @Test
	public void testPrices() {
    	this.dut = new PriceReportDto(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, premiumDieselPrice, userId); 
		
		assertEquals(this.dieselPrice.doubleValue(), this.dut.getDieselPrice().doubleValue(), "Error: Price report dto - get price issue");
		assertEquals(this.superPrice.doubleValue(), this.dut.getSuperPrice().doubleValue(), "Error: Price report dto - get price issue");
		assertEquals(this.methanePrice.doubleValue(), this.dut.getMethanePrice().doubleValue(), "Error: Price report dto - get price issue");
		assertEquals(this.gasPrice.doubleValue(), this.dut.getGasPrice().doubleValue(), "Error: Price report dto - get price issue");
		assertEquals(this.superPlusPrice.doubleValue(), this.dut.getSuperPlusPrice().doubleValue(), "Error: Price report dto - get price issue");
		assertEquals(this.premiumDieselPrice.doubleValue(), this.dut.getPremiumDieselPrice().doubleValue(), "Error: Price report dto - get price issue");
		
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
		
		assertEquals(newDieselPrice, this.dut.getDieselPrice().doubleValue(), "Error: Price report dto - set price issue");
		assertEquals(newSuperPrice, this.dut.getSuperPrice().doubleValue(), "Error: Price report dto - set price issue");
		assertEquals(newMethanePrice, this.dut.getMethanePrice().doubleValue(), "Error: Price report dto - set price issue");
		assertEquals(newGasPrice, this.dut.getGasPrice().doubleValue(), "Error: Price report dto - set price issue");
		assertEquals(newSuperPlusPrice, this.dut.getSuperPlusPrice().doubleValue(), "Error: Price report dto - set price issue");
		assertEquals(newPremiumDieselPrice, this.dut.getPremiumDieselPrice().doubleValue(), "Error: Price report dto - get price issue");
	}//EndTest.
}
