package az.atl.auto_mart.repository;

import az.atl.auto_mart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Query("DELETE FROM User u WHERE u.isEnabled = false")
    void deleteNotVerifiedUsers();
}
