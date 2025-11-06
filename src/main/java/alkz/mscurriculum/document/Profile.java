package alkz.mscurriculum.document;

import alkz.mscurriculum.model.ProfileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Profiles")
public class Profile {

  @MongoId
  private String id;
  private String userId;
  private LocalDate birthDate;
  private String codePhone;
  private String cellphone;
  private String image;
  private String fullName;

  /**
   * Build a Profile from ProfileDto.Request
   * @param request ProfileDto.Request
   * @return Profile
   */
  public static Profile build(ProfileDto.Request request) {
    return Profile.builder()
        .userId(request.userId())
        .birthDate(request.birthDate())
        .codePhone(request.codePhone())
        .cellphone(request.cellphone())
        .fullName(request.fullName())
        .build();
  }

  /**
   * Update profile data
   * @param profile existing profile
   * @param request data to update
   * @return updated profile
   */
  public static Profile update(Profile profile, ProfileDto.Request request) {
    profile.setBirthDate(request.birthDate());
    profile.setCodePhone(request.codePhone());
    profile.setCellphone(request.cellphone());
    profile.setFullName(request.fullName());
    return profile;
  }
}

