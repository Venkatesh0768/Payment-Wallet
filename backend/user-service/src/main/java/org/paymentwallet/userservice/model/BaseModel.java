package org.paymentwallet.userservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public abstract class BaseModel {
    @Id // this annotation makes the id property a primary key of our table
    @GeneratedValue(strategy = GenerationType.AUTO) // Identity means auto_increment
    protected UUID id;

    @Column(nullable = false, updatable = false)
    @CreatedDate // this annotation tells spring that only handle it for object creation
    protected LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate  // this annotation tells spring that only handle it for object update
    protected LocalDateTime updatedAt;
}