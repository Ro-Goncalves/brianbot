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

@Table(name = "consorciados")
@Entity(name = "Consorciado")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Consorciado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Consorciado")
    @ToString.Exclude
    private Long idConsorciado;

    @Column(name = "NM_Consorciado")
    private String nomeConsorciado;   

    @Column(name = "WhatsApp_Consorciado")
    private String whatsAppConsorciado;

    @Column(name = "Email_Consorciado")
    private String emailConsorciado;

    @Column(name = "DS_Personalidade")
    private String personalidadeConsorciado;

    @Column(name = "DS_Info_Pessoais")
    private String informacoesPessoaisConsorciado;

    @Column(name = "DS_Objetivo")
    private String objetivoConsorciado;
    
}

