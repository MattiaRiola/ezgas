package it.polito.ezgas;

import it.polito.ezgas.converter.Converter;
import it.polito.ezgas.converter.impl.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals((Integer)1, gasStationDto.getGasStationId());
        assertEquals("Minmus", gasStationDto.getGasStationName(), "dto name is wrong");
        assertEquals("Via Kerbal Space Program", gasStationDto.getGasStationAddress(), "dto address is wrong");
        assertTrue(gasStationDto.getHasDiesel(), "dto hasdiesel is wrong");
        assertFalse(gasStationDto.getHasSuper(), "dto hassuper is wrong");
        assertTrue(gasStationDto.getHasSuperPlus(), "dto hassuperplus is wrong");
        assertFalse(gasStationDto.getHasGas(), "dto hasgas is wrong");
        assertTrue(gasStationDto.getHasMethane(), "dto hasmethane is wrong");
        assertNull(gasStationDto.getCarSharing(), "dto carsharing is wrong");
        assertEquals(10.32, gasStationDto.getLat(), "dto latitude is wrong");
        assertEquals(-26.11, gasStationDto.getLon(), "dto longitude is wrong");
        assertEquals(1.1, gasStationDto.getDieselPrice(), "dto diesel price is wrong");
        assertEquals(-1, gasStationDto.getSuperPrice(), "dto super price is wrong");
        assertEquals(1.2, gasStationDto.getSuperPlusPrice(), "dto superplus price is wrong");
        assertEquals(-1, gasStationDto.getGasPrice(), "dto gas price is wrong");
        assertEquals(1.3, gasStationDto.getMethanePrice(), "dto methane price is wrong");
        assertEquals("10/12/20", gasStationDto.getReportTimestamp(), "dto timestamp is wrong");
        assertEquals(76, gasStationDto.getReportDependability(), "dto dependability is wrong");
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
        assertEquals("Minmus", gasStation.getGasStationName(), "entity name is wrong");
        assertEquals("Via Kerbal Space Program", gasStation.getGasStationAddress(), "entity address is wrong");
        assertTrue(gasStation.getHasDiesel(), "entity hasdiesel is wrong");
        assertFalse(gasStation.getHasSuper(), "entity hassuper is wrong");
        assertTrue(gasStation.getHasSuperPlus(), "entity hassuperplus is wrong");
        assertFalse(gasStation.getHasGas(), "entity hasgas is wrong");
        assertTrue(gasStation.getHasMethane(), "entity hasmethane is wrong");
        assertNull(gasStation.getCarSharing(), "entity carsharing is wrong");
        assertEquals(10.32, gasStation.getLat(), "entity latitude is wrong");
        assertEquals(-26.11, gasStation.getLon(), "entity longitude is wrong");
        assertEquals(1.1, gasStation.getDieselPrice(), "entity diesel price is wrong");
        assertEquals(-1, gasStation.getSuperPrice(), "entity super price is wrong");
        assertEquals(1.2, gasStation.getSuperPlusPrice(), "entity superplus price is wrong");
        assertEquals(-1, gasStation.getGasPrice(), "entity gas price is wrong");
        assertEquals(1.3, gasStation.getMethanePrice(), "entity methane price is wrong");
        assertEquals("10/12/20", gasStation.getReportTimestamp(), "entity timestamp is wrong");
        assertEquals(76, gasStation.getReportDependability(), "entity dependability is wrong");
    }
}
