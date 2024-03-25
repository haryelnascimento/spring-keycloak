CREATE TABLE IF NOT EXISTS IMOB_ADDRESS (
   ID                   UUID                 NOT NULL DEFAULT Uuid_generate_v4(),
   CEP                  VARCHAR(10)          NOT NULL,
   UF                   VARCHAR(2)           NOT NULL,
   CITY                 VARCHAR(100)         NOT NULL,
   NEIGHBORHOOD         VARCHAR(70)          NOT NULL,
   STREET               VARCHAR(70)          NOT NULL,
   NUMBER               VARCHAR(6)           NULL,
   COMPLEMENT           VARCHAR(70)          NULL,
   LOCATION_ID          UUID                 NOT NULL,
   CREATED_AT           TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP,
   UPDATED_AT           TIMESTAMP            NULL,
   CONSTRAINT PK_IMOB_ADDRESS PRIMARY KEY (ID)
);

DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM PG_CONSTRAINT WHERE CONNAME = 'FK_IMOB_ADDRESS_LOCATION') THEN
            ALTER TABLE IMOB_ADDRESS
                ADD CONSTRAINT FK_IMOB_ADDRESS_LOCATION FOREIGN KEY (LOCATION_ID)
                    REFERENCES IMOB_LOCATION (ID)
                    ON DELETE RESTRICT ON UPDATE RESTRICT;
        END IF;
    END;
$$;