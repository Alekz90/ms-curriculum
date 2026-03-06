package alkz.mscurriculum.document;

import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.dto.ProfileDto;
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
  private String fullName;
  private Address address;
  private Image image;

  /**
   * Create an empty profile for a user
   * @param userId User id
   * @return Profile
   */
  public static Profile emptyProfile(String userId) {
    return Profile.builder()
        .userId(userId)
        .birthDate(CommonUtils.getDateZero())
        .codePhone("")
        .cellphone("")
        .fullName("")
        .address(Address.emptyAddress())
        .image(Image.emptyImage())
        .build();
  }

  /**
   * Build a Profile from ProfileDto.Request
   * @param request ProfileDto.Request
   * @return Profile
   */
  public static Profile build(String userId, ProfileDto.Request request) {
    return Profile.builder()
        .userId(userId)
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
