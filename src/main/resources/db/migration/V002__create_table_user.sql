CREATE TABLE IF NOT EXISTS TB_USER (
   ID                   BIGSERIAL            NOT NULL,
   LOGIN                VARCHAR(50)          NOT NULL,
   EMAIL                VARCHAR(254)         NOT NULL,
   PASSWORD             VARCHAR(60)          NOT NULL,
   NAME                 VARCHAR(70)          NOT NULL,
   ACTIVE               BOOL                 NOT NULL,
   BLOCKED              BOOL                 NOT NULL,
   CPF                  VARCHAR(14)          NULL,
   TELEPHONE            VARCHAR(20)          NOT NULL,
   CREATED_AT           TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP,
   UPDATED_AT           TIMESTAMP            NULL,
   CONSTRAINT PK_USER PRIMARY KEY (ID)
);

CREATE UNIQUE INDEX IF NOT EXISTS UK_USER_LOGIN ON TB_USER (LOGIN);

CREATE UNIQUE INDEX IF NOT EXISTS UK_USER_CPF ON TB_USER (CPF)
WHERE CPF IS NOT NULL AND COALESCE(TRIM(CPF),'') <> '';