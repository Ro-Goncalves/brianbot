package com.rgbrain.brianbot.domain.lerechum.infrastructure.model.entity;

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

@Table(name = "vagas")
@Entity(name = "Vaga")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String departamento;
    private String tipoContratacao;
    private String modalidade;
    private String localizacao;
    private String senioridade;
    private String faixaSalarial;
    private LocalDate dataLimite;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(columnDefinition = "TEXT")
    private String requisitosObrigatorios;
    
    @Column(columnDefinition = "TEXT")
    private String requisitosDesejaveis;
    
    private String formacaoAcademica;
    
    @Column(columnDefinition = "TEXT")
    private String beneficios;
    
    @Column(columnDefinition = "TEXT")
    private String etapasProcesso;
    
    private String responsavelNome;
    private String responsavelEmail;
    private boolean publicada;
    
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;
    
}
