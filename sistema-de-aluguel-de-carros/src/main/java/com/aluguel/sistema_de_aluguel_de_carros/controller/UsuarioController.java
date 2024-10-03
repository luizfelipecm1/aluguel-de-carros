package com.aluguel.sistema_de_aluguel_de_carros.controller;

import com.aluguel.sistema_de_aluguel_de_carros.model.UsuarioEntity;
import com.aluguel.sistema_de_aluguel_de_carros.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public UsuarioEntity createUsuario(@RequestBody UsuarioEntity usuario){
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping("/clientes")
    public List<UsuarioEntity> getAllClientes(){
        return usuarioService.getClientes();
    }

    @GetMapping("/agentes")
    public List<UsuarioEntity> getAllAgentes(){
        return usuarioService.getAgentes();
    }

    @GetMapping
    public List<UsuarioEntity> getAllUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/buscarPorNome/{nome}")
    public List<UsuarioEntity> buscarUsuarioPorNome(@PathVariable String nome){
        return usuarioService.getUsuarioByName(nome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> getUsuarioById(@PathVariable int id) {
        Optional<UsuarioEntity> usuario = usuarioService.getUsuarioById(id);
        if(usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id) {
        try{
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
