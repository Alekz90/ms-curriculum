package alkz.mscurriculum.util;


import alejdaf.commonutils.exception.CustomCommonException;
import alejdaf.commonutils.util.enums.ECommonError;

import java.util.List;

import static alejdaf.commonutils.util.CommonConstants.SLASH;
import static alejdaf.commonutils.util.CommonConstants.SLASH_ALL;

public final class PathConstants {

  public static final String AUTHENTICATIONS      = "authentications";
  public static final String VERIFICATIONS        = "verifications";
  public static final String RECOVERIES           = "recoveries";
  public static final String USERS                = "users";
  public static final String PROFESSIONAL_DETAILS = "professional-details";
  public static final String PROFILES             = "profiles";
  public static final String ADDRESS              = "address";
  public static final String EDUCATIONS           = "educations";
  public static final String EXPERIENCES          = "experiences";
  public static final String ABILITIES            = "abilities";
  public static final String ABILITY_GROUPS       = "ability-groups";
  public static final String LINKS                = "links";
  public static final String LANGUAGES            = "languages";
  public static final String CERTIFICATIONS       = "certifications";




  public static final String V1_PATH = "/v1";
  public static final String PUBLIC_V1_PATH = V1_PATH + "/public";

  public static final String[] WHITELIST = {
      "/v3/api-docs/**",
      "/v3/api-docs",
      "/swagger-ui/**",
      SLASH + AUTHENTICATIONS + PUBLIC_V1_PATH + SLASH_ALL
  };
  public static final List<String> WHITELIST_PATTERNS = Utils.convertPatterEndpoints(WHITELIST);

  private PathConstants() {
    throw new CustomCommonException(ECommonError.UTILITY_CLASS);
  }
}
