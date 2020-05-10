package it.polito.ezgas.repository;

import it.polito.ezgas.entity.GasStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation, Integer> {
    @Query(value = "", nativeQuery = true)
    GasStation findById(Integer id);

    @Query(value = "", nativeQuery = true)
    List<GasStation> findByGasolineType(
            Boolean hasDiesel,
            Boolean hasGas,
            Boolean hasMethane,
            Boolean hasSuper,
            Boolean hasSuperPlus
    );

    @Query(value = "", nativeQuery = true)
    List<GasStation> findByProximity(
            Double latUL, Double lonUL, // Upper Left lat and lon
            Double latUR, Double lonUR, // Upper Right lat and lon
            Double latLL, Double lonLL, // Lower Left lat and lon
            Double latLR, Double lonLR // Lower Right lat and lon
    );

    @Query(value = "", nativeQuery = true)
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

    @Query(value = "", nativeQuery = true)
    List<GasStation> findWithoutCoordinates(
            Boolean hasDiesel,
            Boolean hasGas,
            Boolean hasMethane,
            Boolean hasSuper,
            Boolean hasSuperPlus,
            String carSharing
    );

    @Query(value = "", nativeQuery = true)
    List<GasStation> findByCarSharing(String carSharing);
}
