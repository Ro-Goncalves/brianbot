package com.rgbrain.brianbot.domain.brian.infrastructure.model.entity;

import java.time.LocalDateTime;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "falhas_requisicao")
@Table(name = "falhas_requisicao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FalhaRequisicao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String tipoRequisicao;
    private String url;
    private String mensagemErro;
    
    @Column(length = 4000)
    private String stackTrace;
    
    private LocalDateTime dataHora;
}
