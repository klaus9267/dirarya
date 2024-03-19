package grouf.dirarya.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUidOrNickname(String uid, String nickname);

  Optional<User> findByUid(String uid);
}
