package alkz.mscurriculum.controller;

import alejdaf.commonutils.util.CommonConstants;
import alkz.mscurriculum.service.interfaces.IUsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static alkz.mscurriculum.util.PathConstants.RECOVERIES;
import static alkz.mscurriculum.util.PathConstants.V1_PATH;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(RECOVERIES)
@Tag(name = "Recoveries", description = "Endpoints for account recoveries management")
public class RecoveriesController {

  private final IUsersService service;

  @GetMapping(V1_PATH + "/send-recovery-password")
  public ResponseEntity<Void> sendRecoverPassword(
      @RequestParam @Pattern(regexp = CommonConstants.EMAIL_PATTERN) String email) {
    //TODO: Implement email sending with new password
    return ResponseEntity.noContent().build();
  }
}
