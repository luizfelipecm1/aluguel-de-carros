package com.example.alugueldeveiculos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "auto_entity")
public class AutomovelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;

    public String placa;

    public int ano;

    public String marca;

    public String modelo;

    public AutomovelEntity(){}

    public AutomovelEntity(String matricula, String placa, int ano, String marca, String modelo, Long id) {
        this.matricula = matricula;
        this.placa = placa;
        this.ano = ano;
        this.marca = marca;
        this.modelo = modelo;
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
