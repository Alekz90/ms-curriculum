package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alkz.mscurriculum.model.UserDto;
import alkz.mscurriculum.service.interfaces.IAuthenticationService;
import alkz.mscurriculum.util.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.Constants.PUBLIC_V1_PATH;
import static alkz.mscurriculum.util.Constants.V1_PATH;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.AUTHENTICATIONS_PATH)
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthenticationController {

  private final IAuthenticationService service;

  @PostMapping(PUBLIC_V1_PATH + "/register")
  public ResponseEntity<ResultDto<UserDto.Authentication>> register(@Valid @RequestBody UserDto.Register request) {
    return ResponseEntity.ok(new ResultDto<>(service.register(request)));
  }

  @PostMapping(PUBLIC_V1_PATH + "/login")
  public ResponseEntity<ResultDto<UserDto.Authentication>> login(@Valid @RequestBody UserDto.Login request) {
    return ResponseEntity.ok(new ResultDto<>(service.login(request)));
  }

  @PatchMapping(V1_PATH + "/change-password")
  public ResponseEntity<Void> changePassword(@RequestBody UserDto.ChangePassword request) {
    service.changePassword(request);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping(PUBLIC_V1_PATH + "/recovery-password")
  public ResponseEntity<Void> recoveryPassword(@RequestBody UserDto.RecoveryPassword request) {
    service.recoveryPassword(request);
    return ResponseEntity.noContent().build();
  }
}
