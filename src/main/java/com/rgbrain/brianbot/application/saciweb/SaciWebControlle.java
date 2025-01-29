package com.rgbrain.brianbot.application.saciweb;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rgbrain.brianbot.domain.saci.infrastructure.AtrasoRepository;
import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Atraso;
import com.rgbrain.brianbot.domain.saci.infrastructure.entity.Consorciado;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/saci-web")
public class SaciWebControlle {

    @Autowired
    private AtrasoRepository atrasoRepository;

    @Autowired
    private OpenAiChatModel chatModel;
   
    @GetMapping
    public String listarAtrasos(Model model) {
        List<Atraso> atrasos = atrasoRepository.findAll();
        model.addAttribute("atrasos", atrasos);
        return "saci-index"; // Nome do template Thymeleaf
    }

    @GetMapping("/atrasos/detalhes/{id}")
    @ResponseBody
    public Map<String, Object> getDetalhesAtraso(@PathVariable Long id) {
        Optional<Atraso> atrasoOptional = atrasoRepository.findById(id);
        
        return atrasoOptional.map(atraso -> {
            Map<String, Object> detalhes = new HashMap<>();
            detalhes.put("valorAtraso", atraso.getValorAtraso());
            detalhes.put("quantidadeParcelas", atraso.getQuantidadeParcelasAtraso());
            detalhes.put("dataAtraso", atraso.getDataAtraso().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            detalhes.put("nomeConsorciado", atraso.getConsorciado().getNomeConsorciado());
            detalhes.put("nomeComissionado", atraso.getComissionado().getNomeComissionado());
            return detalhes;
        }).orElse(Collections.singletonMap("erro", "Atraso não encontrado"));
    }

    @GetMapping("/atrasos/mensagem-contato/{id}")
    @ResponseBody
    public String gerarMensagemContato(@PathVariable Long id) {
        Optional<Atraso> atrasoOptional = atrasoRepository.findById(id);
        
        return atrasoOptional.map(atraso -> {
            return gerarMensagemPersonalizadaAtraso(atraso);
        }).orElse("Atraso não encontrado.");
    }

    private String gerarMensagemPersonalizadaAtraso(Atraso atraso) {
        try {
            var templateMensagem = new String(this.getClass()
            .getResourceAsStream("/templates/mensagem/saci_mensagem_atraso.txt")
            .readAllBytes());

            var prompt = templateMensagem.formatted(
                atraso.getConsorciado().getNomeConsorciado(),
                atraso.getConsorciado().getPersonalidadeConsorciado(),
                atraso.getConsorciado().getInformacoesPessoaisConsorciado(),
                atraso.getConsorciado().getObjetivoConsorciado(),
                atraso.getQuantidadeParcelasAtraso(),
                atraso.getComissionado().getNomeComissionado()
            );
       
            var mensagemResposta = chatModel.call(prompt);

            return mensagemResposta;
        } catch (Exception e) {
            return "Erro ao gerar mensagem personalizada. Tente novamente.";
        }
       
    }
}