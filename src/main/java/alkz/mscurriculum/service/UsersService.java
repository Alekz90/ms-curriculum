package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.document.User;
import alkz.mscurriculum.dto.UserDto;
import alkz.mscurriculum.repository.UsersRepository;
import alkz.mscurriculum.service.interfaces.IUsersService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService implements IUsersService {

  private final PasswordEncoder passwordEncoder;
  private final UsersRepository repository;

  @Override
  public User save(UserDto.Register userDto) {
    if (repository.existsByUsername(userDto.username())) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.USERNAME_FOUND);
    }
    if (repository.existsByEmail(userDto.email())) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.EMAIL_FOUND);
    }

    User entity = User.build(userDto);
    entity.setPassword(passwordEncoder.encode(userDto.password()));

    return repository.save(entity);
  }

  @Override
  public User findById(String id) {
    return repository.findById(id)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.USER_NOT_FOUND));
  }

  @Override
  public void changePassword(String id, UserDto.ChangePassword request) {
    User user = this.findById(id);
    if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
      throw new CustomCommonException(HttpStatus.UNAUTHORIZED, EError.INVALID_OLD_PASSWORD);
    }
    user.setPassword(passwordEncoder.encode(request.newPassword()));
    this.update(user);
  }

  @Override
  public void recoveryPassword(String id, UserDto.RecoveryPassword request) {
    User user = this.findById(id);
    user.setPassword(passwordEncoder.encode(request.newPassword()));
    this.update(user);
  }

  @Override
  public UserDto.UserResponse getUserByUsername(String username) {
    return UserDto.UserResponse.build(this.findByUsername(username));
  }

  @Override
  public UserDto.UserResponse getUserById(String id) {
    return UserDto.UserResponse.build(this.findById(id));
  }

  @Override
  public User findByEmail(String email) {
    return repository.findByEmail(email)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.USER_NOT_FOUND));
  }

  @Override
  public User findByUsername(String username) {
    return repository.findByUsername(username)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.USER_NOT_FOUND));
  }

  @Override
  public void update(User user) {
    repository.save(user);
  }
}
