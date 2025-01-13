CREATE TABLE atrasos (
    ID_Atraso           INTEGER     PRIMARY KEY AUTOINCREMENT,
    ID_Comissionado     INTEGER     NOT NULL,
    ID_Consorciado      INTEGER     NOT NULL,
    ID_Cota             INTEGER     NOT NULL,
    QTD_Parcelas_Atraso INTEGER     NOT NULL,
    VL_Atraso           REAL        NOT NULL, 
    DT_Atraso           DATETIME    NOT NULL,

    FOREIGN KEY (ID_Comissionado) REFERENCES comissionados (ID_Comissionado),
    FOREIGN KEY (ID_Consorciado)  REFERENCES consorciados  (ID_Consorciado),
    FOREIGN KEY (ID_Cota)         REFERENCES cotas         (ID_Cota)    
);