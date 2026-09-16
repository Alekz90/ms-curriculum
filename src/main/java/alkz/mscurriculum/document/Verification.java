package alkz.mscurriculum.document;

import akz.commonutils.util.CommonUtils;
import alkz.mscurriculum.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Verifications")
public class Verification {

  @MongoId
  private String id;
  private String userId;
  private String code;
  private LocalDateTime creationDate;
  private LocalDateTime expirationDate;
  private LocalDateTime usedDate;
  private boolean used;


  public static Verification build(String userId, String code) {
    return Verification.builder()
        .userId(userId)
        .code(code)
        .creationDate(CommonUtils.getCurrentLocalDateTime())
        .expirationDate(CommonUtils.getCurrentLocalDateTime().plusHours(Constants.VERIFICATION_EXPIRATION_HOURS))
        .used(false)
        .build();
  }
}
