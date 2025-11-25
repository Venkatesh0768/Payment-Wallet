package org.paymentwallet.userservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.paymentwallet.userservice.enums.Role;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    @Email(message = "Enter a valid Email address")
    @NotBlank(message = "Email should Not be Empty")
    private String email;

    @NotBlank(message = "Password should Not be Empty")
    @Size(min = 8, message = "Password Must be 8 Letter")
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Size(max = 100, message = "Do not exceed the name above 100 Character")
    @NotBlank(message = "Name should Not be Empty")
    private String firstName;

    @Size(max = 100, message = "Do not exceed the name above 100 Character")
    @NotBlank(message = "Name should Not be Empty")
    private String lastName;

    @NotBlank(message = "Role should Not be Empty")
    private Role role;

}
