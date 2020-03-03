package com.eicon.pedidos.port.adapter.configuration;

import com.eicon.pedidos.domain.model.PedidoRepository;
import com.eicon.pedidos.port.adapter.repository.PedidoJpaRepository;
import com.eicon.pedidos.port.adapter.repository.PedidoRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public PedidoRepository pedidoRepository(PedidoJpaRepository pedidoJpaRepository){
        return new PedidoRepositoryImpl(pedidoJpaRepository);
    }

}
