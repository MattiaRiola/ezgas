package it.polito.ezgas;

import it.polito.ezgas.dto.PriceReportDto;
import it.polito.ezgas.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PriceReportDtoTest {
	private Integer priceReportId = new Integer(1);
	private User user = new User();
	private double dieselPrice = 1.0;
	private double superPrice = 1.0;
	private double superPlusPrice = 1.0;
	private double gasPrice = 1.0;
	
	private PriceReportDto priceReportDto;
	
	@Test
	public void testId() {
		priceReportDto = new PriceReportDto(priceReportId, user, dieselPrice, superPrice,
				superPlusPrice, gasPrice);
		assertEquals(priceReportId.intValue(), priceReportDto.getPriceReportId().intValue());
		priceReportDto.setPriceReportId(new Integer(2));
		assertEquals(2, priceReportDto.getPriceReportId().intValue());
	}//EndTest.
	
	@Test
	public void testUser() {
		priceReportDto = new PriceReportDto(priceReportId, user, dieselPrice, superPrice,
				superPlusPrice, gasPrice);
		User newUser = new User();
		newUser.setUserId(1);
		priceReportDto.setUser(newUser);
		assertEquals(newUser.getUserId().intValue(), priceReportDto.getUser().getUserId().intValue());
		
	}//EndTest.
	
	@Test 
	public void testPrice() {
		priceReportDto = new PriceReportDto(priceReportId, user, dieselPrice, superPrice,
				superPlusPrice, gasPrice);
		assertEquals(this.dieselPrice, priceReportDto.getDieselPrice());
		assertEquals(this.superPrice, priceReportDto.getSuperPrice());
		assertEquals(this.gasPrice, priceReportDto.getGasPrice());
		assertEquals(this.superPlusPrice, priceReportDto.getSuperPlusPrice());
		
		double newDieselPrice = this.dieselPrice*2;
		double newSuperPrice = this.superPrice*2;
		double newGasPrice = this.gasPrice*2;
		double newSuperPlusPrice = this.superPlusPrice*2;
		
		priceReportDto.setDieselPrice(newDieselPrice);
		priceReportDto.setSuperPrice(newSuperPrice);
		priceReportDto.setGasPrice(newGasPrice);
		priceReportDto.setSuperPlusPrice(newSuperPlusPrice);
		
		assertEquals(newDieselPrice, priceReportDto.getDieselPrice());
		assertEquals(newSuperPrice, priceReportDto.getSuperPrice());
		assertEquals(newGasPrice, priceReportDto.getGasPrice());
		assertEquals(newSuperPlusPrice, priceReportDto.getSuperPlusPrice());
	}//EndTest.
}
