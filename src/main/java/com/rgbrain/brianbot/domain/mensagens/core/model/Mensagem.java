package com.rgbrain.brianbot.domain.mensagens.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Table(name = "mensagens")
@Entity(name = "Mensagem")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "idMensagem")
@ToString(exclude = "idMensagem")
public class Mensagem {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensagem;

    private String telefoneRemente;
    private String telefoneResposta;
    private String nomeRemetente;
    private String mensagem;

    @Enumerated(EnumType.STRING)
    private TipoMensagem tipoMensagem;
}