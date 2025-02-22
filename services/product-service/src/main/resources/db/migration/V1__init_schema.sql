CREATE TABLE IF NOT EXISTS category (
                                        id BIGSERIAL PRIMARY KEY not null ,
                                        name VARCHAR(255) NOT NULL,
                                        description varchar(255)
);

CREATE TABLE IF NOT EXISTS product (
                                       id BIGSERIAL PRIMARY KEY  not null ,
                                       name VARCHAR(255) NOT NULL,
                                       description varchar(255),
                                       price DOUBLE PRECISION NOT NULL,
                                       quantity INTEGER NOT NULL,
                                       category_id BIGINT,
                                       CONSTRAINT fk_product_category FOREIGN KEY (category_id)
                                           REFERENCES category (id)
                                           ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS photo (
                                     id BIGSERIAL PRIMARY KEY not null ,
                                     data BYTEA NOT NULL,
                                     file_name VARCHAR(255),
                                     product_id BIGINT NOT NULL,
                                     CONSTRAINT fk_photo_product FOREIGN KEY (product_id)
                                         REFERENCES product (id)
                                         ON DELETE CASCADE
);