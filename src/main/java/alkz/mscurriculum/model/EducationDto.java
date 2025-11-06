package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidTextName;
import alkz.mscurriculum.document.Education;
import alkz.mscurriculum.util.enums.EEducationLevel;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Year;

public class EducationDto {

  public record Request(
      @NotBlank @ValidIdentifier String idProfesionalExperience,
      @NotNull EEducationLevel level,
      @NotBlank @ValidTextName @Size(max = 100) String institute,
      @NotBlank @ValidTextName @Size(max = 100) String degree,
      @NotNull @Digits(integer = 4, fraction = 0) Year startDate,
      @Digits(integer = 4, fraction = 0) Year endDate,
      @NotNull Boolean stillStudying) {}

  public record Response(String idProfesionalExperience, String id, EEducationLevel level, String institute,
                         String degree, Year startDate, Year endDate, Boolean stillStudying) {

    public static Response fromDocument(String idProfesionalExperience, Education education) {
      return new Response(
          idProfesionalExperience,
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
