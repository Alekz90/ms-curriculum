package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidPassword;
import alejdaf.commonutils.annotation.ValidUsername;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {

  @Schema(name = "UserDto.Login", description = "DTO for user login")
  public record Login(
      @NotBlank @ValidUsername @Size(max = 100) String username,
      @NotBlank @ValidPassword String password) {}

  @Schema(name = "UserDto.Register", description = "DTO for user registration")
  public record Register(
      @NotBlank @ValidUsername @Size(max = 100) String username,
      @NotBlank @ValidPassword String password,
      @NotBlank @Email @Size(max = 100) String email ) { }

  @Schema(name = "UserDto.ChangePassword", description = "DTO for changing user password")
  public record ChangePassword(
      @NotBlank @ValidIdentifier String id,
      @NotBlank @ValidPassword String oldPassword,
      @NotBlank @ValidPassword String newPassword) { }

  @Schema(name = "UserDto.RecoveryPassword", description = "DTO for recovering user password")
  public record RecoveryPassword(
      @NotBlank @ValidIdentifier String id,
      @NotBlank @ValidPassword String newPassword) { }

  @Schema(name = "UserDto.Authentication", description = "DTO for user authentication")
  public record Authentication(String token) {

    public static Authentication build(String token) {
      return new Authentication(token);
    }
  }
}
