package com.rgbrain.brianbot.application.saciweb;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rgbrain.brianbot.domain.saci.infrastructure.ConsorciadoRepository;
import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Consorciado;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/saci-web")
public class SaciWebControlle {

    @Autowired
    private ConsorciadoRepository consorciadoRepository;

    @Autowired
    private OpenAiChatModel chatModel;
   
    @GetMapping
    public String listarConsorciados(Model model) {
        List<Consorciado> consorciados = consorciadoRepository.findAll();
        model.addAttribute("consorciados", consorciados);
        return "saci-index"; // Nome do template Thymeleaf
    }

    @GetMapping("/ajuda-cancelamento/{id}")
    @ResponseBody
    public String gerarMensagemAjudaCancelamento(@PathVariable Long id) throws IOException {
        var templateMensagem = new String(this.getClass()
            .getResourceAsStream("/templates/mensagem/saci_mensagem_atraso.txt")
            .readAllBytes());
            
        Optional<Consorciado> consorciadoOptional = consorciadoRepository.findById(id);
        
        return consorciadoOptional.map(consorciado -> {
            // Lógica para gerar mensagem personalizada de ajuda
            return gerarMensagemPersonalizada(consorciado);
        }).orElse("Consorciado não encontrado.");
    }

    private String gerarMensagemPersonalizada(Consorciado consorciado) {
        // Exemplo de geração de mensagem personalizada
        StringBuilder mensagem = new StringBuilder();
        
        mensagem.append("Olá ").append(consorciado.getNomeConsorciado()).append(",\n\n");
        
        mensagem.append("Entendemos que você está considerando o cancelamento do consórcio. ");
        
        // Personalização baseada no objetivo do consorciado
        if (consorciado.getObjetivoConsorciado() != null) {
            mensagem.append("Vimos que seu objetivo inicial era: ")
                   .append(consorciado.getObjetivoConsorciado())
                   .append(". ");
        }
        
        mensagem.append("Gostaríamos de ajudar você a encontrar a melhor solução para sua situação. ");
        
        // Personalização baseada na personalidade
        switch (consorciado.getPersonalidadeConsorciado()) {
            case "PROATIVA":
                mensagem.append("Como notamos que você é uma pessoa proativa, ")
                       .append("podemos discutir alternativas que se alinhem com seus objetivos.");
                break;
            case "ANALITICA":
                mensagem.append("Entendemos que, como pessoa analítica, ")
                       .append("você precisa de informações claras sobre as opções disponíveis.");
                break;
            case "CRIATIVA":
                mensagem.append("Sua personalidade criativa nos inspira a pensar ")
                       .append("em soluções personalizadas para sua situação.");
                break;
            case "COLABORATIVA":
                mensagem.append("Estamos abertos a colaborar e encontrar ")
                       .append("a melhor estratégia junto com você.");
                break;
            default:
                mensagem.append("Queremos trabalhar em conjunto para encontrar a melhor solução.");
        }
        
        mensagem.append("\n\nPor favor, entre em contato conosco pelo WhatsApp ")
               .append(consorciado.getWhatsappConsorciado())
               .append(" para mais detalhes.");
        
        return mensagem.toString();
    }
}