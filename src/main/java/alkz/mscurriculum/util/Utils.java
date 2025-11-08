package alkz.mscurriculum.util;

import alejdaf.commonutils.exception.CustomCommonException;
import alejdaf.commonutils.util.enums.ECommonError;
import org.bson.types.ObjectId;

import java.util.List;

import static alejdaf.commonutils.util.CommonConstants.PATH_ALL_PATTERN;
import static alejdaf.commonutils.util.CommonConstants.PATH_VARIABLE_PATTERN;
import static alkz.mscurriculum.util.Constants.*;
import static alejdaf.commonutils.util.CommonConstants.*;

/**
 * Utility class for common operations
 */
public final class Utils {

  /**
   * Convert an array of paths with variables to regex patterns
   * @param paths array of paths with variables
   * @return list of regex patterns
   */
  public static List<String> convertPatterEndpoints(String[] paths) {
    List<String> pathsCopy = List.of(paths);

    return pathsCopy.stream().map(Utils::convertPatternPath).toList();
  }

  /**
   * Convert a path with variables to a regex pattern
   * @param path path with variables
   * @return regex pattern
   */
  private static String convertPatternPath(String path) {
    while (path.contains(OPENING_BRACE) && path.contains(CLOSING_BRACE)) {
      int start = path.indexOf(OPENING_BRACE);
      int end = path.indexOf(CLOSING_BRACE);
      String toReplace = path.substring(start, end + INT_UNO);
      path = path.replace(toReplace, PATH_VARIABLE_PATTERN);
    }
    if (path.contains(SLASH_ALL)) {
      path = path.replace(SLASH_ALL, PATH_ALL_PATTERN);
    }
    return INIT_PATTERN + path + END_PATTERN;
  }

  /**
   * Generate a numeric verification code of length VERIFICATION_CODE_LENGTH
   * @return verification code
   */
  public static String generateCode() {
    StringBuilder code = new StringBuilder();
    while (true) {
      code.append((int) (Math.random() * 10));
      if (code.length() >= VERIFICATION_CODE_LENGTH) {
        return code.substring(INT_CERO, VERIFICATION_CODE_LENGTH);
      }
    }
  }

  /**
   * Generate a new ObjectId
   * @return ObjectId
   */
  public static String generateObjectId() {
    return new ObjectId().toString();
  }

  private Utils() {
    throw new CustomCommonException(ECommonError.UTILITY_CLASS);
  }
}
