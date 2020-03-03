package com.eicon.pedidos.application;

import com.eicon.pedidos.domain.model.Pedido;
import com.eicon.pedidos.domain.model.PedidoRepository;
import com.eicon.pedidos.resources.dto.PedidoResource;
import com.eicon.pedidos.resources.translate.PedidoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Optional<Pedido> criarPedido(@NotNull Pedido pedido) {
        return pedidoRepository.salvar(pedido);
    }

    @Override
    public Optional<Pedido> pesquisarPorId(@NotNull Long id) {
        return pedidoRepository.pesquisarPorId(id);
    }

    @Override
    public List<Pedido> pesquisar(@NotNull Consulta consulta) {
        return pedidoRepository.pesquisar(consulta);
    }

    @Override
    public void criarPedidoLote(@NotNull List<PedidoResource> pedidosResource) {

        pedidosResource
            .stream()
            .forEach(
                pedidoResource -> {
                    try {
                        pedidoRepository.salvar(PedidoTranslator.paraModelo(pedidoResource))
                                .orElseThrow(RuntimeException::new);
                    } catch (DatatypeConfigurationException e) {
                        e.printStackTrace();
                    }
                }
            );
    }

}
