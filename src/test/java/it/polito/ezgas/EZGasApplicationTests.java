package it.polito.ezgas;

import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import org.junit.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) 
@SpringBootTest
@SelectClasses({GasStationEntityTest.class , UserEntityTest.class, HaversineTest.class, GasStationConverterTest.class, UserConverterTest.class, GasStationRepository.class, UserRepository.class, UserServiceTest.class, GasStationServiceTestMock.class})
public class EZGasApplicationTests {	
	
	@Test
	public void contextLoads() {
	}

}
