package com.eicon.pedidos.port.adapter.repository;

import com.eicon.pedidos.application.Consulta;
import com.eicon.pedidos.domain.model.Pedido;
import com.eicon.pedidos.domain.model.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

public class PedidoRepositoryImpl implements PedidoRepository {

    @Autowired
    private PedidoJpaRepository pedidoJpaRepository;


    public PedidoRepositoryImpl(PedidoJpaRepository pedidoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
    }

    @Override
    public Optional<Pedido> salvar(@NotNull Pedido pedido) {
        return Optional.ofNullable(pedidoJpaRepository.save(pedido));
    }

    @Override
    public Optional<Pedido> pesquisarPorId(@NotNull Long id) {
        return pedidoJpaRepository
                .findById(id);
    }

    @Override
    public List<Pedido> pesquisar(@NotNull Consulta consulta) {

        if((isNull(consulta.getNumeroControle())) &&
           (isNull(consulta.getCodigoCliente())) &&
           (isNull(consulta.getDataCadastro()))
        ){
            return pedidoJpaRepository
                    .findAll();
        }

        return pedidoJpaRepository
                .findAll(new PedidoSpecification(consulta));
    }

}
