package grouf.dirarya.domain.user;

import grouf.dirarya.domain.common.TimeEntity;
import grouf.dirarya.domain.user.dto.JoinDto;
import grouf.dirarya.domain.user.dto.UpdateUserDto;
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
public class User extends TimeEntity {
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

  public void updateUser(final UpdateUserDto updateUserDto) {
    this.nickname = updateUserDto.getNickname().isEmpty() ? this.nickname : updateUserDto.getNickname();
    this.password = updateUserDto.getPassword().isEmpty() ? this.password : updateUserDto.getPassword();
  }
}
