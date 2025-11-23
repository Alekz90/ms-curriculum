package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.document.ProfessionalDetail;
import alkz.mscurriculum.dto.DetailDto;
import alkz.mscurriculum.repository.ProfesionalDetailsRepository;
import alkz.mscurriculum.service.interfaces.IProfessionalDetailsService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessionalDetailsService implements IProfessionalDetailsService {

  private final ProfesionalDetailsRepository repository;

  /**
   * Create professional detail
   * @param userId User id
   * @return ProfessionalDetail
   */
  @Override
  public ProfessionalDetail create(String userId) {
    this.validateProfessionalDetailUnique(userId);
    return repository.save(ProfessionalDetail.emptyDetail(userId));
  }

  /**
   * Update professional detail
   * @param id Professional detail id
   * @param detail ProfessionalDetail
   * @return ProfessionalDetail
   */
  @Override
  public ProfessionalDetail update(ProfessionalDetail detail) {
    return repository.save(detail);
  }

  /**
   * Find professional detail by id
   * @param id Professional detail id
   * @return ProfessionalDetailDto.Response
   */
  @Override
  public DetailDto.Response getDetailById(String id) {
    return DetailDto.Response.build(this.findById(id));
  }

  /**
   * Find professional detail by user id
   * @param userId User id
   * @return ProfessionalDetailDto.Response
   */
  @Override
  public DetailDto.Response getDetailByUserId(String userId) {
    return DetailDto.Response.build(this.findByUserId(userId));
  }

  /**
   * Find professional detail by id
   * @param id Professional detail id
   * @return ProfessionalDetail
   */
  @Override
  public ProfessionalDetail findById(String id) {
    return repository.findById(id)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.PROFESIONAL_DETAIL_NOT_FOUND));
  }

  /**
   * Find professional detail by user id
   * @param userId User id
   * @return ProfessionalDetail
   */
  @Override
  public ProfessionalDetail findByUserId(String userId) {
    return repository.findByUserId(userId)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.PROFESIONAL_DETAIL_NOT_FOUND));
  }

  /**
   * Validate that professional detail is unique for a user
   * @param userId User id
   */
  private void validateProfessionalDetailUnique(String userId) {
    repository.findByUserId(userId)
        .ifPresent(pd -> {throw new CustomCommonException(HttpStatus.BAD_REQUEST, EError.PROFESIONAL_DETAIL_FOUND);});
  }
}
