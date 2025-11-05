package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidPassword;
import alejdaf.commonutils.annotation.ValidUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static alkz.mscurriculum.util.Constants.*;

public class UserDto {

  public record Login(
      @NotBlank @ValidUsername @Size(max = 100) String username,
      @NotBlank @ValidPassword String password) {}

  public record Register(
      @NotBlank @ValidUsername @Size(max = 100) String username,
      @NotBlank @ValidPassword String password,
      @NotBlank @Email @Size(max = 100) String email ) { }

  public record ChangePassword(
      @NotBlank @ValidIdentifier String id,
      @NotBlank @ValidPassword String oldPassword,
      @NotBlank @ValidPassword String newPassword) { }

  public record RecoveryPassword(
      @NotBlank @ValidIdentifier String id,
      @NotBlank @ValidPassword String newPassword) { }

  public record Authentication(String token) {

    public static Authentication build(String token) {
      return new Authentication(token);
    }
  }
}
