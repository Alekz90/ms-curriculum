package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidSpecialText;
import alejdaf.commonutils.annotation.ValidTextName;
import alkz.mscurriculum.document.ProfessionalDetail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProfessionalDetailDto {

  public record Request(
      @NotNull @ValidIdentifier String idUser,
      @NotBlank @ValidTextName @Size(max = 100) String position,
      @NotBlank @ValidSpecialText @Size(max = 1000) String summary) {}

  public record Response(String id, String idUser, String position, String summary) {
    public static Response build(ProfessionalDetail professionalDetail) {
      return new Response(
          professionalDetail.getId(),
          professionalDetail.getUserId(),
          professionalDetail.getPosition(),
          professionalDetail.getSummary());
    }
  }
}
