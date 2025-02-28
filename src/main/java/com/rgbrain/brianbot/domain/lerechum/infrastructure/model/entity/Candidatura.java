package com.rgbrain.brianbot.domain.lerechum.infrastructure.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "candidaturas")
@Entity(name = "Candidatura")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Candidatura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "vaga_id")
    private Long vagaId;
    
    private String nome;
    private String email;
    private String telefone;
    private String linkedin;
    private String cidade;
    private String estado;
    private String pretensaoSalarial;
    private String disponibilidade;
    
    @Column(columnDefinition = "TEXT")
    private String sobreMim;
    
    @Column(columnDefinition = "TEXT")
    private String experiencia;
    
    @Column(columnDefinition = "TEXT")
    private String habilidades;
    
    private String curriculoNome;
    private String curriculoTipo;
    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] curriculoArquivo;
    
    @Enumerated(EnumType.STRING)
    private StatusCandidatura status = StatusCandidatura.RECEBIDA;
    
    @Column(name = "data_candidatura")
    private LocalDateTime dataCandidatura = LocalDateTime.now();
    
    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao = LocalDateTime.now();
    
    public enum StatusCandidatura {
        RECEBIDA,
        EM_ANALISE,
        APROVADA_PARA_ENTREVISTA,
        ENTREVISTADA,
        APROVADA,
        REPROVADA
    }

    public void setCurriculoNome(String curriculoNome) {
        this.curriculoNome = curriculoNome;
    }

    public void setCurriculoTipo(String curriculoTipo) {
        this.curriculoTipo = curriculoTipo;
    }

    public void setCurriculoArquivo(byte[] curriculoArquivo) {
        this.curriculoArquivo = curriculoArquivo;
    }
    
}
