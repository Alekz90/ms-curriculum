package alkz.mscurriculum.dto;

import alejdaf.commonutils.annotation.ValidSpecialText;
import alejdaf.commonutils.annotation.ValidTittleText;
import alkz.mscurriculum.document.Summary;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SummaryDto {

  @Schema(name = "SummaryDto.Request", description = "DTO for Summary")
  public record Request(
      @NotBlank @ValidTittleText @Size(max = 100) String position,
      @NotBlank @ValidSpecialText @Size(max = 1000) String summary) {}

  @Schema(name = "SummaryDto.Response", description = "DTO for Summary")
  public record Response(String id, String name, String url) {

    public static Response build(Summary summary) {
      return new Response(summary.getId(), summary.getPosition(), summary.getSummary());
    }
  }
}
