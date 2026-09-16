package alkz.mscurriculum.service;

import akz.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.document.User;
import alkz.mscurriculum.dto.UserDto;
import alkz.mscurriculum.service.interfaces.*;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

  private final IUsersService usersService;
  private final IProfilesService profilesService;
  private final IProfessionalDetailsService professionalDetailsService;
  private final IVerificationsService verificationsService;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public UserDto.Authentication register(UserDto.Register request) {
    if (!request.acceptTerms()) {
      throw new CustomCommonException(HttpStatus.BAD_REQUEST, EError.TERMS_NOT_ACCEPTED);
    }

    User user = usersService.save(request);
    profilesService.create(user.getId());
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
    User user = usersService.findByUsername(request.username());
    String token = jwtService.generateToken(user);
    return UserDto.Authentication.build(token, user);
  }

  @Override
  public UserDto.Authentication checkStatus(String tokenHeader) {
    String token =  tokenHeader.replace("Bearer ", "");
    String username = jwtService.getUsernameFromToken(token);
    User user = usersService.findByUsername(username);
    if (jwtService.isInvalidToken(token, user)) {
      throw new CustomCommonException(HttpStatus.FORBIDDEN, EError.USER_FORBIDDEN);
    }
    return UserDto.Authentication.build(token, user);
  }
}
