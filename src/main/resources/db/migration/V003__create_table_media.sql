CREATE TABLE IF NOT EXISTS IMOB_MEDIA (
   ID                   UUID                 NOT NULL DEFAULT Uuid_generate_v4(),
   TYPE                 CHAR(10)             NOT NULL,
   FILENAME             VARCHAR(255)         NOT NULL,
   CONTENT_TYPE         VARCHAR(100)         NOT NULL,
   SIZE                 INTEGER              NOT NULL,
   IS_TEMPORARY         BOOLEAN              NOT NULL DEFAULT TRUE,
   CREATED_AT           TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CONSTRAINT PK_IMOB_MEDIA PRIMARY KEY (ID)
);