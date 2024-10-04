package com.example.alugueldeveiculos.controller;

import com.example.alugueldeveiculos.model.AutomovelEntity;
import com.example.alugueldeveiculos.service.AutomovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
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

    //consulta personalizada

    // http://localhost:8080/auto/buscarPorMatricula/(Matricula do automovel)
    @GetMapping("/buscaPorMatricula/{matricula}")
    public List<AutomovelEntity> buscarVeiculoPorMatricula(@PathVariable String matricula){
        return automovelService.buscaPorMatricula(matricula);
    }

    // http://localhost:8080/auto/buscarPorPlaca/(Placa do automovel)
    @GetMapping("/buscarPorPlaca/{placa}")
    public List<AutomovelEntity> buscaVeiculoPorPlaca(@PathVariable String placa){
        return automovelService.buscaPorPlaca(placa);
    }

    // http: //localhost:8080/auto/buscarPorMarca/(Marca do automovel)
    @GetMapping("/buscarPorMarca/{marca}")
    public List<AutomovelEntity> buscaVeiculoPorMarca(@PathVariable String marca){
        return automovelService.buscarPorMarca(marca);
    }






}
