package alkz.mscurriculum.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ELanguageLevel {
  BASIC         ("Básico"),
  INTERMEDIATE  ("Intermedio"),
  ADVANCED      ("Avanzado"),
  NATIVE        ("Nativo");

  private final String level;
}
