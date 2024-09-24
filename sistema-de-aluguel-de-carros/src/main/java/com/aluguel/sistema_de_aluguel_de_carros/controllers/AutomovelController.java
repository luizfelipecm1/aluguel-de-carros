package com.aluguel.sistema_de_aluguel_de_carros.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/automovel")
public class AutomovelController{
    @GetMapping
    public ResponseEntity getAllautomovel(){
        return ResponseEntity.ok("automovel ok");
    }

}