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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static alkz.mscurriculum.util.Constants.V1_PATH;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.AUTHENTICATIONS_PATH)
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthenticationController {

  private final IAuthenticationService service;

  @PostMapping(V1_PATH + "/register")
  public ResponseEntity<ResultDto<UserDto.Authentication>> register(@Valid @RequestBody UserDto.Register request) {
    return ResponseEntity.ok(new ResultDto<>(service.register(request)));
  }

  @PostMapping(V1_PATH + "/login")
  public ResponseEntity<ResultDto<UserDto.Authentication>> login(@Valid @RequestBody UserDto.Login request) {
    return ResponseEntity.ok(new ResultDto<>(service.login(request)));
  }
}
