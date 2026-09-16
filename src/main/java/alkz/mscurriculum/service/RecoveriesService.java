package alkz.mscurriculum.service;

import akz.commonutils.exception.CustomCommonException;
import akz.commonutils.util.CommonUtils;
import alkz.mscurriculum.document.Recovery;
import alkz.mscurriculum.document.User;
import alkz.mscurriculum.dto.UserDto;
import alkz.mscurriculum.repository.RecoveriesRepository;
import alkz.mscurriculum.service.interfaces.IRecoveriesService;
import alkz.mscurriculum.service.interfaces.IUsersService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecoveriesService implements IRecoveriesService {

  private final IUsersService usersService;
  private final RecoveriesRepository repository;

  @Override
  public void sendingRecoveryPassword(String email) {
    User user = usersService.findByEmail(email);

    Recovery recovery = repository.findByEmailAndUsedFalseAndExpirationDateAfter(email, CommonUtils.getCurrentLocalDateTime())
        .orElse(Recovery.build(user.getId(), email));

    repository.save(recovery);

    // Additional actions like sending recovery email could go here
  }

  @Override
  public void recoveryPassword(String id, UserDto.RecoveryPassword request) {
    Recovery recovery = repository.findById(id)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.RECOVERY_NOT_FOUND));

    if (recovery.isUsed() || recovery.getExpirationDate().isBefore(CommonUtils.getCurrentLocalDateTime())) {
      throw new CustomCommonException(HttpStatus.BAD_REQUEST, EError.RECOVERY_INVALID);
    }

    usersService.recoveryPassword(recovery.getUserId(), request);

    recovery.setUsed(true);
    recovery.setUsedDate(CommonUtils.getCurrentLocalDateTime());
    repository.save(recovery);

    // Additional actions like sending confirmation email could go here
  }
}
