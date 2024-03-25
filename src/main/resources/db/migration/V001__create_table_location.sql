CREATE TABLE IF NOT EXISTS IMOB_LOCATION (
   ID               UUID                 NOT NULL DEFAULT Uuid_generate_v4(),
   LAT              VARCHAR              NOT NULL,
   LNG              VARCHAR              NOT NULL,
   CREATED_AT       TIMESTAMP            NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CONSTRAINT PK_IMOB_LOCATION PRIMARY KEY (ID)
);