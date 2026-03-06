package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.document.User;
import alkz.mscurriculum.document.Verification;
import alkz.mscurriculum.repository.VerificationsRepository;
import alkz.mscurriculum.service.interfaces.IUsersService;
import alkz.mscurriculum.service.interfaces.IVerificationsService;
import alkz.mscurriculum.util.Utils;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationsService implements IVerificationsService {

  private final VerificationsRepository repository;
  private final IUsersService usersService;

  @Override
  public Verification create(String userId) {
    Verification verification = Verification.build(userId, Utils.generateCode());
    return repository.save(verification);
  }

  @Override
  public void markVerificationAsUsed(String id, String code) {
    Verification verification = repository.findById(id)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.VERIFICATION_NOT_FOUND));

    User user = usersService.findById(verification.getUserId());
    if (user.isVerified()) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.USER_VERIFIED);
    }

    if (!verification.getCode().equals(code)) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.VERIFICATION_INVALID_CODE);
    }
    if (verification.getExpirationDate().isBefore(CommonUtils.getCurrentLocalDateTime())) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.VERIFICATION_EXPIRED);
    }

    verification.setUsed(true);
    verification.setUsedDate(CommonUtils.getCurrentLocalDateTime());
    user.setVerified(true);

    repository.save(verification);
    usersService.update(user);
  }
}
