package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidTittleText;
import document.Education;
import alkz.mscurriculum.util.enums.EEducationLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Year;

public class EducationDto {

  /**
   * Request DTO for Education.
   */
  @Schema(name = "EducationDto.Request", description = "DTO for Education")
  public record Request(
      @NotNull EEducationLevel level,
      @NotBlank @ValidTittleText @Size(max = 100) String institute,
      @NotBlank @ValidTittleText @Size(max = 100) String degree,
      @NotNull @Digits(integer = 4, fraction = 0) Year startDate,
      @Digits(integer = 4, fraction = 0) Year endDate,
      @NotNull Boolean stillStudying) {}

  /**
   * Response DTO for Education.
   */
  @Schema(name = "EducationDto.Response", description = "DTO for Education")
  public record Response(String id, EEducationLevel level, String institute,
                         String degree, Year startDate, Year endDate, Boolean stillStudying) {

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
