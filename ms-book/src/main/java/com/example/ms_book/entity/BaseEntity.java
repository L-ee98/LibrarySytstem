package com.example.ms_book.entity;

import com.example.ms_book.constant.BookCommonConstant;
import com.example.ms_book.enums.StatusCode;
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
        createdBy = BookCommonConstant.SYS;
        statusCode = StatusCode.ACTIVE.getCode();
    }

    @PreUpdate
    public void preUpdate() {
        updatedBy = BookCommonConstant.SYS;
        updatedTime = LocalDateTime.now();
    }
}
