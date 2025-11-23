package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.document.User;
import alkz.mscurriculum.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUsersService extends UserDetailsService {
  @Override
  User loadUserByUsername(String username);
  User save(UserDto.Register userDto, String encodedPassword);
  void update(User user);
  User findById(String id);
  UserDto.UserResponse getUserByUsername(String username);
  UserDto.UserResponse getUserById(String id);
}
