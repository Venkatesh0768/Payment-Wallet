package org.paymentwallet.userservice.dto;

import lombok.*;
import org.paymentwallet.userservice.enums.KYCSTATUS;
import org.paymentwallet.userservice.enums.Role;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private UUID id;
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Role role;
    private KYCSTATUS kycstatus;
}
