package alkz.mscurriculum.controller;

import alejdaf.commonutils.annotation.ValidIdentifier;
import alejdaf.commonutils.annotation.ValidUsername;
import alejdaf.commonutils.dto.ResultDto;
import alkz.mscurriculum.model.UserDto;
import alkz.mscurriculum.service.interfaces.IUsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static alkz.mscurriculum.util.PathConstants.USERS;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(USERS)
@Tag(name = "Users", description = "Users management endpoints")
public class UsersController {

  private final IUsersService service;

  @GetMapping("/{id}")
  public ResponseEntity<ResultDto<UserDto.UserResponse>> getCurrentUser(@PathVariable @ValidIdentifier String id) {
    return ResponseEntity.ok(new ResultDto<>(service.getUserById(id)));
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<ResultDto<UserDto.UserResponse>> getUserByUsername(@PathVariable @ValidUsername String username) {
    return ResponseEntity.ok(new ResultDto<>(service.getUserByUsername(username)));
  }
}
