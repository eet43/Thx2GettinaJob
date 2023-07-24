package KHOneTop.Thx2GettinaJob.user.repository;

import KHOneTop.Thx2GettinaJob.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(Long userId);
    boolean existsByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
}
