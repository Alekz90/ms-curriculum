package alkz.mscurriculum.service.interfaces;

import alejdaf.commonutils.exception.CustomCommonException;
import alkz.mscurriculum.document.ProfessionalDetail;
import alkz.mscurriculum.model.ProfessionalDetailDto;
import alkz.mscurriculum.repository.ProfesionalDetailsRepository;
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
  public ProfessionalDetailDto.Response create(ProfessionalDetailDto.Request request) {
    this.validateProfessionalDetailUnique(request.idUser());
    return ProfessionalDetailDto.Response.build(repository.save(ProfessionalDetail.build(request)));
  }

  /**
   * Update professional detail
   * @param id Professional detail id
   * @param request ProfessionalDetailDto.Request
   * @return ProfessionalDetailDto.Response
   */
  @Override
  public ProfessionalDetailDto.Response update(String id, ProfessionalDetailDto.Request request) {
    return ProfessionalDetailDto.Response.build(ProfessionalDetail.update(this.findDetailById(id), request));
  }

  /**
   * Find professional detail by id
   * @param id Professional detail id
   * @return ProfessionalDetailDto.Response
   */
  @Override
  public ProfessionalDetailDto.Response findById(String id) {
    return ProfessionalDetailDto.Response.build(this.findDetailById(id));
  }

  /**
   * Find professional detail by user id
   * @param idUser User id
   * @return ProfessionalDetailDto.Response
   */
  @Override
  public ProfessionalDetailDto.Response findByIdUser(String idUser) {
    return ProfessionalDetailDto.Response.build(this.findDetailByIdUser(idUser));
  }

  /**
   * Find professional detail by id
   * @param id Professional detail id
   * @return ProfessionalDetail
   */
  private ProfessionalDetail findDetailById(String id) {
    return repository.findById(id)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.PROFESIONAL_DETAIL_NOT_FOUND));
  }

  /**
   * Find professional detail by user id
   * @param idUser User id
   * @return ProfessionalDetail
   */
  private ProfessionalDetail findDetailByIdUser(String idUser) {
    return repository.findByIdUser(idUser)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.PROFESIONAL_DETAIL_NOT_FOUND));
  }

  /**
   * Validate that professional detail is unique for a user
   * @param idUser User id
   */
  private void validateProfessionalDetailUnique(String idUser) {
    repository.findByIdUser(idUser)
        .ifPresent(pd -> {throw new CustomCommonException(HttpStatus.BAD_REQUEST, EError.PROFESIONAL_DETAIL_FOUND);});
  }
}
