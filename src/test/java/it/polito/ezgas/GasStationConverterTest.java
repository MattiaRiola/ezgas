package it.polito.ezgas;

import it.polito.ezgas.converter.Converter;
import it.polito.ezgas.converter.impl.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.Assert.*;

public class GasStationConverterTest {
    @Test
    public void testGasStationToDto() {
        String timestamp = LocalDateTime.now().toString();
        LocalDateTime localDateTime = LocalDateTime.parse(timestamp);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Date date = Date.from(zonedDateTime.toInstant());
        DateFormat formatter = new SimpleDateFormat("MM-dd-YYYY");
        String formattedDate = formatter.format(date);

        GasStation gasStation = new GasStation(
        		"Minmus", "Via Kerbal Space Program",
                true, false, true, false, true, true, null,
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, timestamp, 76);
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
        assertEquals("dto timestamp is wrong", formattedDate, gasStationDto.getReportTimestamp());
        assertEquals("dto dependability is wrong", (int)76, (int)gasStationDto.getReportDependability());
    }

    @Test
    public void testGasStationFromDto() {
        String timestamp = LocalDateTime.now().toString();
        GasStationDto gasStationDto = new GasStationDto(10, "Minmus", "Via Kerbal Space Program",
                true, false, true, false, true, true, null,
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, timestamp, 76);
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
        assertEquals("entity timestamp is wrong", timestamp, gasStation.getReportTimestamp());
        assertEquals("entity dependability is wrong", (int)76, (int)gasStation.getReportDependability());
    }
}
