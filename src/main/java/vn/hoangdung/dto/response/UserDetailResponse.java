package vn.hoangdung.dto.response;

import lombok.*;
import vn.hoangdung.util.Gender;
import vn.hoangdung.util.UserStatus;
import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
public class UserDetailResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private Gender gender;
    private String username;
    private String type;
    private UserStatus status;

    public UserDetailResponse(Long id, String firstName, String lastName, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
}