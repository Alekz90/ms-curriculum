package alkz.mscurriculum.document;

import alkz.mscurriculum.dto.LinkDto;
import alkz.mscurriculum.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Link {

  @MongoId
  private String id;
  private String name;
  private String url;

  /**
   * Create a new link from LinkDto.Request
   * @param request LinkDto.Request
   * @return Link
   */
  public static Link build(LinkDto.Request request) {
    return Link.builder()
        .id(Utils.generateObjectId())
        .name(request.name())
        .url(request.url())
        .build();
  }

  /**
   * Update link data
   * @param request LinkDto.Request
   */
  public void update(LinkDto.Request request) {
    this.name = request.name();
    this.url = request.url();
  }
}

