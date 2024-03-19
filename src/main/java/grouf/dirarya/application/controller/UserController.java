package grouf.dirarya.application.controller;

import grouf.dirarya.domain.user.UserService;
import grouf.dirarya.domain.user.dto.JoinDto;
import grouf.dirarya.domain.user.dto.LoginDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
  private final UserService userService;

  @PostMapping
  public void join(@RequestBody @Valid final JoinDto joinDto) {
    userService.join(joinDto);
  }

  @PostMapping("login")
  public void login(@RequestBody @Valid final LoginDto loginDto) {
    userService.login(loginDto);
  }
}
