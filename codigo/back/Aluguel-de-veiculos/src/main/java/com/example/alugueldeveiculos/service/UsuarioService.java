package com.example.alugueldeveiculos.service;

import com.example.alugueldeveiculos.model.UsuarioEntity;
import com.example.alugueldeveiculos.model.enums.UsuarioEnum;
import com.example.alugueldeveiculos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> getUsuarioById(int id){
        return usuarioRepository.findById(id);
    }

    public List<UsuarioEntity> getUsuarioByName(String nome){
        return usuarioRepository.findByNome(nome);
    }

    public UsuarioEntity saveUsuario(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

     public List<UsuarioEntity> getClientes() {
        return usuarioRepository.findByRole(UsuarioEnum.CLIENTE);
     }

     public List<UsuarioEntity> getAgentes(){
        return usuarioRepository.findByRole(UsuarioEnum.AGENTE);
     }

    public UsuarioEntity updateUsuario(int id, UsuarioEntity usuarioDetails){
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setNome(usuarioDetails.getNome());
        usuario.setCpf(usuarioDetails.getCpf());
        usuario.setEndereco(usuarioDetails.getEndereco());
        usuario.setProfissao(usuarioDetails.getProfissao());
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(int id){
        usuarioRepository.deleteById(id);
    }
}
