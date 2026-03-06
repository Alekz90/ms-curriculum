package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.dto.UserDto;

public interface IRecoveriesService {
  void sendingRecoveryPassword(String email);
  void recoveryPassword(String id, UserDto.RecoveryPassword request);
}
