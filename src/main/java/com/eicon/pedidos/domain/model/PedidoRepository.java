package com.eicon.pedidos.domain.model;

import com.eicon.pedidos.application.Consulta;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository {

    Optional<Pedido> salvar(@NotNull Pedido pedido);
    Optional<Pedido> pesquisarPorId(@NotNull Long id);
    List<Pedido> pesquisar(@NotNull Consulta consulta);

}
