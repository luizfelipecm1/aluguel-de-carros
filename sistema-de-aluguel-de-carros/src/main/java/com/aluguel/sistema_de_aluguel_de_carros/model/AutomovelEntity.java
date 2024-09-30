package com.aluguel.sistema_de_aluguel_de_carros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auto_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutomovelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String matricula;

    public String placa;

    public int ano;

    public String marca;

    public String modelo;

    private Long id;
}
