package alkz.mscurriculum.controller;

import alejdaf.commonutils.util.CommonConstants;
import alkz.mscurriculum.model.UserDto;
import alkz.mscurriculum.service.interfaces.IUsersService;
import alkz.mscurriculum.util.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.Constants.V1_PATH;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.RECOVERIES_PATH)
@Tag(name = "Recoveries", description = "Endpoints for user recoveries")
public class RecoveriesController {

  private final IUsersService service;

  @GetMapping(V1_PATH + "/{email}/send-recovery-password")
  public ResponseEntity<Void> sendRecoverPassword(
      @PathVariable @Pattern(regexp = CommonConstants.EMAIL_PATTERN) String email) {
    //TODO: Implement email sending with new password
    return ResponseEntity.noContent().build();
  }
}
