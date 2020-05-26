package it.polito.ezgas;

import it.polito.ezgas.converter.Converter;
import it.polito.ezgas.converter.impl.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import org.junit.Test;
import static org.junit.Assert.*;

public class GasStationConverterTest {
    @Test
    public void testGasStationToDto() {
        GasStation gasStation = new GasStation("Minmus", "Via Kerbal Space Program",
                true, false, true, false, true, null,
                10.32, -26.11, 1.1, -1, 1.2, -1, 1.3,
                5, "10/12/20", 76);
        gasStation.setGasStationId(1);
        Converter<GasStation, GasStationDto> converter = new GasStationConverter();
        GasStationDto gasStationDto = converter.convertToDto(gasStation);
        assertEquals("dto id is wrong", 1, (int)gasStationDto.getGasStationId());
        assertEquals("dto name is wrong", "Minmus", gasStationDto.getGasStationName());
        assertEquals("dto address is wrong", "Via Kerbal Space Program", gasStationDto.getGasStationAddress());
        assertTrue("dto hasdiesel is wrong", gasStationDto.getHasDiesel());
        assertFalse("dto hassuper is wrong", gasStationDto.getHasSuper());
        assertTrue("dto hassuperplus is wrong", gasStationDto.getHasSuperPlus());
        assertFalse("dto hasgas is wrong", gasStationDto.getHasGas());
        assertTrue("dto hasmethane is wrong", gasStationDto.getHasMethane());
        assertNull("dto carsharing is wrong", gasStationDto.getCarSharing());
        assertEquals("dto latitude is wrong", 10.32, gasStationDto.getLat(), 0.0);
        assertEquals("dto longitude is wrong", -26.11, gasStationDto.getLon(), 0.0);
        assertEquals("dto diesel price is wrong", 1.1, gasStationDto.getDieselPrice(), 0.0);
        assertEquals("dto super price is wrong", -1, gasStationDto.getSuperPrice(), 0.0);
        assertEquals("dto superplus price is wrong", 1.2, gasStationDto.getSuperPlusPrice(), 0.0);
        assertEquals("dto gas price is wrong", -1, gasStationDto.getGasPrice(), 0.0);
        assertEquals("dto methane price is wrong", 1.3, gasStationDto.getMethanePrice(), 0.0);
        assertEquals("dto timestamp is wrong", "10/12/20", gasStationDto.getReportTimestamp());
        assertEquals("dto dependability is wrong", (int)76, (int)gasStationDto.getReportDependability());
    }

    @Test
    public void testGasStationFromDto() {
        GasStationDto gasStationDto = new GasStationDto(10, "Minmus", "Via Kerbal Space Program",
                true, false, true, false, true, null,
                10.32, -26.11, 1.1, -1, 1.2, -1, 1.3,
                5, "10/12/20", 76);
        Converter<GasStation, GasStationDto> converter = new GasStationConverter();
        GasStation gasStation = converter.convertFromDto(gasStationDto);
        assertEquals((Integer)10, gasStation.getGasStationId());
        assertEquals("entity name is wrong", "Minmus", gasStation.getGasStationName());
        assertEquals("entity address is wrong", "Via Kerbal Space Program", gasStation.getGasStationAddress());
        assertTrue("entity hasdiesel is wrong", gasStation.getHasDiesel());
        assertFalse("entity hassuper is wrong", gasStation.getHasSuper());
        assertTrue("entity hassuperplus is wrong", gasStation.getHasSuperPlus());
        assertFalse("entity hasgas is wrong", gasStation.getHasGas());
        assertTrue("entity hasmethane is wrong", gasStation.getHasMethane());
        assertNull("entity carsharing is wrong", gasStation.getCarSharing());
        assertEquals("entity latitude is wrong", 10.32, gasStation.getLat(), 0.0);
        assertEquals("entity longitude is wrong", -26.11, gasStation.getLon(), 0.0);
        assertEquals("entity diesel price is wrong", 1.1, gasStation.getDieselPrice(), 0.0);
        assertEquals("entity super price is wrong", -1, gasStation.getSuperPrice(), 0.0);
        assertEquals("entity superplus price is wrong", 1.2, gasStation.getSuperPlusPrice(), 0.0);
        assertEquals("entity gas price is wrong", -1, gasStation.getGasPrice(), 0.0);
        assertEquals("entity methane price is wrong", 1.3, gasStation.getMethanePrice(), 0.0);
        assertEquals("entity timestamp is wrong", "10/12/20", gasStation.getReportTimestamp());
        assertEquals("entity dependability is wrong", (int)76, (int)gasStation.getReportDependability());
    }
}
