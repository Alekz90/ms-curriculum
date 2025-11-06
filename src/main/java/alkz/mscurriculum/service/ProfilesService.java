package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.document.Profile;
import alkz.mscurriculum.model.ProfileDto;
import alkz.mscurriculum.repository.ProfilesRepository;
import alkz.mscurriculum.service.interfaces.IProfilesService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfilesService implements IProfilesService {

  private final ProfilesRepository repository;

  /**
   * Create profile
   * @param request ProfileDto.Request
   * @return ProfileDto.Response
   */
  @Override
  public ProfileDto.Response create(ProfileDto.Request request) {
    this.validateProfileUnique(request.userId());
    this.validatePhoneUnique(request.codePhone(), request.cellphone());
    return ProfileDto.Response.build(repository.save(Profile.build(request)));
  }

  /**
   * Update profile
   * @param id Profile id
   * @param request ProfileDto.Request
   * @return ProfileDto.Response
   */
  @Override
  public ProfileDto.Response update(String id, ProfileDto.Request request) {
    Profile profile = this.findProfileById(id);

    if (!profile.getCodePhone().equals(request.codePhone() ) || !profile.getCellphone().equals(request.cellphone())) {
      this.validatePhoneUnique(request.codePhone(), request.cellphone());
    }

    return ProfileDto.Response.build(repository.save(Profile.update(profile, request)));
  }

  /**
   * Find profile by id
   * @param id Profile id
   * @return ProfileDto.Response
   */
  @Override
  public ProfileDto.Response findById(String id) {
    return ProfileDto.Response.build(this.findProfileById(id));
  }

  /**
   * Find profile by userId
   * @param idUser User id
   * @return ProfileDto.Response
   */
  @Override
  public ProfileDto.Response findByIdUser(String idUser) {
    return ProfileDto.Response.build(this.findProfileByIdUser(idUser));
  }

  /**
   * Find profile by id or throw not found exception
   * @param id Profile id
   * @return Profile
   */
  private Profile findProfileById(String id) {
    return repository.findById(id)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.PROFILE_NOT_FOUND));
  }

  /**
   * Find profile by userId or throw not found exception
   * @param idUser User id
   * @return Profile
   */
  private Profile findProfileByIdUser(String idUser) {
    return repository.findByIdUser(idUser)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.PROFILE_NOT_FOUND));
  }

  /**
   * Validate that the phone number is unique
   * @param codePhone Phone code
   * @param cellphone Cellphone number
   */
  private void validatePhoneUnique(String codePhone, String cellphone) {
    repository.findByCodePhoneAndCellphone(codePhone, cellphone)
        .ifPresent(p -> {throw new CustomCommonException(HttpStatus.CONFLICT, EError.PHONE_FOUND);});
  }

  private void validateProfileUnique(String idUser) {
    repository.findByIdUser(idUser)
        .ifPresent(p -> {throw new CustomCommonException(HttpStatus.CONFLICT, EError.PROFILE_FOUND);});
  }
}
