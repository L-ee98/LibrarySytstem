create table t_book (
                        id UNIQUEIDENTIFIER primary key,
                        title varchar(255) not null,
                        author varchar(255) not null,
                        isbn varchar(17) not null,
                        version BIGINT not null,
                        created_time DATETIME2 not null,
                        created_by varchar(255) not null,
                        updated_time DATETIME2,
                        updated_by varchar(255),
                        status_code varchar(5),
                        borrow_flag BIT not null,
                        borrow_by UNIQUEIDENTIFIER
);