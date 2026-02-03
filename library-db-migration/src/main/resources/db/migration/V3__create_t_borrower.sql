create table t_borrower (
                            id UNIQUEIDENTIFIER primary key,
                            username varchar(255) not null,
                            email varchar(255) not null,
                            borrow_limit_remaining INT NOT NULL,
                            version BIGINT not null,
                            created_time DATETIME2 not null,
                            created_by varchar(255) not null,
                            updated_time DATETIME2,
                            updated_by varchar(255),
                            status_code varchar(5),
);