package grouf.dirarya.domain.user;

import grouf.dirarya.domain.user.dto.JoinDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String uid, password, nickname;

  public static User from(JoinDto joinDto, String hashedPassword) {
    return User.builder()
        .uid(joinDto.uid())
        .password(hashedPassword)
        .nickname(joinDto.nickname())
        .build();
  }
}
