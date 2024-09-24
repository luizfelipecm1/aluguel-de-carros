package com.aluguel.sistema_de_aluguel_de_carros.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Usuario {
    private List<Pedidos> pedidos;
}
