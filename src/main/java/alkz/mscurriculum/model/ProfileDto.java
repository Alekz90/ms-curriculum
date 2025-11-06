package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.*;
import alkz.mscurriculum.document.Profile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ProfileDto {

  public record Request(
      @NotBlank @ValidIdentifier String userId,
      @NotNull LocalDate birthDate,
      @NotBlank @ValidCodePhone String codePhone,
      @NotBlank @ValidPhone String cellphone,
      @NotBlank @ValidText String fullName
  ) { }

  public record Response(String id, String userId, LocalDate birthDate, String codePhone, String cellphone,
                         String image, String fullName
  ) {
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
