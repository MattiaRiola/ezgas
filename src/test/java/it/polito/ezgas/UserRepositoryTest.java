package it.polito.ezgas;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private void compareUsers(User expected, User actual) {
        assertEquals("entity admin is wrong", expected.getAdmin(), actual.getAdmin());
        assertEquals("entity username is wrong", expected.getUserName(), actual.getUserName());
        assertEquals("entity email is wrong", expected.getEmail(), actual.getEmail());
        assertEquals("entity password is wrong", expected.getPassword(), actual.getPassword());
        assertEquals("entity reputation is wrong", expected.getReputation(), actual.getReputation());
    }

    @Test
    public void findByIdTest() {
        User expected = new User("Valentina Kerman", "PlanesAreNiceRocketsAreBetter", "vale.kerman@ksp.com", 5);
        expected.setAdmin(false);
        userRepository.save(expected);
        User actual = userRepository.findById(100);
        assertNull("user should have been null", actual);
        // It appears that I cannot force spring to use a particular id, even if the db is empty. To test that the
        // expected user is returned by findById I need to retrieve it through the findAll method. Ironic.
        List<User> l = userRepository.findAll();
        Integer id = l.get(1).getUserId(); // The Spring application always creates the admin account. Take the second to retrieve the test one.
        actual = userRepository.findById(id);
        compareUsers(expected, actual);
    }

    @Test
    public void findByEmailTest() {
        User expected = new User("Valentina Kerman", "PlanesAreNiceRocketsAreBetter", "vale.kerman@ksp.com", 5);
        expected.setAdmin(false);
        userRepository.save(expected);
        User actual = userRepository.findByEmail("bob.kerman@ksp.com");
        assertNull("user should have been null", actual);
        actual = userRepository.findByEmail("vale.kerman@ksp.com");
        compareUsers(expected, actual);
    }

    @Test
    public void findByAdminTest() {
        User u = new User("Bill Kerman", "KerbalSpaceProgram", "bill.kerman@ksp.com", 0);
        u.setAdmin(false);
        User expected = new User("Valentina Kerman", "PlanesAreNiceRocketsAreBetter", "vale.kerman@ksp.com", 5);
        expected.setAdmin(true);
        userRepository.save(u);
        userRepository.save(expected);
        User actual = userRepository.findAdmin("Bill Kerman", "KerbalSpaceProgram", "bill.kerman@ksp.com");
        assertNull("user should have been null", actual);
        actual = userRepository.findAdmin("Valentina Kerman", "PlanesAreNiceRocketsAreBetter", "vale.kerman@ksp.com");
        compareUsers(expected, actual);
    }
}
