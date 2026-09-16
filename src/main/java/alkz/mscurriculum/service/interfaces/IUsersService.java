package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.document.User;
import alkz.mscurriculum.dto.UserDto;

public interface IUsersService {
  User save(UserDto.Register userDto);
  void update(User user);
  User findById(String id);
  void changePassword(String id, UserDto.ChangePassword request);
  void recoveryPassword(String id, UserDto.RecoveryPassword request);
  UserDto.UserResponse getUserByUsername(String username);
  UserDto.UserResponse getUserById(String id);
  User findByEmail(String email);
  User findByUsername(String username);
}
