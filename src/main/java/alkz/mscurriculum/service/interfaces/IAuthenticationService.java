package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.dto.UserDto;

public interface IAuthenticationService {
  UserDto.Authentication register(UserDto.Register request);
  UserDto.Authentication login(UserDto.Login request);
  void changePassword(String id, UserDto.ChangePassword request);
  void recoveryPassword(String id, UserDto.RecoveryPassword request);
  UserDto.Authentication checkStatus(String tokenHeader);
}
