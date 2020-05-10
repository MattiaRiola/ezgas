package it.polito.ezgas.repository;

import it.polito.ezgas.entity.GasStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation, Integer> {
    @Query(value = "SELECT * FROM GAS_STATION WHERE USER_ID = ?1", nativeQuery = true)
    GasStation findById(Integer id);

    @Query(value = "SELECT * FROM GAS_STATION WHERE HAS_DIESEL = ?1 OR HAS_GAS = ?2 OR HAS_METHANE = ?3 OR HAS_SUPER = ?4 OR HAS_SUPER_PLUS = ?5", nativeQuery = true)
    List<GasStation> findByGasolineType(
            Boolean hasDiesel,
            Boolean hasGas,
            Boolean hasMethane,
            Boolean hasSuper,
            Boolean hasSuperPlus
    );

    // TODO: Do we need all these points, or we can remove some of them?

    @Query(value = "SELECT * FROM GAS_STATION WHERE LAT BETWEEN ?1 AND ?3 AND LON BETWEEN ?2 AND ?6", nativeQuery = true)
    List<GasStation> findByProximity(
            Double latUL, Double lonUL, // Upper Left lat and lon
            Double latUR, Double lonUR, // Upper Right lat and lon
            Double latLL, Double lonLL, // Lower Left lat and lon
            Double latLR, Double lonLR // Lower Right lat and lon
    );

    @Query(value = "SELECT * FROM GAS_STATION WHERE (LAT BETWEEN ?1 AND ?3 AND LON BETWEEN ?2 AND ?6) AND (HAS_DIESEL = ?9 OR HAS_GAS = ?10 OR HAS_METHANE= ?11 OR HAS_SUPER = ?12 OR HAS_SUPER_PLUS = ?13)", nativeQuery = true)
    List<GasStation> findByCoordinates(
            Double latUL, Double lonUL, // Upper Left lat and lon
            Double latUR, Double lonUR, // Upper Right lat and lon
            Double latLL, Double lonLL, // Lower Left lat and lon
            Double latLR, Double lonLR, // Lower Right lat and lon
            Boolean hasDiesel,
            Boolean hasGas,
            Boolean hasMethane,
            Boolean hasSuper,
            Boolean hasSuperPlus
    );

    @Query(value = "SELECT * FROM GAS_STATION WHERE (HAS_DIESEL = ?1 OR HAS_GAS = ?2 OR HAS_METHANE = ?3 OR HAS_SUPER = ?4 OR HAS_SUPER_PLUS = ?5) AND (CAR_SHARING = ?6)", nativeQuery = true)
    List<GasStation> findWithoutCoordinates(
            Boolean hasDiesel,
            Boolean hasGas,
            Boolean hasMethane,
            Boolean hasSuper,
            Boolean hasSuperPlus,
            String carSharing
    );

    @Query(value = "SELECT * FROM GAS_STATION WHERE (LAT BETWEEN ?1 AND ?3 AND LON BETWEEN ?2 AND ?6) AND (CAR_SHARING = ?7)", nativeQuery = true)
    List<GasStation> findByCarSharing(
            Double latUL, Double lonUL, // Upper Left lat and lon
            Double latUR, Double lonUR, // Upper Right lat and lon
            Double latLL, Double lonLL, // Lower Left lat and lon
            Double latLR, Double lonLR, // Lower Right lat and lon
            String carSharing
    );
}
