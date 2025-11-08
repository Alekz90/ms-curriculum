package alkz.mscurriculum.model;

import alejdaf.commonutils.annotation.ValidSpecialText;
import alejdaf.commonutils.annotation.ValidTittleText;
import document.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LinkDto {

  @Schema(name = "LinkDto.Request", description = "DTO for Link")
  public record Request(
      @NotBlank @ValidTittleText @Size(max = 100) String name,
      @NotBlank @ValidSpecialText @Size(max = 300) String url) {}

  @Schema(name = "LinkDto.Response", description = "DTO for Link")
  public record Response(String id, String name, String url) {

    public static Response build(Link link) {
      return new Response(link.getId(), link.getName(), link.getUrl());
    }
  }
}
