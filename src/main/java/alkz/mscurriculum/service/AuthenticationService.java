package alkz.mscurriculum.service;

import alkz.mscurriculum.document.User;
import alkz.mscurriculum.model.UserDto;
import alkz.mscurriculum.service.interfaces.IAuthenticationService;
import alkz.mscurriculum.service.interfaces.IUsersService;
import alkz.mscurriculum.service.interfaces.IVerificationsService;
import lombok.RequiredArgsConstructor;
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
}
