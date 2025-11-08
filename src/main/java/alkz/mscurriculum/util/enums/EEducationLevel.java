package alkz.mscurriculum.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EEducationLevel {
  PRIMARY           ("Primaria"),
  SECONDARY         ("Secundaria"),
  HIGH_SCHOOL       ("Bachillerato"),
  ASSOCIATE_DEGREE  ("Técnico Superior"),
  UNIVERSITY_DEGREE ("Ingeniería / Licenciatura"),
  MASTER_DEGREE     ("Maestría"),
  DOCTORATE         ("Doctorado");

  private final String level;
}
