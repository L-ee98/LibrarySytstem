package com.example.ms_book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "t_book")
public class Book extends BaseEntity {

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "borrow_flag")
    private boolean borrowFlag;

    @Column(name = "borrow_by")
    private UUID borrowBy;
}
