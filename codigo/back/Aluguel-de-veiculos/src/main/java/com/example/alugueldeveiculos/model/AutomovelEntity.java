package com.example.alugueldeveiculos.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "auto_entity")
public class AutomovelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matricula;

    public String placa;

    public int ano;

    public String marca;

    public String modelo;

    @OneToMany(mappedBy = "automovel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImagemEntity> imagens;

    public AutomovelEntity(){}

    public AutomovelEntity(Long matricula, String placa, int ano, String marca, String modelo, Long id, List<ImagemEntity> imagens) {
        this.matricula = matricula;
        this.placa = placa;
        this.ano = ano;
        this.marca = marca;
        this.modelo = modelo;
        this.id = id;
        this.imagens = imagens;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
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

    public List<ImagemEntity> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemEntity> imagens) {
        this.imagens = imagens;
    }
}
