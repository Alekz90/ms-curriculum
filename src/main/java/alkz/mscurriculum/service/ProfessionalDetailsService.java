package alkz.mscurriculum.service;

import alejdaf.commonutils.exception.CustomCommonException;
import document.ProfessionalDetail;
import alkz.mscurriculum.model.DetailDto;
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
   * @param request ProfessionalDetailDto.Request
   * @return ProfessionalDetailDto.Response
   */
  @Override
  public DetailDto.Response create(DetailDto.Request request) {
    this.validateProfessionalDetailUnique(request.userId());
    return DetailDto.Response.build(repository.save(ProfessionalDetail.build(request)));
  }

  /**
   * Update professional detail
   * @param id Professional detail id
   * @param request ProfessionalDetailDto.Request
   * @return ProfessionalDetailDto.Response
   */
  @Override
  public DetailDto.Response update(String id, DetailDto.Request request) {
    ProfessionalDetail detail = this.findDetailById(id);
    detail.update(request);
    return DetailDto.Response.build(detail);
  }

  /**
   * Find professional detail by id
   * @param id Professional detail id
   * @return ProfessionalDetailDto.Response
   */
  @Override
  public DetailDto.Response findById(String id) {
    return DetailDto.Response.build(this.findDetailById(id));
  }

  /**
   * Find professional detail by user id
   * @param userId User id
   * @return ProfessionalDetailDto.Response
   */
  @Override
  public DetailDto.Response findByIdUser(String userId) {
    return DetailDto.Response.build(this.findDetailByIdUser(userId));
  }

  /**
   * Find professional detail by id
   * @param id Professional detail id
   * @return ProfessionalDetail
   */
  @Override
  public ProfessionalDetail findDetailById(String id) {
    return repository.findById(id)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.PROFESIONAL_DETAIL_NOT_FOUND));
  }

  /**
   * Find professional detail by user id
   * @param userId User id
   * @return ProfessionalDetail
   */
  private ProfessionalDetail findDetailByIdUser(String userId) {
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

  @Override
  public ProfessionalDetail updateDetailData(ProfessionalDetail professionalDetail) {
    return repository.save(professionalDetail);
  }
}
