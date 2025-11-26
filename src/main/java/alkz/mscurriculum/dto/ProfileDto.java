package alkz.mscurriculum.dto;

import alejdaf.commonutils.annotation.ValidCodePhone;
import alejdaf.commonutils.annotation.ValidNameText;
import alejdaf.commonutils.annotation.ValidPhone;
import alejdaf.commonutils.util.CommonConstants;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.document.Profile;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ProfileDto {

  @Schema(name = "ProfileDto.Request", description = "DTO for Profile")
  public record Request(
      @NotNull LocalDate birthDate,
      @NotBlank @ValidCodePhone String codePhone,
      @NotBlank @ValidPhone String cellphone,
      @NotBlank @ValidNameText String fullName) { }

  @Schema(name = "ProfileDto.Response", description = "DTO for Profile")
  public record Response(String id, String userId, String birthDate, String codePhone, String cellphone, String fullName,
                         AddressDto.Response address) {
    public static Response build(Profile profile) {
      return new Response(
          profile.getId(),
          profile.getUserId(),
          CommonUtils.convertLocalDateToString(profile.getBirthDate(), CommonConstants.FORMAT_RESPONSE_DATE),
          profile.getCodePhone(),
          profile.getCellphone(),
          profile.getFullName(),
          AddressDto.Response.build(profile.getAddress())
      );
    }
  }
}
