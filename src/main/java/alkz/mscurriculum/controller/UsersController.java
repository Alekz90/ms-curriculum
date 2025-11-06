package alkz.mscurriculum.controller;

import alkz.mscurriculum.service.interfaces.IUsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static alkz.mscurriculum.util.PathConstants.USERS;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(USERS)
@Tag(name = "Users", description = "Users management endpoints")
public class UsersController {

  private final IUsersService service;

}
