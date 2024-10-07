package com.example.alugueldeveiculos.service;

import com.example.alugueldeveiculos.model.AutomovelEntity;
import com.example.alugueldeveiculos.model.ImagemEntity;
import com.example.alugueldeveiculos.repository.AutomovelRepository;
import com.example.alugueldeveiculos.repository.ImagemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AutomovelService {

    @Autowired
    private AutomovelRepository automovelRepository;

    @Autowired
    private ImagemRepository imagemRepository;


    public List<AutomovelEntity> getAllAutomoveis(){
        return automovelRepository.findAll();
    }


//    public  AutomovelEntity inserirAutomovel(AutomovelEntity automovel){
//        return automovelRepository.save(automovel);
//    }

    public void excluirAutomovel(Long id){
        automovelRepository.deleteById(id);
    }

    public List<AutomovelEntity> buscaPorMatricula(Long matricula){
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

    @Transactional
    public AutomovelEntity inserirAutomovel(AutomovelEntity automovel) {
        // Salvar o automóvel
        AutomovelEntity savedAutomovel = automovelRepository.save(automovel);

        // Se houver imagens, associe as URLs
        if (automovel.getImagens() != null) {
            for (ImagemEntity imagem : automovel.getImagens()) {
                imagem.setAutomovel(savedAutomovel); // Associa a imagem ao automóvel
                imagemRepository.save(imagem);
            }
        }

        return savedAutomovel;
    }


    private String saveImage(MultipartFile arquivo) throws IOException {
        // Defina o diretório onde você deseja salvar as imagens
        String directoryPath = "/path/to/save/"; // Altere para o seu diretório desejado
        File directory = new File(directoryPath);

        // Cria o diretório se não existir
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Cria o arquivo e o salva
        String filePath = directoryPath + arquivo.getOriginalFilename();
        File file = new File(filePath);
        arquivo.transferTo(file);

        // Retorne a URL ou caminho da imagem
        return filePath; // Ou retorne uma URL se você estiver servindo as imagens de um servidor
    }

}
