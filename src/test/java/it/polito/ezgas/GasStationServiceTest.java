package it.polito.ezgas;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;

public class GasStationServiceTest {
	
	GasStationRepository gasRep;
	
	@BeforeEach
	public void setUp() {
		//gasRep = mock(GasStationRepository.class);
		//when(gasRep.findAll()).thenReturn(new ArrayList <GasStation>());
	}
	
}
//EndClass.