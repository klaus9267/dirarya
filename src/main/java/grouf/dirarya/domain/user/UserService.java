package grouf.dirarya.domain.user;

import grouf.dirarya.application.handler.exception.BadRequestException;
import grouf.dirarya.domain.user.dto.JoinDto;
import grouf.dirarya.domain.user.dto.LoginDto;
import grouf.dirarya.domain.user.dto.UpdateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void join(final JoinDto joinDto) {
    if (userRepository.findByUidOrNickname(joinDto.uid(), joinDto.nickname()).isPresent()) {
      throw new RuntimeException("이미 가입된 사용자");
    } else {
      final String hashedPassword = passwordEncoder.encode(joinDto.password());
      final User user = User.from(joinDto, hashedPassword);

      userRepository.save(user);
    }
  }

  @Transactional
  public void login(final LoginDto loginDto) {
    final User user = this.findByUid(loginDto.uid());
    if (!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
      throw new BadRequestException("비밀번호 오류");
    }
  }

  @Transactional
  public void updateUser(UpdateUserDto userDto, final Long userId) {
    final User user = this.findById(userId);
    if (!userDto.getPassword().isEmpty()) {
      userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    }
    user.updateUser(userDto);
  }

  private User findByUid(String uid) {
    return userRepository.findByUid(uid).orElseThrow(() -> new RuntimeException("존재하지 않는 사용자" + uid));
  }

  private User findById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 사용자" + id + "번"));
  }
}
