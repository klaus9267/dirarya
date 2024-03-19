package grouf.dirarya.domain.user;

import grouf.dirarya.application.handler.exception.BadRequestException;
import grouf.dirarya.domain.user.dto.JoinDto;
import grouf.dirarya.domain.user.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public void join(final JoinDto joinDto) {
    if (userRepository.findByUidOrNickname(joinDto.uid(), joinDto.nickname()).isPresent()) {
      throw new RuntimeException("이미 가입된 사용자");
    } else {
      final String hashedPassword = passwordEncoder.encode(joinDto.password());
      final User user = User.from(joinDto, hashedPassword);

      userRepository.save(user);
    }
  }

  public void login(final LoginDto loginDto) {
    final User user = this.findByUId(loginDto.uid());
    if (!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
      throw new BadRequestException("비밀번호 오류");
    }
  }

  private User findByUId(String uid) {
    return userRepository.findByUid(uid).orElseThrow(() -> new RuntimeException("존재하지 않는 사용자" + uid));
  }
}
