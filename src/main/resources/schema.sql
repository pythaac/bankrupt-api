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
   id                   BIGINT,
   category             TEXT
);