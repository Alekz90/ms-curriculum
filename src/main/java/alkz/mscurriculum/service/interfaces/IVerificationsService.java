package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.document.Verification;

public interface IVerificationsService {
  Verification create(String userId);
  void markVerificationAsUsed(String id, String code);
}
