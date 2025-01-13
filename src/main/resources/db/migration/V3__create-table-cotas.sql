CREATE TABLE cotas (
    ID_Cota       INTEGER     PRIMARY KEY AUTOINCREMENT,
    CD_Grupo      VARCHAR(6)  NOT NULL,
    CD_Cota       INTEGER     NOT NULL,
    Versao        INTEGER     NOT NULL,
    VL_Credito    REAL        NOT NULL,
    VL_Parcela    REAL        NOT NULL,
    DT_Vencimento DATETIME    NOT NULL,
    NM_Bem        VARCHAR(80) NOT NULL
);