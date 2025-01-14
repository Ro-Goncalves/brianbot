package com.rgbrain.brianbot.domain.saci.infrastructure.entity;

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
import lombok.ToString;

@Table(name = "comissionados")
@Entity(name = "Comissionado")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Comissionado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Comissionado")
    @ToString.Exclude
    private Long idComissionado;

    @Column(name = "NM_Comissionado")
    private String nomeComissionado;   

    @Column(name = "WhatsApp_Comissionado")
    private String whatsAppComissionado;
    
}
