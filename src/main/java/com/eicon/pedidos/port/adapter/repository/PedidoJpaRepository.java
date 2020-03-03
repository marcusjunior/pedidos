package com.eicon.pedidos.port.adapter.repository;

import com.eicon.pedidos.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoJpaRepository
        extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
}
