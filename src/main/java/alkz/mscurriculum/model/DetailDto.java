package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidSpecialText;
import alejdaf.commonutils.annotation.ValidTittleText;
import document.ProfessionalDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DetailDto {

  /**
   * Request DTO for ProfessionalDetail
   */
  @Schema(name = "ProfessionalDetailDto.Request", description = "DTO for ProfessionalDetail")
  public record Request(
      @NotNull @ValidIdentifier String userId,
      @NotBlank @ValidTittleText @Size(max = 100) String position,
      @NotBlank @ValidSpecialText @Size(max = 1000) String summary) {}

  /**
   * Response DTO for ProfessionalDetail
   */
  @Schema(name = "ProfessionalDetailDto.Response", description = "DTO for ProfessionalDetail")
  public record Response(String id, String userId, String position, String summary) {

    /**
     * Build a Response DTO from ProfessionalDetail document
     * @param professionalDetail ProfessionalDetail
     */
    public static Response build(ProfessionalDetail professionalDetail) {
      return new Response(
          professionalDetail.getId(),
          professionalDetail.getUserId(),
          professionalDetail.getPosition(),
          professionalDetail.getSummary());
    }
  }
}
