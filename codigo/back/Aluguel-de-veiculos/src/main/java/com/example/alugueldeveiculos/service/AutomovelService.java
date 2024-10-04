package com.example.alugueldeveiculos.service;

import com.example.alugueldeveiculos.model.AutomovelEntity;
import com.example.alugueldeveiculos.repository.AutomovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutomovelService {

    @Autowired
    private AutomovelRepository automovelRepository;

    public List<AutomovelEntity> getAllAutomoveis(){
        return automovelRepository.findAll();
    }


    public  AutomovelEntity inserirAutomovel(AutomovelEntity automovel){
        return automovelRepository.save(automovel);
    }

    public void excluirAutomovel(Long id){
        automovelRepository.deleteById(id);
    }

    public List<AutomovelEntity> buscaPorMatricula(String matricula){
        return automovelRepository.findByMatricula(matricula);
    }

    public List<AutomovelEntity> buscaPorPlaca(String placa){
        return automovelRepository.findByplaca(placa);
    }

    public List<AutomovelEntity> buscarPorMarca(String marca){
        return automovelRepository.findByMarca(marca);
    }

    public List<AutomovelEntity> buscarPorModeloEAno(String modelo, int ano){
        return automovelRepository.findByModeloAndAno(modelo, ano);
    }







}
