package grouf.dirarya.application.controller;

import grouf.dirarya.domain.user.UserService;
import grouf.dirarya.domain.user.dto.JoinDto;
import grouf.dirarya.domain.user.dto.LoginDto;
import grouf.dirarya.domain.user.dto.UpdateUserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

  @PatchMapping
  public void updateUser(@RequestBody UpdateUserDto userDto) {
    userService.updateUser(userDto, 1L);
  }
}
