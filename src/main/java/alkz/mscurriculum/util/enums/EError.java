package alkz.mscurriculum.util.enums;

import alejdaf.commonutils.util.enums.GenericEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EError implements GenericEnum<String> {

  USER_FORBIDDEN                ("0001", "Access denied. You do not have permission to access this resource."),
  WRONG_CREDENTIALS             ("0002", "Wrong User and/or Password."),
  USER_NOT_FOUND                ("0003", "User wasn't found."),
  USERNAME_FOUND                ("0004", "This username already have been registered."),
  EMAIL_FOUND                   ("0005", "This E-Mail already have been registered."),
  VERIFICATION_NOT_FOUND        ("0006", "Verification wasn't found."),
  VERIFICATION_EXPIRED          ("0007", "Verification code already has expired."),
  VERIFICATION_INVALID_CODE     ("0008", "Verification code is invalid."),
  USER_VERIFIED                 ("0009", "This user already has been verified."),
  INVALID_OLD_PASSWORD          ("0010", "The old password is incorrect."),
  PROFILE_FOUND                 ("0013", "This user already has a profile."),
  PROFILE_NOT_FOUND             ("0014", "Profile not found for this user."),
  PHONE_FOUND                   ("0015", "Phone number already exists."),
  PROFESIONAL_DETAIL_FOUND      ("0016", "This user already has professional details."),
  PROFESIONAL_DETAIL_NOT_FOUND  ("0017", "Professional details not found for this user."),
  ADDRESS_FOUND                 ("0018", "Address already exists for this professional detail."),
  ADDRESS_NOT_FOUND             ("0019", "Address not found for this professional detail."),
  CERTIFICATION_NOT_FOUND       ("0020", "Certification not found for this professional detail."),
  EXPERIENCE_NOT_FOUND          ("0021", "Profesional experience not found for this professional detail."),
  EDUCATION_NOT_FOUND           ("0022", "Education not found for this professional detail."),
  LANGUAGE_NOT_FOUND            ("0023", "Language not found for this professional detail."),
  LINK_NOT_FOUND                ("0024", "Link not found for this professional detail."),
  ABILITY_GROUP_NOT_FOUND       ("0025", "Ability group not found for this professional detail."),
  ABILITY_NOT_FOUND             ("0026", "Ability not found for this professional detail."),
  TERMS_NOT_ACCEPTED            ("0027", "Terms and conditions must be accepted."),
  SUMMARY_NOT_FOUND             ("0028", "Summary not found for this professional detail."),;

  private final String id;
  private final String message;
}
