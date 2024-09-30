package com.aluguel.sistema_de_aluguel_de_carros.controller;

import com.aluguel.sistema_de_aluguel_de_carros.model.AutomovelEntity;
import com.aluguel.sistema_de_aluguel_de_carros.repository.AutomovelRepository;
import com.aluguel.sistema_de_aluguel_de_carros.service.AutomovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auto")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;

    @GetMapping
    public List<AutomovelEntity> getAllAutomoveis(){//Consulta do banco de dados por completo
        return automovelService.getAllAutomoveis();
    }

    @PostMapping
    public AutomovelEntity inserirAutomovel(@RequestBody AutomovelEntity automovel){
        return automovelService.inserirAutomovel(automovel);
    }

    @DeleteMapping("/{id}")
    public void excluirAutomovel(@PathVariable long id){
        automovelService.excluirAutomovel(id);

    }

    @GetMapping("/buscaPorMatricula/{matricula}")
    public List<AutomovelEntity> buscarVeiculoPorMatricula(@PathVariable String matricula){
        return automovelService.buscaPorMatricula(matricula);
    }

    @GetMapping("/buscarPorPlaca/{placa}")
    public List<AutomovelEntity> buscaVeiculoPorPlaca(@PathVariable String placa){
        return automovelService.buscaPorPlaca(placa);
    }

    @GetMapping("/buscarPorMarca/{marca}")
    public List<AutomovelEntity> buscaVeiculoPorMarca(@PathVariable String marca){
        return automovelService.buscarPorMarca(marca);
    }






}
