package alkz.mscurriculum.controller;

import alejdaf.commonutils.util.CommonConstants;
import alkz.mscurriculum.model.UserDto;
import alkz.mscurriculum.service.interfaces.IUsersService;
import alkz.mscurriculum.util.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static alkz.mscurriculum.util.Constants.V1_PATH;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.USERS_PATH)
@Tag(name = "Users", description = "Endpoints for users management")
public class UsersController {

  private final IUsersService service;

}
