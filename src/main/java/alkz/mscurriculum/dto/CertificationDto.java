package alkz.mscurriculum.dto;

import akz.commonutils.annotation.ValidSpecialText;
import akz.commonutils.annotation.ValidTittleText;
import alkz.mscurriculum.document.Certification;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for Certification
 */
public class CertificationDto {

  /**
   * Request DTO for Certification
   */
  @Schema(name = "CertificationDto.Request", description = "DTO for Certification")
  public record Request(
      @NotBlank @ValidTittleText @Size(max = 100) String name,
      @NotBlank @ValidSpecialText @Size(max = 500) String description) {}

  /**
   * Response DTO for Certification
   */
  @Schema(name = "CertificationDto.Response", description = "DTO for Certification")
  public record Response(String id, String name, String description) {

    /**
     * Build a Response DTO from Certification document
     *
     * @param certification Certification
     */
    public static Response build(Certification certification) {
      return new Response(
          certification.getId(),
          certification.getName(),
          certification.getDescription());
    }
  }
}
