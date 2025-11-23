package alkz.mscurriculum.document;

import alkz.mscurriculum.dto.SummaryDto;
import alkz.mscurriculum.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Summaries")
public class Summary {

  @MongoId
  private String id;
  private String position;
  private String summary;

  /**
   * Build ProfessionalDetail from ProfessionalDetailDto.Request
   * @param request ProfessionalDetailDto.Request
   * @return ProfessionalDetail
   */
  public static Summary build(SummaryDto.Request request) {
    return Summary.builder()
        .id(Utils.generateObjectId())
        .position(request.position())
        .summary(request.summary())
        .build();
  }

  /**
   * Update ProfessionalDetail from ProfessionalDetailDto.Request
   * @param request ProfessionalDetailDto.Request
   */
  public void update(SummaryDto.Request request) {
    this.position = request.position();
    this.summary = request.summary();
  }
}

