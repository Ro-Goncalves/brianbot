package com.rgbrain.brianbot.domain.saci.infrastructure.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "atrasos")
@Entity(name = "Atraso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "idAtraso")
public class Atraso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Atraso")
    private Long idAtraso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Comissionado")
    private Comissionado comissionado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Consorciado")
    private Consorciado consorciado;

    @Column(name = "QTD_Parcelas_Atraso")
    private Integer quantidadeParcelasAtraso;

    @Column(name = "VL_Atraso")
    private Double valorAtraso;

    @Column(name = "DT_Atraso")
    private LocalDate dataAtraso;
    
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

    public Long getIdComissionado() {
        return comissionado.getIdComissionado();
    }

}
