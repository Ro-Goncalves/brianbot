package com.rgbrain.brianbot.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class BrianBotController {

    @GetMapping("")
    public String welcome() {
        return "Bem-vindo ao BrianBot!";
    }
}