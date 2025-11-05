package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.document.User;
import alkz.mscurriculum.model.UserDto;
import alkz.mscurriculum.service.interfaces.IAuthenticationService;
import alkz.mscurriculum.service.interfaces.IUsersService;
import alkz.mscurriculum.service.interfaces.IVerificationsService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

  private final IUsersService usersService;
  private final IVerificationsService verificationsService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public UserDto.Authentication register(UserDto.Register request) {
    User user = usersService.save(request, passwordEncoder.encode(request.password()));
    verificationsService.create(user.getId());
    //TODO: Send verification email
    return new UserDto.Authentication(jwtService.generateToken(user));
  }

  @Override
  public UserDto.Authentication login(UserDto.Login request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password()));
    String token = jwtService.generateToken(usersService.loadUserByUsername(request.username()));
    return new UserDto.Authentication(token);
  }

  @Override
  public void changePassword(UserDto.ChangePassword request) {
    User user = usersService.getUserById(request.id());
    if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
      throw new CustomCommonException(HttpStatus.UNAUTHORIZED, EError.INVALID_OLD_PASSWORD);
    }
    user.setPassword(passwordEncoder.encode(request.newPassword()));
    usersService.updateUser(user);
  }

  @Override
  public void recoveryPassword(UserDto.RecoveryPassword request) {
    User user = usersService.getUserById(request.id());
    user.setPassword(passwordEncoder.encode(request.newPassword()));
    usersService.updateUser(user);
  }
}
