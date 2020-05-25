package it.polito.ezgas.converter.impl;

import it.polito.ezgas.converter.Converter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;

public class GasStationConverter implements Converter<GasStation, GasStationDto> {
    @Override
    public GasStation convertFromDto(GasStationDto gasStationDto) {
        GasStation gasStation = new GasStation(gasStationDto.getGasStationName(), gasStationDto.getGasStationAddress(),
                gasStationDto.getHasDiesel(), gasStationDto.getHasSuper(), gasStationDto.getHasSuperPlus(), gasStationDto.getHasGas(),
                gasStationDto.getHasMethane(), gasStationDto.getCarSharing(), gasStationDto.getLat(), gasStationDto.getLon(),
                gasStationDto.getDieselPrice(), gasStationDto.getSuperPrice(), gasStationDto.getSuperPlusPrice(), gasStationDto.getGasPrice(),
                gasStationDto.getMethanePrice(), gasStationDto.getReportUser(), gasStationDto.getReportTimestamp(), gasStationDto.getReportDependability());
        return gasStation;
    }

    @Override
    public GasStationDto convertToDto(GasStation gasStation) {
        GasStationDto gasStationDto = new GasStationDto(gasStation.getGasStationId(), gasStation.getGasStationName(), gasStation.getGasStationAddress(),
                gasStation.getHasDiesel(), gasStation.getHasSuper(), gasStation.getHasSuperPlus(), gasStation.getHasGas(),
                gasStation.getHasMethane(), gasStation.getCarSharing(), gasStation.getLat(), gasStation.getLon(),
                gasStation.getDieselPrice(), gasStation.getSuperPrice(), gasStation.getSuperPlusPrice(), gasStation.getGasPrice(),
                gasStation.getMethanePrice(), gasStation.getReportUser(), gasStation.getReportTimestamp(), gasStation.getReportDependability());
        return gasStationDto;
    }
}
