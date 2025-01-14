CREATE TABLE consorciados (
    ID_Consorciado       INTEGER     PRIMARY KEY AUTOINCREMENT,
    NM_Consorciado       VARCHAR(80) NOT NULL,
    WhatsApp_Consorciado VARCHAR(13) NOT NULL,
    Email_Consorciado    VARCHAR(100) NOT NULL,
    DS_Personalidade     TEXT,
    DS_Info_Pessoais     TEXT,
    DS_Objetivo          TEXT
);