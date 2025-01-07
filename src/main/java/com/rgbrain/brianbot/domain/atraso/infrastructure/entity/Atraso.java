package com.rgbrain.brianbot.domain.atraso.infrastructure.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "atraso")
@Entity(name = "Atraso")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "idAtraso")
public class Atraso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Atraso")
    private Long idAtraso;

    @Column(name= "ID_Comissionado")
    private Long idComissionado;

    @Column(name= "NM_Consorciado")
    private String nomeConsorciado;

    @Column(name = "Telefone_Consorciado")
    private String telefoneConsorciado;

    @Column(name = "Email_Consorciado")
    private String emailConsorciado;

    @Column(name = "VL_Credito")
    private Double valorCredito;

    @Column(name = "VL_Atraso")
    private Double valorAtraso;

    @Column(name = "VL_Parcela")
    private Double valorParcela;

    @Column(name = "DT_Vencimento")
    private LocalDate dataVencimento;

    @Column(name = "NM_Bem")
    private String nomeBem;

    @Column(name = "QTD_Parcelas_Atraso")
    private Integer quantidadeParcelasAtraso;

    @Column(name = "DT_Atraso")
    private LocalDate dataAtraso;

    @Column(name = "CD_Grupo")
    private String codigoGrupo;

    @Column(name = "CD_Cota")
    private String codigoCota;
    
    @Column(name = "Versao")
    private String versao;

}
