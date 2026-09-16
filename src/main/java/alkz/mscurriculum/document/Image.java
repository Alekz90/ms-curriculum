package alkz.mscurriculum.document;

import akz.commonutils.util.CommonConstants;
import alkz.mscurriculum.dto.ImageDto;
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
public class Image {

  @MongoId
  private String id;
  private String name;
  private String url;

  /**
   * Create an empty image
   * @return Image
   */
  public static Image emptyImage() {
    return Image.builder()
        .id(Utils.generateObjectId())
        .name(CommonConstants.EMPTY)
        .url(CommonConstants.EMPTY)
        .build();
  }
  /**
   * Create a new image from ImageDto.Request
   * @param request ImageDto.Request
   * @return Image
   */
  public static Image build(ImageDto.Request request) {
    return Image.builder()
        .id(Utils.generateObjectId())
        .name(request.name())
        .url(request.url())
        .build();
  }

  /**
   * Update image data
   * @param request ImageDto.Request
   */
  public void update(ImageDto.Request request) {
    this.name = request.name();
    this.url = request.url();
  }
}

