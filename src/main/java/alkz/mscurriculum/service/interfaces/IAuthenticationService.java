package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.dto.UserDto;

public interface IAuthenticationService {
  UserDto.Authentication register(UserDto.Register request);
  UserDto.Authentication login(UserDto.Login request);
  UserDto.Authentication checkStatus(String tokenHeader);
}
