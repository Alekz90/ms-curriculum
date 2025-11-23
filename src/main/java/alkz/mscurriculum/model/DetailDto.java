package alkz.mscurriculum.model;

import alkz.mscurriculum.document.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class DetailDto {

  /**
   * Response DTO for ProfessionalDetail
   */
  @Schema(name = "ProfessionalDetailDto.Response", description = "DTO for ProfessionalDetail")
  public record Response(String id, String userId, Summary summary, Address address,
      List<ExperienceDto.Response>experiences,
      List<LanguageDto.Response> languages,
      List<AbilityGroupDto.Response> abilityGroups,
      List<EducationDto.Response> educations,
      List<CertificationDto.Response> certifications,
      List<LinkDto.Response> links) {

    /**
     * Build a Response DTO from ProfessionalDetail document
     * @param detail ProfessionalDetail
     */
    public static Response build(ProfessionalDetail detail) {
      return new Response(
          detail.getId(),
          detail.getUserId(),
          detail.getSummary(),
          detail.getAddress(),
          detail.getExperiences().stream().map(ExperienceDto.Response::build).toList(),
          detail.getLanguages().stream().map(LanguageDto.Response::build).toList(),
          detail.getAbilityGroups().stream().map(AbilityGroupDto.Response::build).toList(),
          detail.getEducations().stream().map(EducationDto.Response::build).toList(),
          detail.getCertifications().stream().map(CertificationDto.Response::build).toList(),
          detail.getLinks().stream().map(LinkDto.Response::build).toList()
      );
    }
  }
}
