package com.rgbrain.brianbot.domain.saci.infrastructure.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cotas")
@Entity(name = "Cota")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "idCota")
public class Cota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cota")
    private Long idCota;
    
    @Column(name = "CD_Grupo")
    private String codigoGrupo;
    
    @Column(name = "CD_Cota")
    private String codigoCota;
    
    @Column(name = "Versao")
    private String versao;
    
    @Column(name = "VL_Credito")
    private Double valorCredito;
    
    @Column(name = "VL_Parcela")
    private Double valorParcela;
    
    @Column(name = "DT_Vencimento")
    private LocalDate dataVencimento;
    
    @Column(name = "NM_Bem")
    private String nomeBem;
    
}
