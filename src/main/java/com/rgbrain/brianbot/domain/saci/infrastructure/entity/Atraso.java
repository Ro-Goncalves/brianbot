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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Cota")
    private Cota cota;
    
    @Column(name = "QTD_Parcelas_Atraso")
    private Integer quantidadeParcelasAtraso;

    @Column(name = "VL_Atraso")
    private Double valorAtraso;

    @Column(name = "DT_Atraso")
    private LocalDate dataAtraso;   

    public Long getIdComissionado() {
        return comissionado.getIdComissionado();
    }
}
