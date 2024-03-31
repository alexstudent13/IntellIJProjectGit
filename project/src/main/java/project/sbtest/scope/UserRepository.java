package project.sbtest.scope;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import project.sbtest.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserID(Long userID);
    void deleteByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.firstName = ?1, u.lastName = ?2 WHERE u.email = ?3")
    void updateUserInfoByEmail(String firstName, String lastName, String email);
}
