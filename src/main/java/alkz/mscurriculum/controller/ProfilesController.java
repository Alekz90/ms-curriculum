package alkz.mscurriculum.controller;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.util.CommonConstants;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.dto.ProfileDto;
import alkz.mscurriculum.service.interfaces.IProfilesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.PROFILES;
import static alkz.mscurriculum.util.PathConstants.V1_PATH;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(PROFILES)
@Tag(name = "Profiles", description = "Profiles management endpoints")
public class ProfilesController {

  private final IProfilesService service;

  @GetMapping(V1_PATH + "/{id}")
  public ResponseEntity<ResultDto<ProfileDto.Response>> getProfileById(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id) {
    return ResponseEntity.ok(new ResultDto<>(service.getById(id)));
  }

  @GetMapping(V1_PATH + "/users/{userId}")
  public ResponseEntity<ResultDto<ProfileDto.Response>> getProfileByUserId(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String userId) {
    return ResponseEntity.ok(new ResultDto<>(service.getByUserId(userId)));
  }

  @PostMapping(V1_PATH + "/users/{userId}")
  public ResponseEntity<ResultDto<ProfileDto.Response>> createProfile(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String userId,
      @Valid @RequestBody ProfileDto.Request request) {
    ProfileDto.Response response = service.create(userId, request);
    return ResponseEntity
        .created(CommonUtils.buildUriPost(PROFILES + V1_PATH, response.id()))
        .body(new ResultDto<>(response));
  }

  @PutMapping(V1_PATH + "/{id}")
  public ResponseEntity<ResultDto<ProfileDto.Response>> updateProfile(
      @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) @PathVariable String id,
      @Valid @RequestBody ProfileDto.Request request) {
    return ResponseEntity.ok(new ResultDto<>(service.update(id, request)));
  }
}
