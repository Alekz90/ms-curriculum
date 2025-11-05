package alkz.mscurriculum.util;

import alejdaf.commonutils.exception.CustomCommonException;
import alejdaf.commonutils.util.enums.ECommonError;

import java.util.List;

import static alkz.mscurriculum.util.Constants.*;

public final class Utils {

  public static List<String> convertPatterEndpoints(String[] paths) {
    List<String> pathsCopy = List.of(paths);

    return pathsCopy.stream().map(Utils::convertPatternPath).toList();
  }

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

  public static String generateCode() {
    StringBuilder code = new StringBuilder();
    while (true) {
      code.append((int) (Math.random() * 10));
      if (code.length() >= VERIFICATION_CODE_LENGTH) {
        return code.substring(INT_CERO, VERIFICATION_CODE_LENGTH);
      }
    }
  }

  private Utils() {
    throw new CustomCommonException(ECommonError.UTILITY_CLASS);
  }
}
