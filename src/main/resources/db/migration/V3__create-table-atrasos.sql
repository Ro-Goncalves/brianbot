CREATE TABLE atrasos (
    ID_Atraso           INTEGER     PRIMARY KEY AUTOINCREMENT,
    ID_Comissionado     INTEGER     NOT NULL,
    ID_Consorciado      INTEGER     NOT NULL,
    QTD_Parcelas_Atraso INTEGER     NOT NULL,
    VL_Atraso           REAL        NOT NULL, 
    DT_Atraso           DATETIME    NOT NULL,
    CD_Grupo            VARCHAR(6)  NOT NULL,
    CD_Cota             INTEGER     NOT NULL,
    Versao              INTEGER     NOT NULL,
    VL_Credito          REAL        NOT NULL,
    VL_Parcela          REAL        NOT NULL,
    DT_Vencimento       DATETIME    NOT NULL,
    NM_Bem              VARCHAR(80) NOT NULL
);