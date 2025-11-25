package org.paymentwallet.userservice.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.paymentwallet.userservice.enums.KYCSTATUS;
import org.paymentwallet.userservice.enums.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
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
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
