package grouf.dirarya.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record JoinDto(
    @NotBlank
    String uid,
    @NotBlank
    String password,
    @NotBlank
    String nickname
) {
}
