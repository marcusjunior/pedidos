package com.eicon.pedidos.resources.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PedidoDTO {

    private Long numeroControle;
    private LocalDate dataCadastro;
    private String nomeProduto;
    private Double valorUnitarioProduto;
    private Integer quantidadeProduto;
    private Long codigoCliente;
    private Double valorTotalPedido;

}
