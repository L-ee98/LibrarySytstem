package com.example.ms_borrower.entity;

import com.example.ms_borrower.constant.BorrowerCommonConstant;
import com.example.ms_borrower.enums.StatusCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Version
    @Column(name = "version")
    private Long version;

    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "status_code")
    private String statusCode;

    @PrePersist
    public void prePersist() {
        createdBy = BorrowerCommonConstant.SYS;
        createdTime = LocalDateTime.now();
        statusCode = StatusCode.ACTIVE.getCode();
    }

    @PreUpdate
    public void preUpdate() {
        updatedBy = BorrowerCommonConstant.SYS;
        updatedTime = LocalDateTime.now();
    }
}
