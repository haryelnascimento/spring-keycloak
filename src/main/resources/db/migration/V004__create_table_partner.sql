CREATE TABLE IF NOT EXISTS IMOB_PARTNER (
   ID                       UUID               NOT NULL DEFAULT Uuid_generate_v4(),
   ACTIVE                   BOOL               NOT NULL,
   CPF_CNPJ                 VARCHAR(14)        NOT NULL,
   NAME                     VARCHAR(100)       NOT NULL,
   TELEPHONE                VARCHAR(20)        NOT NULL,
   EMAIL                    VARCHAR(100)       NOT NULL,
   LINK                     VARCHAR(50)        NOT NULL,
   TEMPLATE_STYLE           VARCHAR(30)        NOT NULL,
   SUMMARY                  VARCHAR(1000)      NULL,
   REPRESENTATIVE_NAME      VARCHAR(100)       NULL,
   REPRESENTATIVE_EMAIL     VARCHAR(100)       NULL,
   REPRESENTATIVE_TELEPHONE VARCHAR(20)        NULL,
   LOGO_ID                  BIGINT             NULL,
   FAVICON_ID               BIGINT             NULL,
   ADDRESS_ID               BIGINT             NOT NULL,
   CREATED_AT               TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
   UPDATED_AT               TIMESTAMP          NULL,
   CONSTRAINT PK_IMOB_PARTNER PRIMARY KEY (ID)
);

CREATE UNIQUE INDEX IF NOT EXISTS UK_PARTNER_LINK ON IMOB_PARTNER (LINK);

