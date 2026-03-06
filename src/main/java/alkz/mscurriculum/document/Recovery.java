package alkz.mscurriculum.document;

import alejdaf.commonutils.util.CommonUtils;
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
@Document(collection = "Recoveries")
public class Recovery {

  @MongoId
  private String id;
  private String userId;
  private String email;
  private LocalDateTime creationDate;
  private LocalDateTime expirationDate;
  private LocalDateTime usedDate;
  private boolean used;


  public static Recovery build(String userId, String email) {
    return Recovery.builder()
        .userId(userId)
        .email(email)
        .creationDate(CommonUtils.getCurrentLocalDateTime())
        .expirationDate(CommonUtils.getCurrentLocalDateTime().plusHours(Constants.VERIFICATION_EXPIRATION_HOURS))
        .used(false)
        .build();
  }
}
