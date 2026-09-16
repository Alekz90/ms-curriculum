package alkz.mscurriculum.service.interfaces;

import alkz.mscurriculum.document.Profile;
import alkz.mscurriculum.dto.ProfileDto;

public interface IProfilesService {
  Profile findById(String id);
  Profile findByIdUser(String userId);
  Profile update(Profile profile);
  Profile create(String userId);
  ProfileDto.Response create(String userId, ProfileDto.Request request);
  ProfileDto.Response update(String id, ProfileDto.Request request);
  ProfileDto.Response getById(String id);
  ProfileDto.Response getByUserId(String userId);
}
