package alkz.mscurriculum.controller;

import akz.commonutils.annotation.ValidIdentifier;
import akz.commonutils.annotation.ValidUsername;
import akz.commonutils.dto.ResultDto;
import akz.commonutils.util.CommonConstants;
import alkz.mscurriculum.dto.UserDto;
import alkz.mscurriculum.service.interfaces.IUsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.USERS;
import static alkz.mscurriculum.util.PathConstants.V1;

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

  @PatchMapping(V1 + "/change-password/{id}")
  public ResponseEntity<Void> changePassword(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id,
      @RequestBody UserDto.ChangePassword request) {
    service.changePassword(id, request);
    return ResponseEntity.noContent().build();
  }
}
