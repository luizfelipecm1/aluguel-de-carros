package com.example.alugueldeveiculos.model;

import com.example.alugueldeveiculos.model.enums.UsuarioEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "user_entity")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String profissao;

    @Enumerated(EnumType.STRING)
    private UsuarioEnum role;

    public boolean isCliente(){
        return this.role == UsuarioEnum.CLIENTE;
    }

    public boolean isAgente(){
        return this.role == UsuarioEnum.AGENTE;    }

    public void cadastrarPedidos(){

    }

    public void alterarPedidos(){

    }

    public void consultarPedidos(){

    }

    public void cancelarPedidos(){

    }

    public UsuarioEntity(){}

    public UsuarioEntity(Long id, String nome, String cpf, String endereco, String profissao, UsuarioEnum role) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.profissao = profissao;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public UsuarioEnum getRole() {
        return role;
    }

    public void setRole(UsuarioEnum role) {
        this.role = role;
    }
}
