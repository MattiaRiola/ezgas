package it.polito.ezgas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootEZGasApplication {
	@Autowired
	UserRepository userRepository;

	@Autowired
	GasStationRepository gr;

	// Store the connection to the DB
	private Connection conn;

	public static void main(String[] args) {
		SpringApplication.run(BootEZGasApplication.class, args);
	}
	
	@PostConstruct
	public void setupDbWithData() throws SQLException{
		conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");

		User admin = userRepository.findAdmin("admin", "admin", "admin@ezgas.com");
		// If the database doesn't contain an admin create it
		if (admin == null) {
			admin = new User("admin", "admin", "admin@ezgas.com", 5);
			admin.setAdmin(true);
			userRepository.save(admin);
		}

		//conn.close();
	}


	@PreDestroy
	public void closeDB() throws SQLException {
		try {
			conn.close();
			System.out.println("DB closed successfully");
		} catch (Exception e) {
			System.err.println("Error while closing the DB: " + e.getMessage());
		}
	}
}
