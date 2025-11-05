package alkz.mscurriculum.util;

import alejdaf.commonutils.exception.CustomCommonException;
import alejdaf.commonutils.util.enums.ECommonError;

import java.util.List;

public class Constants {
  public static final String BEARER = "Bearer ";
  public static final String ROLE_NAME = "ROLE_";
  public static final String V1_PATH = "/v1";
  public static final String PUBLIC_V1_PATH = V1_PATH + "/public";
  public static final String AUTHENTICATIONS_PATH = "authentications";
  public static final String VERIFICATIONS_PATH = "verifications";
  public static final String RECOVERIES_PATH = "recoveries";
  public static final String USERS_PATH = "users";
  public static final String SLASH = "/";
  public static final String SLASH_ALL = "/**";
  public static final String OPENING_BRACE = "{";
  public static final String CLOSING_BRACE = "}";
  public static final String INIT_PATTERN = "^";
  public static final String END_PATTERN = "$";
  public static final String PATH_VARIABLE_PATTERN = "[a-zA-Z0-9\\-]+";
  public static final String PATH_ALL_PATTERN = "[/a-zA-Z0-9\\-.]+";
  public static final String QUESTION_MARK = "?";
  public static final String EMPTY = "";
  public static final int INT_UNO = 1;
  public static final int INT_CERO = 0;
  public static final String[] WHITELIST = {
      "/v3/api-docs/**",
      "/v3/api-docs",
      "/swagger-ui/**",
      SLASH + AUTHENTICATIONS_PATH + PUBLIC_V1_PATH + SLASH_ALL
  };
  public static final List<String> WHITELIST_PATTERNS = Utils.convertPatterEndpoints(WHITELIST);
  public static final int VERIFICATION_EXPIRATION_HOURS = 24;
  public static final int VERIFICATION_CODE_LENGTH = 8;
  public static final String VERIFICATION_CODE_PATTERN = "^\\d{" + VERIFICATION_CODE_LENGTH + "}$";


  private Constants() {
    throw new CustomCommonException(ECommonError.UTILITY_CLASS);
  }
}
