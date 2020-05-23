package it.polito.ezgas.repository;

import it.polito.ezgas.entity.GasStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation, Integer> {
    @Query(value = "SELECT * FROM GAS_STATION WHERE GAS_STATION_ID = ?1", nativeQuery = true)
    GasStation findById(Integer id);

    @Query(value = "SELECT * FROM GAS_STATION WHERE HAS_DIESEL = TRUE", nativeQuery = true)
    List<GasStation> findByDiesel();

    @Query(value = "SELECT * FROM GAS_STATION WHERE HAS_GAS = TRUE", nativeQuery = true)
    List<GasStation> findByGas();

    @Query(value = "SELECT * FROM GAS_STATION WHERE HAS_METHANE = TRUE", nativeQuery = true)
    List<GasStation> findByMethane();

    @Query(value = "SELECT * FROM GAS_STATION WHERE HAS_SUPER = TRUE", nativeQuery = true)
    List<GasStation> findBySuper();

    @Query(value = "SELECT * FROM GAS_STATION WHERE HAS_SUPER_PLUS = TRUE", nativeQuery = true)
    List<GasStation> findBySuperPlus();

    @Query(value = "SELECT * FROM GAS_STATION WHERE CAR_SHARING = ?1", nativeQuery = true)
    List<GasStation> findByCarSharing (String carSharing);

    @Query(value = "SELECT * FROM GAS_STATION WHERE HAS_DIESEL=TRUE AND CAR_SHARING = ?1", nativeQuery = true)
    List<GasStation> findByHasDieselAndCarSharing(String carSharing);

    @Query(value = "SELECT * FROM GAS_STATION WHERE HAS_GAS = TRUE AND CAR_SHARING=?1", nativeQuery = true)
    List<GasStation> findByGasAndCarSharing(String carSharing);

    @Query(value = "SELECT * FROM GAS_STATION WHERE HAS_METHANE = TRUE AND CAR_SHARING=?1", nativeQuery = true)
    List<GasStation> findByMethaneAndCarSharing(String carSharing);

    @Query(value = "SELECT * FROM GAS_STATION WHERE HAS_SUPER = TRUE AND CAR_SHARING=?1", nativeQuery = true)
    List<GasStation> findBySuperAndCarSharing(String carSharing);

    @Query(value = "SELECT * FROM GAS_STATION WHERE HAS_SUPER_PLUS = TRUE AND CAR_SHARING=?1", nativeQuery = true)
    List<GasStation> findBySuperPlusAndCarSharing(String carSharing);
}
