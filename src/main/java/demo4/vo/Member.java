package demo4.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Member {

    private Long id;

    //@NotEmpty
    @Size(min =8, max = 15, message = "사용자 ID의 사이즈는 최소 8자리, 최대 15자리 까지 가능합니다.")
    private String username;

   // @Size(min =8, max = 15, message = "사용자 비밀번호의 사이즈는 최소 8자리, 최대 15자리 까지 가능합니다.")
   @Pattern(regexp = "^(?=.*[a-zA-z])(?=.*[0-9])(?!.*[^a-zA-z0-9]).{8,15}$", message = "비밀번호는 영문,숫자 8~15 자리로 작성해주세요.")
   // @NotEmpty
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    //@NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;

   // @NotEmpty
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "전화번호는 10 ~ 11 자리의 숫자만 입력 가능합니다.")
    private String phone;

    private String regdate;
    private String moddate;
}