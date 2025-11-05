package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.model.UserDto;

public interface IAuthenticationService {
  UserDto.Authentication register(UserDto.Register request);
  UserDto.Authentication login(UserDto.Login request);
}
