package alkz.mscurriculum.service.interfaces.generic;

public interface IGenericCrudService<REQUEST, RESPONSE, ID> {
  RESPONSE create(REQUEST request);
  RESPONSE update(ID id, REQUEST request);
//  void delete(ID id);
  RESPONSE getById(ID id);
  RESPONSE getByIdUser(ID userId);
}
