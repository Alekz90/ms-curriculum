package alkz.mscurriculum.service;

import akz.commonutils.exception.CustomCommonException;
import akz.commonutils.util.enums.ECommonError;
import alkz.mscurriculum.document.Image;
import alkz.mscurriculum.document.Profile;
import alkz.mscurriculum.dto.ImageDto;
import alkz.mscurriculum.service.interfaces.IImagesService;
import alkz.mscurriculum.service.interfaces.IProfilesService;
import alkz.mscurriculum.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImagesService implements IImagesService {

  private final IProfilesService profilesService;

  @Override
  public ImageDto.Response create(String profileId, ImageDto.Request request) {
    Profile profile =  profilesService.findById(profileId);

    if (Objects.nonNull(profile.getImage())) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.IMAGE_FOUND);
    }
    profile.setImage(Image.build(request));
    return ImageDto.Response.build(profilesService.update(profile).getImage());
  }

  @Override
  public ImageDto.Response update(String profileId, String id, ImageDto.Request request) {
    Profile profile =  profilesService.findById(profileId);
    if (Objects.isNull(profile.getImage()) || !id.equals(profile.getImage().getId())) {
      throw new CustomCommonException(HttpStatus.NOT_FOUND, EError.ADDRESS_NOT_FOUND);
    }
    profile.getImage().update(request);
    profilesService.update(profile);
    return ImageDto.Response.build(profile.getImage());
  }

  @Override
  public void delete(String profileId, String id) {
    throw new CustomCommonException(HttpStatus.NOT_IMPLEMENTED, ECommonError.METHOD_NOT_IMPLEMENTED);
  }
}
