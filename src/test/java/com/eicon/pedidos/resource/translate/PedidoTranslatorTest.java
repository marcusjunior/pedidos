package com.eicon.pedidos.resource.translate;

import com.eicon.pedidos.domain.model.Pedido;
import com.eicon.pedidos.resources.dto.PedidoDTO;
import com.eicon.pedidos.resources.dto.PedidoResource;
import com.eicon.pedidos.resources.translate.PedidoTranslator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PedidoTranslatorTest {

    @Test
    public void testarParaModeloSemDesconto(){

        PedidoResource from = new PedidoResource();
        from.setCodigoCliente(1L);
        from.setDataCadastro(LocalDate.now());
        from.setNomeProduto("Produto de teste");
        from.setNumeroControle(1L);
        from.setQuantidadeProduto(2);
        from.setValorUnitarioProduto(100.00);

        Pedido resultadoEsperado =
            Pedido.builder()
                    .codigoCliente(from.getCodigoCliente())
                    .dataCadastro(from.getDataCadastro())
                    .nomeProduto(from.getNomeProduto())
                    .numeroControle(from.getNumeroControle())
                    .quantidadeProduto(from.getQuantidadeProduto())
                    .valorUnitarioProduto(from.getValorUnitarioProduto())
                    .valorTotalPedido(200.00)
                    .build();

        try {
            Pedido resultado = PedidoTranslator.paraModelo(from);
            assertEquals(resultadoEsperado, resultado);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testarParaModeloComDescontoCincoPorcento(){

        PedidoResource from = new PedidoResource();
        from.setCodigoCliente(1L);
        from.setDataCadastro(LocalDate.now());
        from.setNomeProduto("Produto de teste");
        from.setNumeroControle(1L);
        from.setQuantidadeProduto(6);
        from.setValorUnitarioProduto(20.00);

        Pedido resultadoEsperado =
                Pedido.builder()
                        .codigoCliente(from.getCodigoCliente())
                        .dataCadastro(from.getDataCadastro())
                        .nomeProduto(from.getNomeProduto())
                        .numeroControle(from.getNumeroControle())
                        .quantidadeProduto(from.getQuantidadeProduto())
                        .valorUnitarioProduto(from.getValorUnitarioProduto())
                        .valorTotalPedido(114.00)
                        .build();

        try {
            Pedido resultado = PedidoTranslator.paraModelo(from);
            assertEquals(resultadoEsperado, resultado);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testarParaModeloComDescontoDezPorcento(){

        PedidoResource from = new PedidoResource();
        from.setCodigoCliente(1L);
        from.setDataCadastro(LocalDate.now());
        from.setNomeProduto("Produto de teste");
        from.setNumeroControle(1L);
        from.setQuantidadeProduto(11);
        from.setValorUnitarioProduto(20.00);

        Pedido resultadoEsperado =
                Pedido.builder()
                        .codigoCliente(from.getCodigoCliente())
                        .dataCadastro(from.getDataCadastro())
                        .nomeProduto(from.getNomeProduto())
                        .numeroControle(from.getNumeroControle())
                        .quantidadeProduto(from.getQuantidadeProduto())
                        .valorUnitarioProduto(from.getValorUnitarioProduto())
                        .valorTotalPedido(198.00)
                        .build();

        try {
            Pedido resultado = PedidoTranslator.paraModelo(from);
            assertEquals(resultadoEsperado, resultado);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testarParaDTO(){

        Pedido from = new Pedido();
        from.setCodigoCliente(1L);
        from.setDataCadastro(LocalDate.now());
        from.setNomeProduto("Produto de teste");
        from.setNumeroControle(1L);
        from.setQuantidadeProduto(5);
        from.setValorUnitarioProduto(20.00);
        from.setValorTotalPedido(200.00);

        PedidoDTO resultadoEsperado =
                PedidoDTO.builder()
                        .numeroControle(from.getNumeroControle())
                        .dataCadastro(from.getDataCadastro())
                        .nomeProduto(from.getNomeProduto())
                        .quantidadeProduto(from.getQuantidadeProduto())
                        .valorUnitarioProduto(from.getValorUnitarioProduto())
                        .valorTotalPedido(from.getValorTotalPedido())
                        .codigoCliente(from.getCodigoCliente())
                        .build();

        PedidoDTO resultado = PedidoTranslator.paraDTO(from);

        assertEquals(resultadoEsperado.getCodigoCliente(), resultado.getCodigoCliente());
        assertEquals(resultadoEsperado.getNumeroControle(), resultado.getNumeroControle());
        assertEquals(resultadoEsperado.getDataCadastro(), resultado.getDataCadastro());
        assertEquals(resultadoEsperado.getNomeProduto(), resultado.getNomeProduto());
        assertEquals(resultadoEsperado.getQuantidadeProduto(), resultado.getQuantidadeProduto());
        assertEquals(resultadoEsperado.getValorTotalPedido(), resultado.getValorTotalPedido());
        assertEquals(resultadoEsperado.getValorUnitarioProduto(), resultado.getValorUnitarioProduto());
    }

    @Test
    public void testarParaListaDTO(){

        Pedido pedido1 = new Pedido();
        pedido1.setCodigoCliente(1L);
        pedido1.setDataCadastro(LocalDate.now());
        pedido1.setNomeProduto("Produto de teste");
        pedido1.setNumeroControle(1L);
        pedido1.setQuantidadeProduto(11);
        pedido1.setValorUnitarioProduto(20.00);

        Pedido pedido2 = new Pedido();
        pedido2.setCodigoCliente(1L);
        pedido2.setDataCadastro(LocalDate.now());
        pedido2.setNomeProduto("Produto de teste");
        pedido2.setNumeroControle(1L);
        pedido2.setQuantidadeProduto(5);
        pedido2.setValorUnitarioProduto(20.00);
        pedido2.setValorTotalPedido(200.00);

        List<Pedido> from = new ArrayList<>();
        from.add(pedido1);
        from.add(pedido2);

        List<PedidoDTO> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(PedidoTranslator.paraDTO(pedido1));
        resultadoEsperado.add(PedidoTranslator.paraDTO(pedido2));

        List<PedidoDTO> resultado = PedidoTranslator.paraListaDTO(from);

        assertEquals(resultadoEsperado.get(0).getCodigoCliente(), resultado.get(0).getCodigoCliente());
        assertEquals(resultadoEsperado.get(0).getNumeroControle(), resultado.get(0).getNumeroControle());
        assertEquals(resultadoEsperado.get(0).getDataCadastro(), resultado.get(0).getDataCadastro());
        assertEquals(resultadoEsperado.get(0).getNomeProduto(), resultado.get(0).getNomeProduto());
        assertEquals(resultadoEsperado.get(0).getQuantidadeProduto(), resultado.get(0).getQuantidadeProduto());
        assertEquals(resultadoEsperado.get(0).getValorTotalPedido(), resultado.get(0).getValorTotalPedido());
        assertEquals(resultadoEsperado.get(0).getValorUnitarioProduto(), resultado.get(0).getValorUnitarioProduto());

        assertEquals(resultadoEsperado.get(1).getCodigoCliente(), resultado.get(1).getCodigoCliente());
        assertEquals(resultadoEsperado.get(1).getNumeroControle(), resultado.get(1).getNumeroControle());
        assertEquals(resultadoEsperado.get(1).getDataCadastro(), resultado.get(1).getDataCadastro());
        assertEquals(resultadoEsperado.get(1).getNomeProduto(), resultado.get(1).getNomeProduto());
        assertEquals(resultadoEsperado.get(1).getQuantidadeProduto(), resultado.get(1).getQuantidadeProduto());
        assertEquals(resultadoEsperado.get(1).getValorTotalPedido(), resultado.get(1).getValorTotalPedido());
        assertEquals(resultadoEsperado.get(1).getValorUnitarioProduto(), resultado.get(1).getValorUnitarioProduto());
    }
}