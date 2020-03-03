package com.eicon.pedidos.resources.translate;

import com.eicon.pedidos.domain.model.Pedido;
import com.eicon.pedidos.resources.dto.PedidoDTO;
import com.eicon.pedidos.resources.dto.PedidoResource;

import javax.validation.constraints.NotNull;
import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class PedidoTranslator {

    private static final Integer cinco = 5;
    private static final Integer dez = 10;
    private static final Integer cem = 100;

    public static Pedido paraModelo(@NotNull final PedidoResource from) throws DatatypeConfigurationException {

        if(from.getDataCadastro() == null){
            from.setDataCadastro(LocalDate.now());
        }

        if(from.getQuantidadeProduto() == null){
            from.setQuantidadeProduto(1);
        }

        Double valorTotalPedido = calcularValorTotal.apply(from.getQuantidadeProduto(), from.getValorUnitarioProduto());

        return Pedido.builder()
                .numeroControle(from.getNumeroControle())
                .dataCadastro(from.getDataCadastro())
                .nomeProduto(from.getNomeProduto())
                .quantidadeProduto(from.getQuantidadeProduto())
                .valorUnitarioProduto(from.getValorUnitarioProduto())
                .valorTotalPedido(valorTotalPedido)
                .codigoCliente(from.getCodigoCliente())
                .build();
    }

    public static PedidoDTO paraDTO(@NotNull final Pedido from){

        return PedidoDTO.builder()
                .numeroControle(from.getNumeroControle())
                .dataCadastro(from.getDataCadastro())
                .nomeProduto(from.getNomeProduto())
                .quantidadeProduto(from.getQuantidadeProduto())
                .valorUnitarioProduto(from.getValorUnitarioProduto())
                .valorTotalPedido(from.getValorTotalPedido())
                .codigoCliente(from.getCodigoCliente())
                .build();
    }

    public static List<PedidoDTO> paraListaDTO(@NotNull final List<Pedido> from){

        return from.stream()
                .map(pedido -> paraDTO(pedido))
                .collect(Collectors.toList());
    }

    private static BiFunction<Integer, Double, Double> calcularValorTotal = (quantidade, valorUnitario) -> {
        Double valorTotal = quantidade * valorUnitario;

        if(quantidade > cinco && quantidade < dez){
            Double valorDesconto = (cinco  * valorTotal) / cem;
            valorTotal = valorTotal - valorDesconto;
        }

        if(quantidade > dez){
            Double valorDesconto = (dez  * valorTotal) / cem;
            valorTotal = valorTotal - valorDesconto;
        }

        return valorTotal;
    };
}
