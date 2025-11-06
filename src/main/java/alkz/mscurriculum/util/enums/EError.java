package alkz.mscurriculum.util.enums;

import alejdaf.commonutils.util.enums.GenericEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EError implements GenericEnum<String> {

  USER_FORBIDDEN                ("0001", "Access denied. You do not have permission to access this resource."),
  WRONG_CREDENTIALS             ("0002", "Wrong User and/or Password."),
  ROLE_NOT_FOUND                ("0003", "This role wasn't found."),
  USER_NOT_FOUND                ("0004", "User wasn't found."),
  USERNAME_FOUND                ("0005", "This username already have been registered."),
  EMAIL_FOUND                   ("0006", "This E-Mail already have been registered."),
  USER_UNAUTHORIZED             ("0007", "Access denied. You do not have permission to access this resource."),
  VERIFICATION_NOT_FOUND        ("0008", "Verification wasn't found."),
  VERIFICATION_EXPIRED          ("0009", "Verification code already has expired."),
  VERIFICATION_INVALID_CODE     ("0010", "Verification code is invalid."),
  USER_VERIFIED                 ("0011", "This user already has been verified."),
  INVALID_OLD_PASSWORD          ("0012", "The old password is incorrect."),
  PROFILE_FOUND                 ("0012", "This user already has a profile."),
  PROFILE_NOT_FOUND             ("0012", "Profile not found for this user."),
  PHONE_FOUND                   ("0012", "Phone number already exists."),
  PROFESIONAL_DETAIL_FOUND      ("0012", "This user already has professional details."),
  PROFESIONAL_DETAIL_NOT_FOUND  ("0012", "Professional details not found for this user.");

  private final String id;
  private final String message;
}
