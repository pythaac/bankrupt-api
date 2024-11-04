DROP TABLE IF EXISTS board;
CREATE TABLE board (
    id                  BIGINT PRIMARY KEY,
    seller              TEXT,
    court               TEXT,
    title               TEXT,
    created             TEXT,
    due                 TEXT,
    file                TEXT,
    file_name           TEXT,
    telephone_number    TEXT
);

DROP TABLE IF EXISTS category;
CREATE TABLE category (
    id                  BIGINT PRIMARY KEY,
    category_name       TEXT,

    CONSTRAINT category_unique UNIQUE (category_name)
);

DROP TABLE IF EXISTS category_resource;
CREATE TABLE category_resource (
    id                  BIGINT PRIMARY KEY,
    category_id         BIGINT,
    category            TEXT
);

DROP TABLE IF EXISTS category_relation;
CREATE TABLE category_relation (
    id                  BIGINT PRIMARY KEY,
    board_id            BIGINT,
    category_id         BIGINT,

    CONSTRAINT category_relation_unique UNIQUE (board_id, category_id)
);