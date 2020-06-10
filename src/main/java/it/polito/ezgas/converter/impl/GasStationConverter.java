package it.polito.ezgas.converter.impl;

import it.polito.ezgas.converter.Converter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class GasStationConverter implements Converter<GasStation, GasStationDto> {
    
    private UserConverter converter = new UserConverter();
    private DateFormat formatter = new SimpleDateFormat("MM-dd-YYYY");
    
    @Override
    public GasStation convertFromDto(GasStationDto gasStationDto) {
        GasStation gasStation = new GasStation(gasStationDto.getGasStationName(), gasStationDto.getGasStationAddress(),
                gasStationDto.getHasDiesel(), gasStationDto.getHasSuper(), gasStationDto.getHasSuperPlus(), gasStationDto.getHasGas(),
                gasStationDto.getHasMethane(), gasStationDto.getCarSharing(), gasStationDto.getLat(), gasStationDto.getLon(),
                gasStationDto.getDieselPrice(), gasStationDto.getSuperPrice(), gasStationDto.getSuperPlusPrice(), gasStationDto.getGasPrice(),
                gasStationDto.getMethanePrice(), gasStationDto.getReportUser(), gasStationDto.getReportTimestamp(), gasStationDto.getReportDependability());
        gasStation.setGasStationId(gasStationDto.getGasStationId());
        if (gasStationDto.getUserDto() !=  null)
            gasStation.setUser(converter.convertFromDto(gasStationDto.getUserDto()));
        return gasStation;
    }

    @Override
    public GasStationDto convertToDto(GasStation gasStation) {
        String formattedDate = null;
        if (gasStation.getReportTimestamp() != null && !gasStation.getReportTimestamp().isEmpty()) {
            LocalDateTime localDateTime = LocalDateTime.parse(gasStation.getReportTimestamp());
            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
            Date date = Date.from(zonedDateTime.toInstant());
            formattedDate = formatter.format(date);
        }

        GasStationDto gasStationDto = new GasStationDto(gasStation.getGasStationId(), gasStation.getGasStationName(), gasStation.getGasStationAddress(),
                gasStation.getHasDiesel(), gasStation.getHasSuper(), gasStation.getHasSuperPlus(), gasStation.getHasGas(),
                gasStation.getHasMethane(), gasStation.getCarSharing(), gasStation.getLat(), gasStation.getLon(),
                gasStation.getDieselPrice(), gasStation.getSuperPrice(), gasStation.getSuperPlusPrice(), gasStation.getGasPrice(),
                gasStation.getMethanePrice(), gasStation.getReportUser(), formattedDate, gasStation.getReportDependability());
        if (gasStation.getUser() !=  null)
            gasStationDto.setUserDto(converter.convertToDto(gasStation.getUser()));
        return gasStationDto;
    }
}