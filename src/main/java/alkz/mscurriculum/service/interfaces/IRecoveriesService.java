package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.document.Verification;

public interface IRecoveriesService {
  Verification create(String userId);
  void markRecoveryAsUsed(String id, String newPassword);
}
