CREATE TABLE IF NOT EXISTS TB_USER_ROLE (
   ID                   BIGSERIAL            NOT NULL,
   USER_ID              BIGINT               NULL,
   ROLE_ID              BIGINT               NULL,
   CONSTRAINT PK_USER_ROLE PRIMARY KEY (ID)
);

CREATE UNIQUE INDEX IF NOT EXISTS UK_USER_ROLE ON TB_USER_ROLE (USER_ID, ROLE_ID);

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM PG_CONSTRAINT WHERE CONNAME = 'fk_user_role_role') THEN
        ALTER TABLE TB_USER_ROLE
        ADD CONSTRAINT FK_USER_ROLE_ROLE FOREIGN KEY (ROLE_ID)
        REFERENCES TB_ROLE (ID)
        ON DELETE RESTRICT ON UPDATE RESTRICT;
    END IF;
END; $$;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM PG_CONSTRAINT WHERE CONNAME = 'fk_user_role_user') THEN
        ALTER TABLE TB_USER_ROLE
        ADD CONSTRAINT FK_USER_ROLE_USER FOREIGN KEY (USER_ID)
        REFERENCES TB_USER (ID)
        ON DELETE RESTRICT ON UPDATE RESTRICT;
    END IF;
END; $$;