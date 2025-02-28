package com.rgbrain.brianbot.application.lerechum;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rgbrain.brianbot.domain.lerechum.core.CandidaturaService;
import com.rgbrain.brianbot.domain.lerechum.core.VagaService;
import com.rgbrain.brianbot.domain.lerechum.infrastructure.model.entity.Candidatura;
import com.rgbrain.brianbot.domain.lerechum.infrastructure.model.entity.Vaga;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/lerechum")
@AllArgsConstructor
public class CandidaturaWebController {
    
    private VagaService vagaService;
    private CandidaturaService candidaturaService;

    @GetMapping("/nova-candidatura/{vagaId}")
    public String novaCandidatura(@PathVariable Long vagaId, Model model) {
        Vaga vaga = vagaService.buscarVagaPorId(vagaId);
        
        if (vaga == null || !vaga.isPublicada()) {
            return "redirect:/lerechum/nova-candidatura?erro=vaga-nao-encontrada";
        }
        
        model.addAttribute("vaga", vaga);
        model.addAttribute("candidatura", new Candidatura());
        
        return "lerechum/nova-candidatura";
    }

    @PostMapping("/nova-candidatura/salvar")
    public String salvarCandidatura(
            @ModelAttribute Candidatura candidatura,
            @RequestParam("arquivoCurriculo") MultipartFile arquivo,
            @RequestParam(value = "autorizaDados", required = false) boolean autorizaDados,
            RedirectAttributes redirectAttributes) {
        
        if (!autorizaDados) {
            redirectAttributes.addFlashAttribute("erro", "É necessário autorizar o tratamento dos dados pessoais.");
            return "redirect:/lerechum/nova-candidatura/" + candidatura.getVagaId();
        }
        
        try {
            if (!arquivo.isEmpty()) {
                candidatura.setCurriculoNome(arquivo.getOriginalFilename());
                candidatura.setCurriculoTipo(arquivo.getContentType());
                candidatura.setCurriculoArquivo(arquivo.getBytes());
            }
            
            candidaturaService.salvarCandidatura(candidatura);
            redirectAttributes.addFlashAttribute("mensagem", "Candidatura enviada com sucesso!");
            return "redirect:/candidaturas/sucesso";
            
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao processar o arquivo do currículo: " + e.getMessage());
            return "redirect:/candidaturas/nova/" + candidatura.getVagaId();
        }
    }
    
    @GetMapping("/sucesso")
    public String paginaSucesso() {
        return "candidatura/sucesso";
    }
    
}
