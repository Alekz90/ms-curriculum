package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidTittleText;
import alkz.mscurriculum.document.Education;
import alkz.mscurriculum.util.enums.EEducationLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class EducationDto {

  /**
   * Request DTO for Education.
   */
  @Schema(name = "EducationDto.Request", description = "DTO for Education")
  public record Request(
      @NotNull EEducationLevel level,
      @NotBlank @ValidTittleText @Size(max = 100) String institute,
      @NotBlank @ValidTittleText @Size(max = 100) String degree,
      @NotNull @Digits(integer = 4, fraction = 0) Integer startYear,
      @Digits(integer = 4, fraction = 0) Integer endYear,
      @NotNull Boolean stillStudying) {}

  /**
   * Response DTO for Education.
   */
  @Schema(name = "EducationDto.Response", description = "DTO for Education")
  public record Response(String id, EEducationLevel level, String institute,
                         String degree, Integer startYear, Integer endYear, Boolean stillStudying) {

    /**
     * Build a Response DTO from an Education document.
     * @param education the Education document
     * @return the Response DTO
     */
    public static Response build(Education education) {
      return new Response(
          education.getId(),
          education.getLevel(),
          education.getInstitute(),
          education.getDegree(),
          education.getStartDate(),
          education.getEndDate(),
          education.getStillStudying()
      );
    }
  }

}
