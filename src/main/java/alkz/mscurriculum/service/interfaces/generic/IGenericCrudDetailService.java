package alkz.mscurriculum.service.interfaces.generic;

public interface IGenericCrudDetailService<REQUEST, RESPONSE, ID> {
  RESPONSE create(REQUEST request);
  RESPONSE update(ID id, REQUEST request);
  void delete(ID id);
  RESPONSE findById(ID id);
  RESPONSE findByIdProfesionalDetail(ID idProfesionalDetail);
}
