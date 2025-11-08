package alkz.mscurriculum.util;

import alejdaf.commonutils.exception.CustomCommonException;
import alejdaf.commonutils.util.enums.ECommonError;

import java.util.List;

public class Constants {
  public static final String BEARER = "Bearer ";
  public static final String ROLE_NAME = "ROLE_";
  public static final String PATH_VARIABLE_PATTERN = "[a-zA-Z0-9\\-]+";
  public static final String PATH_ALL_PATTERN = "[/a-zA-Z0-9\\-.]+";
  public static final int INT_UNO = 1;
  public static final int INT_CERO = 0;
  public static final int VERIFICATION_EXPIRATION_HOURS = 24;
  public static final int VERIFICATION_CODE_LENGTH = 8;
  public static final String VERIFICATION_CODE_PATTERN = "^\\d{" + VERIFICATION_CODE_LENGTH + "}$";


  private Constants() {
    throw new CustomCommonException(ECommonError.UTILITY_CLASS);
  }
}
