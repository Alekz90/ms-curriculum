package alkz.mscurriculum.service.interfaces.generic;

public interface IGenericCrudService<REQUEST, RESPONSE, ID> {
  RESPONSE create(REQUEST request);
  RESPONSE update(ID id, REQUEST request);
  RESPONSE getById(ID id);
  RESPONSE getByIdUser(ID userId);
}
