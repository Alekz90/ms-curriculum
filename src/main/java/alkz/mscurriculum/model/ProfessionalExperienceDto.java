package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidSpecialText;
import alejdaf.commonutils.annotation.ValidTextName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ProfessionalExperienceDto {

  public record Request(
      @NotBlank @ValidIdentifier String idProfesionalExperience,
      @NotBlank @ValidTextName @Size(max = 100) String company,
      @NotBlank @ValidTextName @Size(max = 100) String position,
      @NotNull LocalDate startDate,
      LocalDate endDate,
      @NotNull Boolean stillWorking,
      @NotBlank @ValidSpecialText @Size(max = 2000) String activities) { }

  public record Response(String idProfesionalExperience, String id, String company, String position,
                         LocalDate startDate, LocalDate endDate, Boolean stillWorking, String activities) {

    public static Response build(String idProfesionalExperience, String id, String company, String position, LocalDate startDate, LocalDate endDate,
        Boolean stillWorking, String activities) {
      return new Response(idProfesionalExperience, id, company, position, startDate, endDate, stillWorking, activities);
    }
  }
}
