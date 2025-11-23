package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.document.User;
import alkz.mscurriculum.dto.UserDto;
import alkz.mscurriculum.service.interfaces.IAuthenticationService;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
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
  private final IProfessionalDetailsService professionalDetailsService;
  private final IVerificationsService verificationsService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public UserDto.Authentication register(UserDto.Register request) {
    if (!request.acceptTerms()) {
      throw new CustomCommonException(HttpStatus.BAD_REQUEST, EError.TERMS_NOT_ACCEPTED);
    }

    User user = usersService.save(request, passwordEncoder.encode(request.password()));
    professionalDetailsService.create(user.getId());
    verificationsService.create(user.getId());
    //TODO: Send verification email
    return UserDto.Authentication.build(jwtService.generateToken(user), user);
  }

  @Override
  public UserDto.Authentication login(UserDto.Login request) {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password()));
    } catch (Exception e) {
      throw new CustomCommonException(HttpStatus.UNAUTHORIZED, EError.WRONG_CREDENTIALS);
    }
    User user = usersService.loadUserByUsername(request.username());
    String token = jwtService.generateToken(user);
    return UserDto.Authentication.build(token, user);
  }

  @Override
  public void changePassword(String id, UserDto.ChangePassword request) {
    User user = usersService.findById(id);
    if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
      throw new CustomCommonException(HttpStatus.UNAUTHORIZED, EError.INVALID_OLD_PASSWORD);
    }
    user.setPassword(passwordEncoder.encode(request.newPassword()));
    usersService.update(user);
  }

  @Override
  public void recoveryPassword(String id, UserDto.RecoveryPassword request) {
    User user = usersService.findById(id);
    user.setPassword(passwordEncoder.encode(request.newPassword()));
    usersService.update(user);
  }

  @Override
  public UserDto.Authentication checkStatus(String tokenHeader) {
    String token =  tokenHeader.replace("Bearer ", "");
    String username = jwtService.getUsernameFromToken(token);
    User user = usersService.loadUserByUsername(username);
    if (jwtService.isInvalidToken(token, user)) {
      throw new CustomCommonException(HttpStatus.FORBIDDEN, EError.USER_FORBIDDEN);
    }
    return UserDto.Authentication.build(token, user);
  }
}
