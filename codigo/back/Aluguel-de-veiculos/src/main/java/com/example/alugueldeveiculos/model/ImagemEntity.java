package com.example.alugueldeveiculos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "imagem_entity")
public class ImagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url; // URL ou caminho da imagem

    @ManyToOne
    @JoinColumn(name = "automovel_id")
    @JsonIgnore  // Ignora a referência para evitar loop de serialização
    private AutomovelEntity automovel;

    public ImagemEntity() {}

    public ImagemEntity(String url, AutomovelEntity automovel) {
        this.url = url;
        this.automovel = automovel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AutomovelEntity getAutomovel() {
        return automovel;
    }

    public void setAutomovel(AutomovelEntity automovel) {
        this.automovel = automovel;
    }
}
