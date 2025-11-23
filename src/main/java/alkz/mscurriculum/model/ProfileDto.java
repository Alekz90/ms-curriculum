package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidCodePhone;
import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidNameText;
import alejdaf.commonutils.annotation.ValidPhone;
import alkz.mscurriculum.document.Profile;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ProfileDto {

  @Schema(name = "ProfileDto.Request", description = "DTO for Profile")
  public record Request(
      @NotBlank @ValidIdentifier String userId,
      @NotNull LocalDate birthDate,
      @NotBlank @ValidCodePhone String codePhone,
      @NotBlank @ValidPhone String cellphone,
      @NotBlank @ValidNameText String fullName) { }

  @Schema(name = "ProfileDto.Response", description = "DTO for Profile")
  public record Response(String id, String userId, LocalDate birthDate, String codePhone, String cellphone,
                         String image, String fullName) {
    public static Response build(Profile profile) {
      return new Response(
          profile.getId(),
          profile.getUserId(),
          profile.getBirthDate(),
          profile.getCodePhone(),
          profile.getCellphone(),
          profile.getImage(),
          profile.getFullName()
      );
    }
  }
}
