package alkz.mscurriculum.dto;

import akz.commonutils.annotation.ValidSpecialText;
import akz.commonutils.annotation.ValidTittleText;
import alkz.mscurriculum.document.ProfessionalExperience;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ExperienceDto {

  /**
   * Request DTO for ProfessionalExperience.
   */
  @Schema(name = "ExperienceDto.Request", description = "DTO for ProfessionalExperience")
  public record Request(
      @NotBlank @ValidTittleText @Size(max = 100) String company,
      @NotBlank @ValidTittleText @Size(max = 100) String position,
      @NotNull LocalDate startDate,
      LocalDate endDate,
      @NotNull Boolean stillWorking,
      @NotBlank @ValidSpecialText @Size(max = 2000) String activities,
      @NotNull @Valid AddressDto.Request location) { }

  /**
   * Response DTO for ProfessionalExperience.
   */
  @Schema(name = "ExperienceDto.Response", description = "DTO for ProfessionalExperience")
  public record Response(String id, String company, String position, LocalDate startDate, LocalDate endDate,
                         Boolean stillWorking, String activities, AddressDto.Response location) {

    /**
     * Build a Response DTO from a ProfessionalExperience document.
     * @param experience the ProfessionalExperience document
     * @return the Response DTO
     */
    @Schema(name = "ExperienceDto.Response", description = "DTO for ProfessionalExperience")
    public static Response build(ProfessionalExperience experience) {
      return new Response(
          experience.getId(),
          experience.getCompany(),
          experience.getPosition(),
          experience.getStartDate(),
          experience.getEndDate(),
          experience.getStillWorking(),
          experience.getActivities(),
          AddressDto.Response.build(experience.getLocation())
      );
    }
  }
}
