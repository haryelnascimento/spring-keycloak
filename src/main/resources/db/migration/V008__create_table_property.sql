CREATE TABLE IF NOT EXISTS IMOB_PROPERTY (
    ID                          UUID            NOT NULL DEFAULT Uuid_generate_v4(),
    BUILDING_TYPE               VARCHAR(45)     NULL,
    PROPERTY_TYPE               VARCHAR(45)     NULL,
    NUMBER_ROOMS                INT             NULL,
    NUMBER_BATHROOMS            INT             NULL,
    NUMBER_PARKING_SPACES       INT             NULL,
    PRIVATE_AREA                DECIMAL(10,2)   NULL,
    TOTAL_AREA                  DECIMAL(10,2)   NULL,
    REGISTRATION_NUMBER         VARCHAR(45)     NULL,
    PROPERTY_VALUE              DECIMAL(10,2)   NULL,
    MONTHLY_CONDOMINIUM_FEE     DECIMAL(10,2)   NULL,
    ANNUAL_PROPERTY_TAX         DECIMAL(10,2)   NULL,
    REGISTRY_OFFICE             VARCHAR(45)     NULL,
    OBSERVATION                 VARCHAR(2000)   NULL,
    ADDRESS_ID                  UUID            NOT NULL,
    PARTNER_ID                  UUID            NOT NULL,
    CREATED_AT                  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT                  TIMESTAMP       NULL,
    CONSTRAINT PK_IMOB_PROPERTY PRIMARY KEY (ID)
);

DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM PG_CONSTRAINT WHERE CONNAME = 'FK_IMOB_PROPERTY_ADDRESS') THEN
            ALTER TABLE IMOB_PROPERTY
                ADD CONSTRAINT FK_IMOB_PROPERTY_ADDRESS FOREIGN KEY (ADDRESS_ID)
                    REFERENCES IMOB_ADDRESS (ID)
                    ON DELETE RESTRICT ON UPDATE RESTRICT;
        END IF;
    END;
$$;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM PG_CONSTRAINT WHERE CONNAME = 'FK_PROPERTY_PARTNER') THEN
        ALTER TABLE IMOB_PROPERTY ADD CONSTRAINT FK_PROPERTY_PARTNER FOREIGN KEY (PARTNER_ID)
        REFERENCES IMOB_PARTNER (ID) ON DELETE RESTRICT ON UPDATE RESTRICT;
    END IF;
END; $$;