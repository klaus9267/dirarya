package grouf.dirarya.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UpdateUserDto {
  private String nickname;
  private String password;
}
