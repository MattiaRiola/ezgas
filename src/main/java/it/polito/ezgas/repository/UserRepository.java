package it.polito.ezgas.repository;

import it.polito.ezgas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM USER WHERE USER_ID = ?1", nativeQuery = true)
    User findById(Integer id);

    @Query(value = "SELECT * FROM USER WHERE EMAIL = ?1", nativeQuery = true)
    User findByEmail(String email);

    @Query(value = "SELECT * FROM USER WHERE USER_NAME = ?1 AND PASSWORD = ?2 AND EMAIL = ?3", nativeQuery = true)
    User findAdmin(String userName, String password, String email);
}
