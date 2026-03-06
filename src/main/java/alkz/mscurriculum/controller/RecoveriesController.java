package alkz.mscurriculum.controller;

import alejdaf.commonutils.util.CommonConstants;
import alkz.mscurriculum.dto.UserDto;
import alkz.mscurriculum.service.interfaces.IRecoveriesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.PathConstants.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(RECOVERIES)
@Tag(name = "Recoveries", description = "Endpoints for account recoveries management")
public class RecoveriesController {

  private final IRecoveriesService service;

  @GetMapping(V1 + "/send-recovery-password")
  public ResponseEntity<Void> sendRecoverPassword(
      @RequestParam @Pattern(regexp = CommonConstants.EMAIL_PATTERN) String email) {
    service.sendingRecoveryPassword(email);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping(V1 + "/{id}/recovery-password")
  public ResponseEntity<Void> recoveryPassword(
      @PathVariable @Pattern(regexp = CommonConstants.IDENTIFIER_PATTERN) String id,
      @RequestBody UserDto.RecoveryPassword request) {
    service.recoveryPassword(id, request);
    return ResponseEntity.noContent().build();
  }
}
