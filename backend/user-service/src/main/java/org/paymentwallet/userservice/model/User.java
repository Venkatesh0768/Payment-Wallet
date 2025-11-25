package org.paymentwallet.userservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.paymentwallet.userservice.enums.KYCSTATUS;
import org.paymentwallet.userservice.enums.Role;


@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private KYCSTATUS kycstatus;
}
