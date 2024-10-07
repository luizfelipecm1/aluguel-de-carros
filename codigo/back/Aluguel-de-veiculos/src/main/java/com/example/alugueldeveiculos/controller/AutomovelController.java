package com.example.alugueldeveiculos.controller;

import com.example.alugueldeveiculos.model.AutomovelEntity;
import com.example.alugueldeveiculos.service.AutomovelService;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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
    public ResponseEntity<AutomovelEntity> inserirAutomovel(@RequestBody AutomovelEntity automovel) {
        // Salvar o automóvel
        AutomovelEntity savedAutomovel = automovelService.inserirAutomovel(automovel);

        return ResponseEntity.ok(savedAutomovel);
    }


    // Método para converter MultipartFile em URLs
    private List<String> obterUrlsDasImagens(List<MultipartFile> imagens) throws IOException {
        List<String> urls = new ArrayList<>();

        if (imagens != null) {
            for (MultipartFile imagem : imagens) {
                // Lógica para salvar a imagem e obter a URL (exemplo abaixo)
                String url = salvarImagemEObterUrl(imagem);
                urls.add(url);
            }
        }

        return urls;
    }


    // Método de exemplo para salvar a imagem e retornar a URL
    private String salvarImagemEObterUrl(MultipartFile imagem) throws IOException {
        // Aqui você deve implementar a lógica de salvar a imagem (por exemplo, em um servidor ou sistema de arquivos)
        // E retornar a URL da imagem salva. Aqui está um exemplo fictício:
        String nomeArquivo = imagem.getOriginalFilename();
        String caminhoSalvo = "caminho/para/salvar/" + nomeArquivo; // Ajuste conforme necessário

        // Salve a imagem (exemplo: usando Files.copy ou outra lógica)
        Path path = Paths.get(caminhoSalvo);
        Files.copy(imagem.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        // Retorne a URL da imagem
        return "http://localhost:8080/imagens/" + nomeArquivo; // Ajuste a URL conforme necessário
    }



    @DeleteMapping("/{id}")
    public void excluirAutomovel(@PathVariable long id){
        automovelService.excluirAutomovel(id);

    }

    //consulta personalizada

    // http://localhost:8080/auto/buscarPorMatricula/(Matricula do automovel)
    @GetMapping("/buscaPorMatricula/{matricula}")
    public List<AutomovelEntity> buscarVeiculoPorMatricula(@PathVariable Long matricula){
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
