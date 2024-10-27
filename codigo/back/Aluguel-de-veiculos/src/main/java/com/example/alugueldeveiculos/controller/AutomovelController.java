package com.example.alugueldeveiculos.controller;

import com.example.alugueldeveiculos.model.AutomovelEntity;
import com.example.alugueldeveiculos.service.AutomovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "${frontend.url}") // URL do front-end configurada no application.properties
@RequestMapping("/auto")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;

    @Value("${imagem.diretorio.salvar}")
    private String diretorioImagens;

    @Value("${backend.url.imagens}")
    private String urlBaseImagens;

    @GetMapping
    public List<AutomovelEntity> getAllAutomoveis() {
        return automovelService.getAllAutomoveis();
    }

    @PostMapping
    public ResponseEntity<AutomovelEntity> inserirAutomovel(@RequestBody AutomovelEntity automovel) {
        if (automovel.getMarca() == null || automovel.getPlaca() == null) {
            return ResponseEntity.badRequest().build();
        }
        
        AutomovelEntity savedAutomovel = automovelService.inserirAutomovel(automovel);
        return ResponseEntity.ok(savedAutomovel);
    }

    private List<String> obterUrlsDasImagens(List<MultipartFile> imagens) throws IOException {
        List<String> urls = new ArrayList<>();
        if (imagens != null) {
            for (MultipartFile imagem : imagens) {
                String url = salvarImagemEObterUrl(imagem);
                urls.add(url);
            }
        }
        return urls;
    }

    private String salvarImagemEObterUrl(MultipartFile imagem) throws IOException {
        String nomeArquivo = imagem.getOriginalFilename();
        String caminhoSalvo = diretorioImagens + nomeArquivo;

        Path path = Paths.get(caminhoSalvo);
        Files.copy(imagem.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return urlBaseImagens + nomeArquivo;
    }

    @DeleteMapping("/{id}")
    public void excluirAutomovel(@PathVariable long id) {
        automovelService.excluirAutomovel(id);
    }

    @GetMapping("/buscaPorMatricula/{matricula}")
    public List<AutomovelEntity> buscarVeiculoPorMatricula(@PathVariable Long matricula) {
        return automovelService.buscaPorMatricula(matricula);
    }

    @GetMapping("/buscarPorPlaca/{placa}")
    public List<AutomovelEntity> buscaVeiculoPorPlaca(@PathVariable String placa) {
        return automovelService.buscaPorPlaca(placa);
    }

    @GetMapping("/buscarPorMarca/{marca}")
    public List<AutomovelEntity> buscaVeiculoPorMarca(@PathVariable String marca) {
        return automovelService.buscarPorMarca(marca);
    }
}
