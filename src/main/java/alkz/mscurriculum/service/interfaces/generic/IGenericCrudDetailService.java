package alkz.mscurriculum.service.interfaces.generic;

public interface IGenericCrudDetailService<DETAIL_ID, ID, REQUEST, RESPONSE> {
  RESPONSE create(DETAIL_ID detailId, REQUEST request);
  RESPONSE update(DETAIL_ID detailId, ID id, REQUEST request);
  void delete(DETAIL_ID detailId, ID id);
  //RESPONSE findById(ID id);
  //RESPONSE findByIdProfesionalDetail(ID idProfesionalDetail);
}
