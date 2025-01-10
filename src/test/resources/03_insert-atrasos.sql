INSERT INTO atrasos(ID_Atraso, ID_Comissionado, ID_Consorciado, QTD_Parcelas_Atraso, VL_Atraso, DT_Atraso, CD_Grupo, CD_Cota, Versao, VL_Credito, VL_Parcela, DT_Vencimento, NM_Bem)
VALUES
    (1, 1, 1, 2, 2500.00, '2024-07-26', 'G001', 'C001', 1, 50000.00, 1250.00, '2024-06-26', 'Veículo'), -- Ana Silva (Comissionado: Rodrigo)
    (2, 1, 3, 1, 1800.00, '2024-07-26', 'G002', 'C005', 1, 90000.00, 1800.00, '2024-07-15', 'Imóvel'), -- Carla Rodrigues (Comissionado: Rodrigo)
    (3, 2, 2, 3, 900.00, '2024-07-26', 'G003', 'C010', 1, 30000.00, 300.00, '2024-05-26', 'Serviço'), -- Bruno Oliveira (Comissionado: Carlos)
    (4, 1, 1, 1, 1250.00, '2024-07-26', 'G001', 'C002', 1, 50000.00, 1250.00, '2024-07-10', 'Veículo'), -- Ana Silva (Comissionado: Rodrigo) - Outra cota em atraso
    (5, 2, 5, 2, 3200.00, '2024-07-26', 'G004', 'C015', 1, 80000.00, 1600.00, '2024-06-15', 'Imóvel'), -- Erika Pereira (Comissionado: Carlos)
    (6, 1, 7, 1, 750.00, '2024-07-26', 'G005', 'C020', 1, 25000.00, 750.00, '2024-07-01', 'Serviço'), -- Gabriela Almeida (Comissionado: Rodrigo)
    (7, 2, 8, 4, 4400.00, '2024-07-26', 'G006', 'C025', 1, 110000.00, 1100.00, '2024-04-26', 'Imóvel'), -- Henrique Martins (Comissionado: Carlos)
    (8, 1, 4, 2, 1600.00, '2024-07-26', 'G007', 'C030', 1, 40000.00, 800.00, '2024-06-01', 'Veículo'); -- Daniel Santos (Comissionado: Rodrigo)