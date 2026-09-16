package alkz.mscurriculum.service.interfaces.generic;

public interface IGenericCrudDetailService<DETAIL_ID, ID, CREATE_REQUEST, UPDATE_REQUEST, RESPONSE> {
  RESPONSE create(DETAIL_ID detailId, CREATE_REQUEST request);
  RESPONSE update(DETAIL_ID detailId, ID id, UPDATE_REQUEST request);
  void delete(DETAIL_ID detailId, ID id);
}
