package com.eicon.pedidos.resources.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PedidoResource {

    private Long numeroControle;
    private LocalDate dataCadastro;
    private String nomeProduto;
    private Double valorUnitarioProduto;
    private Integer quantidadeProduto;
    private Long codigoCliente;

}
