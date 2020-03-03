package com.eicon.pedidos.application;

import com.eicon.pedidos.domain.model.Pedido;
import com.eicon.pedidos.resources.dto.PedidoResource;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface PedidoService {

    Optional<Pedido> criarPedido(@NotNull Pedido pedido);
    Optional<Pedido> pesquisarPorId(@NotNull Long id);
    List<Pedido> pesquisar(@NotNull Consulta consulta);
    void criarPedidoLote(@NotNull List<PedidoResource> pedidoResources);

}
