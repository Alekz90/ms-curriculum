package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.document.User;
import alkz.mscurriculum.model.UserDto;
import alkz.mscurriculum.repository.UsersRepository;
import alkz.mscurriculum.service.interfaces.IUsersService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService implements IUsersService {

  private final UsersRepository repository;
  //private final PasswordEncoder passwordEncoder;

  @Override
  public User loadUserByUsername(String username) {
    return repository.findByUsername(username)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.USER_NOT_FOUND));
  }

  @Override
  public User save(UserDto.Register userDto, String encodedPassword) {
    if (repository.existsByUsername(userDto.username())) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.USERNAME_FOUND);
    }
    if (repository.existsByEmail(userDto.email())) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.EMAIL_FOUND);
    }

    return repository.save(User.build(userDto, encodedPassword));
  }

  @Override
  public User findById(String id) {
    return repository.findById(id)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.USER_NOT_FOUND));
  }

  @Override
  public UserDto.UserResponse getUserByUsername(String username) {
    return UserDto.UserResponse.build(this.loadUserByUsername(username));
  }

  @Override
  public UserDto.UserResponse getUserById(String id) {
    return UserDto.UserResponse.build(this.findById(id));
  }

  @Override
  public void update(User user) {
    repository.save(user);
  }
}
