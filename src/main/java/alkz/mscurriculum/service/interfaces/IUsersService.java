package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.document.User;
import alkz.mscurriculum.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUsersService extends UserDetailsService {
  @Override
  User loadUserByUsername(String username);
  User save(UserDto.Register userDto, String encodedPassword);
  void updateUser(User user);
  User getUserById(String id);
  void changePassword(String id, UserDto.ChangePassword request);
  void recoveryPassword(String id, UserDto.RecoveryPassword request);
}
