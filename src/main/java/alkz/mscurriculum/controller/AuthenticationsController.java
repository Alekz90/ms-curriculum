package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonConstants;
import alkz.mscurriculum.dto.UserDto;
import alkz.mscurriculum.service.interfaces.IAuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(AUTHENTICATIONS)
@Tag(name = "Authentication", description = "Authentication management endpoints")
public class AuthenticationsController {

  private final IAuthenticationService service;

  @PostMapping(PUBLIC_V1_PATH + "/register")
  public ResponseEntity<ResultDto<UserDto.Authentication>> register(@Valid @RequestBody UserDto.Register request) {
    return ResponseEntity.ok(new ResultDto<>(service.register(request)));
  }

  @PostMapping(PUBLIC_V1_PATH + "/login")
  public ResponseEntity<ResultDto<UserDto.Authentication>> login(@Valid @RequestBody UserDto.Login request) {
    return ResponseEntity.ok(new ResultDto<>(service.login(request)));
  }

  @PatchMapping(V1_PATH + "/change-password/{id}")
  public ResponseEntity<Void> changePassword(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id,
      @RequestBody UserDto.ChangePassword request) {
    service.changePassword(id, request);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping(PUBLIC_V1_PATH + "/recovery-password/{id}")
  public ResponseEntity<Void> recoveryPassword(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id,
      @RequestBody UserDto.RecoveryPassword request) {
    service.recoveryPassword(id, request);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(V1_PATH + "/check-status")
  public ResponseEntity<ResultDto<UserDto.Authentication>> checkStatus(
      @RequestHeader(value = "Authorization", required = true) String tokenHeader) {
    return ResponseEntity.ok(new ResultDto<>(service.checkStatus(tokenHeader)));
  }
}
