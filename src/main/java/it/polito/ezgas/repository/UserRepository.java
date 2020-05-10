package it.polito.ezgas.repository;

import it.polito.ezgas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM USER U WHERE U.USER_ID=?1", nativeQuery=true)
    User findById(Integer id);

    @Query(value = "SELECT * FROM USER U WHERE U.EMAIL=?1", nativeQuery=true)
    User findByEmail(String email);

    @Query(value = "UPDATE USER U SET U.REPUTATION = ?2 WHERE U.USER_ID=?1", nativeQuery=true)
    void updateUserReputation(Integer id, Integer reputation);
}
