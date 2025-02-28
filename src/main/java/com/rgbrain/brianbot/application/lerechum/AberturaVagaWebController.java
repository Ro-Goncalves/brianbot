package com.rgbrain.brianbot.application.lerechum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rgbrain.brianbot.domain.lerechum.core.VagaService;
import com.rgbrain.brianbot.domain.lerechum.infrastructure.model.entity.Vaga;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/lerechum")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AberturaVagaWebController {
    
    private VagaService vagaService;

    @GetMapping("/abertura-vaga")
    public String aberturaVaga(Model model) {
        model.addAttribute("vaga", new Vaga());
        return "lerechum/abertura-vaga";
    }

    @PostMapping("/abertura-vaga/salvar")
    public String salvarVaga(@ModelAttribute("vaga") Vaga vaga, RedirectAttributes redirectAttributes) {
        vagaService.salvarVaga(vaga);
        redirectAttributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
        return "redirect:/lerechum/abertura-vaga";
    }
}
