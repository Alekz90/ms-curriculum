package alkz.mscurriculum.document;

import alejdaf.commonutils.util.CommonUtils;
import alkz.mscurriculum.model.UserDto;
import alkz.mscurriculum.util.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Users")
public class User implements UserDetails {

  @MongoId
  private String id;
  private String username;
  private String password;
  private String email;
  private boolean active;
  private LocalDateTime creationDate;
  private ERole role;
  private boolean blocked;
  private boolean verified;
  private boolean acceptTerms;

  @Override
  public List<SimpleGrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  public static User build(UserDto.Register userDto, String encodedPassword) {
    return User.builder()
        .username(userDto.username())
        .password(encodedPassword)
        .email(userDto.email())
        .active(true)
        .creationDate(CommonUtils.getCurrentLocalDateTime())
        .role(ERole.USER)
        .blocked(false)
        .verified(false)
        .acceptTerms(userDto.acceptTerms())
        .build();
  }
}
