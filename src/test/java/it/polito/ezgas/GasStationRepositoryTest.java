package it.polito.ezgas;

import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GasStationRepositoryTest {
    @Autowired
    private GasStationRepository gasStationRepository;

    private void compareGasStations(GasStation expected, GasStation actual) {
        assertEquals("entity name is wrong", actual.getGasStationName(), expected.getGasStationName());
        assertEquals("entity address is wrong", actual.getGasStationAddress(), expected.getGasStationAddress());
        assertEquals("entity hasdiesel is wrong", actual.getHasDiesel(), expected.getHasDiesel());
        assertEquals("entity hassuper is wrong", actual.getHasSuper(), expected.getHasSuper());
        assertEquals("entity hassuperplus is wrong", actual.getHasSuperPlus(), expected.getHasSuperPlus());
        assertEquals("entity hasgas is wrong", actual.getHasGas(), expected.getHasGas());
        assertEquals("entity hasmethane is wrong", actual.getHasMethane(), expected.getHasMethane());
        assertEquals("entity carsharing is wrong", actual.getCarSharing(), expected.getCarSharing());
        assertEquals("entity latitude is wrong", actual.getLat(), expected.getLat(), 0.0);
        assertEquals("entity longitude is wrong", actual.getLon(), expected.getLon(), 0.0);
        assertEquals( "entity diesel price is wrong", actual.getDieselPrice(), expected.getDieselPrice(), 0.0);
        assertEquals("entity super price is wrong", actual.getSuperPrice(), expected.getSuperPrice(), 0.0);
        assertEquals("entity superplus price is wrong", actual.getSuperPlusPrice(), expected.getSuperPlusPrice(), 0.0);
        assertEquals("entity gas price is wrong", actual.getGasPrice(), expected.getGasPrice(), 0.0);
        assertEquals("entity methane price is wrong", actual.getMethanePrice(), expected.getMethanePrice(), 0.0);
        assertEquals("entity timestamp is wrong", actual.getReportTimestamp(), expected.getReportTimestamp());
        assertEquals("entity dependability is wrong", actual.getReportDependability(), expected.getReportDependability(), 0.0);
    }

    @Test
    public void findByIdTest() {
        GasStation expected = new GasStation("Not so much eco friendly", "Via",
                true, false, true, false, true, true, "Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        GasStation gasStation = gasStationRepository.findById(54);
        // It appears that I cannot force spring to use a particular id, even if the db is empty. To test that the
        // expected gas station is returned by findById I need to retrieve it through the findAll method. Ironic.
        assertNull(gasStation);
        List<GasStation> l = gasStationRepository.findAll();
        Integer id = l.get(0).getGasStationId();
        gasStation = gasStationRepository.findById(id);
        compareGasStations(expected, gasStation);
    }

    @Test
    public void findByDieselTest() {
        GasStation expected = new GasStation("Not so much eco friendly", "Via",
                false, false, true, false, true, true,"Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        List<GasStation> gasStationList = gasStationRepository.findByDiesel();
        assertEquals("gas station should have been empty", 0, gasStationList.size());

        expected = new GasStation("Not so much eco friendly", "Via",
                true, false, true, false, true,true, "Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        gasStationList = gasStationRepository.findByDiesel();
        assertEquals( "gas station should be 1 in size", 1, gasStationList.size());
        compareGasStations(expected, gasStationList.get(0));
    }

    @Test
    public void findBySuperTest() {
        GasStation expected = new GasStation("Not so much eco friendly", "Via",
                false, false, true, false, true,true, "Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        List<GasStation> gasStationList = gasStationRepository.findBySuper();
        assertEquals("gas station should have been empty", 0, gasStationList.size());

        expected = new GasStation("Not so much eco friendly", "Via",
                false, true, true, false, true,true, "Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        gasStationList = gasStationRepository.findBySuper();
        assertEquals( "gas station should be 1 in size", 1, gasStationList.size());
        compareGasStations(expected, gasStationList.get(0));
    }

    @Test
    public void findBySuperPlusTest() {
        GasStation expected = new GasStation("Not so much eco friendly", "Via",
                false, true, false, false, true, true,"Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        List<GasStation> gasStationList = gasStationRepository.findBySuperPlus();
        assertEquals("gas station should have been empty", 0, gasStationList.size());

        expected = new GasStation("Not so much eco friendly", "Via",
                false, true, true, false, true, true,"Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        gasStationList = gasStationRepository.findBySuperPlus();
        assertEquals( "gas station should be 1 in size", 1, gasStationList.size());
        compareGasStations(expected, gasStationList.get(0));
    }

    @Test
    public void findByGasTest() {
        GasStation expected = new GasStation("Not so much eco friendly", "Via",
                false, true, false, false, true, true, "Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        List<GasStation> gasStationList = gasStationRepository.findByGas();
        assertEquals("gas station should have been empty", 0, gasStationList.size());

        expected = new GasStation("Not so much eco friendly", "Via",
                false, true, true, true, true,true, "Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        gasStationList = gasStationRepository.findByGas();
        assertEquals( "gas station should be 1 in size", 1, gasStationList.size());
        compareGasStations(expected, gasStationList.get(0));
    }

    @Test
    public void findByMethaneTest() {
        GasStation expected = new GasStation("Not so much eco friendly", "Via",
                false, true, false, false, false, true,"Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        List<GasStation> gasStationList = gasStationRepository.findByMethane();
        assertEquals("gas station should have been empty", 0, gasStationList.size());

        expected = new GasStation("Not so much eco friendly", "Via",
                false, true, true, true, true, true,"Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        gasStationList = gasStationRepository.findByMethane();
        assertEquals( "gas station should be 1 in size", 1, gasStationList.size());
        compareGasStations(expected, gasStationList.get(0));
    }

    @Test
    public void findByDieselAndCarSharingTest() {
        GasStation expected = new GasStation("Not so much eco friendly", "Via",
                true, true, true, true, true,true, null,
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        List<GasStation> gasStationList = gasStationRepository.findByHasDieselAndCarSharing("Enjoy");
        assertEquals("gas station should have been empty", 0, gasStationList.size());

        expected = new GasStation("Not so much eco friendly 2", "Via",
                true, true, true, true, true,true, "Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        gasStationList = gasStationRepository.findByHasDieselAndCarSharing("Car2Go");
        assertEquals( "gas station should be 1 in size", 1, gasStationList.size());
        compareGasStations(expected, gasStationList.get(0));
    }

    @Test
    public void findBySuperAndCarSharingTest() {
        GasStation expected = new GasStation("Not so much eco friendly", "Via",
                true, true, true, true, true,true, null,
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        List<GasStation> gasStationList = gasStationRepository.findBySuperAndCarSharing("Enjoy");
        assertEquals("gas station should have been empty", 0, gasStationList.size());

        expected = new GasStation("Not so much eco friendly 2", "Via",
                true, true, true, true, true,true, "Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        gasStationList = gasStationRepository.findBySuperAndCarSharing("Car2Go");
        assertEquals( "gas station should be 1 in size", 1, gasStationList.size());
        compareGasStations(expected, gasStationList.get(0));
    }

    @Test
    public void findBySuperPlusAndCarSharingTest() {
        GasStation expected = new GasStation("Not so much eco friendly", "Via",
                true, true, true, true, true,true, null,
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        List<GasStation> gasStationList = gasStationRepository.findBySuperPlusAndCarSharing("Enjoy");
        assertEquals("gas station should have been empty", 0, gasStationList.size());

        expected = new GasStation("Not so much eco friendly 2", "Via",
                true, true, true, true, true,true, "Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        gasStationList = gasStationRepository.findBySuperPlusAndCarSharing("Car2Go");
        assertEquals( "gas station should be 1 in size", 1, gasStationList.size());
        compareGasStations(expected, gasStationList.get(0));
    }

    @Test
    public void findByGasAndCarSharingTest() {
        GasStation expected = new GasStation("Not so much eco friendly", "Via",
                true, true, true, true, true,true, null,
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        List<GasStation> gasStationList = gasStationRepository.findByGasAndCarSharing("Enjoy");
        assertEquals("gas station should have been empty", 0, gasStationList.size());

        expected = new GasStation("Not so much eco friendly 2", "Via",
                true, true, true, true, true,true, "Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        gasStationList = gasStationRepository.findByGasAndCarSharing("Car2Go");
        assertEquals( "gas station should be 1 in size", 1, gasStationList.size());
        compareGasStations(expected, gasStationList.get(0));
    }

    @Test
    public void findByMethaneAndCarSharingTest() {
        GasStation expected = new GasStation("Not so much eco friendly", "Via",
        		true, true, true, true, true,true, null,
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3, 1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        List<GasStation> gasStationList = gasStationRepository.findByMethaneAndCarSharing("Enjoy");
        assertEquals("gas station should have been empty", 0, gasStationList.size());

        expected = new GasStation("Not so much eco friendly 2", "Via",
                true, true, true, true, true,true, "Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        gasStationList = gasStationRepository.findByMethaneAndCarSharing("Car2Go");
        assertEquals( "gas station should be 1 in size", 1, gasStationList.size());
        compareGasStations(expected, gasStationList.get(0));
    }

    @Test
    public void findByCarSharingTest() {
        GasStation expected = new GasStation("Not so much eco friendly", "Via",
                true, true, true, true, true,true, null,
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        List<GasStation> gasStationList = gasStationRepository.findByCarSharing("Enjoy");
        assertEquals("gas station should have been empty", 0, gasStationList.size());

        expected = new GasStation("Not so much eco friendly 2", "Via",
                true, true, true, true, true, true,"Car2Go",
                10.32, -26.11, 1.1, -1.0, 1.2, -1.0, 1.3,1.4,
                5, "10/12/20", 76);
        gasStationRepository.save(expected);
        gasStationList = gasStationRepository.findByCarSharing("Car2Go");
        assertEquals( "gas station should be 1 in size", 1, gasStationList.size());
        compareGasStations(expected, gasStationList.get(0));
    }
}
