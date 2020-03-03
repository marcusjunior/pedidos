package com.eicon.pedidos.resource;

import com.eicon.pedidos.application.Consulta;
import com.eicon.pedidos.application.PedidoService;
import com.eicon.pedidos.domain.model.Pedido;
import com.eicon.pedidos.resources.PedidoController;
import com.eicon.pedidos.resources.dto.PedidoDTO;
import com.eicon.pedidos.resources.dto.PedidoResource;
import com.eicon.pedidos.resources.translate.PedidoTranslator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class PedidoControllerTest {

    @MockBean
    private PedidoService mockPedidoServiceTest;

    @MockBean
    private PedidoController pedidoControllerTest;

    @Before
    public void setUp() {
        initMocks(this);
        pedidoControllerTest = new PedidoController(mockPedidoServiceTest);
    }

    @Test
    public void testarPesquisar() {

        Consulta consulta = new Consulta();
        consulta.setCodigoCliente(1L);

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

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido1);
        pedidos.add(pedido2);

        List<PedidoDTO> pedidosDTO = new ArrayList<>();
        pedidosDTO.add(PedidoTranslator.paraDTO(pedido1));
        pedidosDTO.add(PedidoTranslator.paraDTO(pedido2));

        ResponseEntity<List<PedidoDTO>> resultadoEsperado = new ResponseEntity<>(pedidosDTO, HttpStatus.OK);
        when(mockPedidoServiceTest.pesquisar(consulta)).thenReturn(pedidos);
        ResponseEntity<List<PedidoDTO>> resultado = pedidoControllerTest.pesquisar(consulta);

        assertEquals(resultadoEsperado.getBody().get(0).getCodigoCliente(), resultado.getBody().get(0).getCodigoCliente());
        assertEquals(resultadoEsperado.getBody().get(0).getNumeroControle(), resultado.getBody().get(0).getNumeroControle());
        assertEquals(resultadoEsperado.getBody().get(0).getDataCadastro(), resultado.getBody().get(0).getDataCadastro());
        assertEquals(resultadoEsperado.getBody().get(0).getNomeProduto(), resultado.getBody().get(0).getNomeProduto());
        assertEquals(resultadoEsperado.getBody().get(0).getQuantidadeProduto(), resultado.getBody().get(0).getQuantidadeProduto());
        assertEquals(resultadoEsperado.getBody().get(0).getValorTotalPedido(), resultado.getBody().get(0).getValorTotalPedido());
        assertEquals(resultadoEsperado.getBody().get(0).getValorUnitarioProduto(), resultado.getBody().get(0).getValorUnitarioProduto());

        assertEquals(resultadoEsperado.getBody().get(1).getCodigoCliente(), resultado.getBody().get(1).getCodigoCliente());
        assertEquals(resultadoEsperado.getBody().get(1).getNumeroControle(), resultado.getBody().get(1).getNumeroControle());
        assertEquals(resultadoEsperado.getBody().get(1).getDataCadastro(), resultado.getBody().get(1).getDataCadastro());
        assertEquals(resultadoEsperado.getBody().get(1).getNomeProduto(), resultado.getBody().get(1).getNomeProduto());
        assertEquals(resultadoEsperado.getBody().get(1).getQuantidadeProduto(), resultado.getBody().get(1).getQuantidadeProduto());
        assertEquals(resultadoEsperado.getBody().get(1).getValorTotalPedido(), resultado.getBody().get(1).getValorTotalPedido());
        assertEquals(resultadoEsperado.getBody().get(1).getValorUnitarioProduto(), resultado.getBody().get(1).getValorUnitarioProduto());
    }

    @Test
    public void testarCriarPedidoLoteComMenosDeDezItens() throws DatatypeConfigurationException {

        PedidoResource pedido1 = new PedidoResource();
        pedido1.setCodigoCliente(1L);
        pedido1.setDataCadastro(LocalDate.now());
        pedido1.setNomeProduto("Produto de teste");
        pedido1.setNumeroControle(1L);
        pedido1.setQuantidadeProduto(11);
        pedido1.setValorUnitarioProduto(20.00);

        PedidoResource pedido2 = new PedidoResource();
        pedido2.setCodigoCliente(1L);
        pedido2.setDataCadastro(LocalDate.now());
        pedido2.setNomeProduto("Produto de teste");
        pedido2.setNumeroControle(1L);
        pedido2.setQuantidadeProduto(5);
        pedido2.setValorUnitarioProduto(20.00);

        List<PedidoResource> pedidos = new ArrayList<>();
        pedidos.add(pedido1);
        pedidos.add(pedido2);

        ResponseEntity resultado = pedidoControllerTest.criarPedidosLote(pedidos);
        assertEquals(resultado.getStatusCode(), HttpStatus.OK);
    }

    @Test(expected = RuntimeException.class)
    public void testarCriarPedidoLoteComMaisDeDezItens() throws DatatypeConfigurationException {

        PedidoResource pedido1 = new PedidoResource();
        PedidoResource pedido2 = new PedidoResource();
        PedidoResource pedido3 = new PedidoResource();
        PedidoResource pedido4 = new PedidoResource();
        PedidoResource pedido5 = new PedidoResource();
        PedidoResource pedido6 = new PedidoResource();
        PedidoResource pedido7 = new PedidoResource();
        PedidoResource pedido8 = new PedidoResource();
        PedidoResource pedido9 = new PedidoResource();
        PedidoResource pedido10 = new PedidoResource();
        PedidoResource pedido11 = new PedidoResource();


        List<PedidoResource> pedidos = new ArrayList<>();
        pedidos.add(pedido1);
        pedidos.add(pedido2);
        pedidos.add(pedido3);
        pedidos.add(pedido4);
        pedidos.add(pedido5);
        pedidos.add(pedido6);
        pedidos.add(pedido7);
        pedidos.add(pedido8);
        pedidos.add(pedido9);
        pedidos.add(pedido10);
        pedidos.add(pedido11);

        ResponseEntity resultado = pedidoControllerTest.criarPedidosLote(pedidos);
    }

    @Test
    public void testarPesquisarPorId() {

        Long id = 1L;

        Pedido pedido= new Pedido();
        pedido.setCodigoCliente(1L);
        pedido.setDataCadastro(LocalDate.now());
        pedido.setNomeProduto("Produto de teste");
        pedido.setNumeroControle(1L);
        pedido.setQuantidadeProduto(2);
        pedido.setValorUnitarioProduto(20.00);
        pedido.setValorTotalPedido(40.00);

        PedidoDTO pedidoDTO =
            PedidoDTO.builder()
                        .codigoCliente(1L)
                        .dataCadastro(LocalDate.now())
                        .nomeProduto("Produto de teste")
                        .numeroControle(1L)
                        .quantidadeProduto(2)
                        .valorUnitarioProduto(20.00)
                        .valorTotalPedido(40.00)
                        .build();

        ResponseEntity<PedidoDTO> resultadoEsperado = new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
        when(mockPedidoServiceTest.pesquisarPorId(id)).thenReturn(Optional.of(pedido));
        ResponseEntity<PedidoDTO> resultado = pedidoControllerTest.pesquisarPorId(id);

        assertEquals(resultadoEsperado.getBody().getCodigoCliente(), resultado.getBody().getCodigoCliente());
        assertEquals(resultadoEsperado.getBody().getNumeroControle(), resultado.getBody().getNumeroControle());
        assertEquals(resultadoEsperado.getBody().getDataCadastro(), resultado.getBody().getDataCadastro());
        assertEquals(resultadoEsperado.getBody().getNomeProduto(), resultado.getBody().getNomeProduto());
        assertEquals(resultadoEsperado.getBody().getQuantidadeProduto(), resultado.getBody().getQuantidadeProduto());
        assertEquals(resultadoEsperado.getBody().getValorTotalPedido(), resultado.getBody().getValorTotalPedido());
        assertEquals(resultadoEsperado.getBody().getValorUnitarioProduto(), resultado.getBody().getValorUnitarioProduto());
    }

    @Test
    public void testarCriarPedido() throws DatatypeConfigurationException {

        Long id = 1L;

        PedidoResource pedido= new PedidoResource();
        pedido.setDataCadastro(LocalDate.now());
        pedido.setNomeProduto("Produto de teste");
        pedido.setNumeroControle(1L);
        pedido.setQuantidadeProduto(2);
        pedido.setValorUnitarioProduto(20.00);


        ResponseEntity resultadoEsperado = new ResponseEntity<>(HttpStatus.OK);
        when(mockPedidoServiceTest.criarPedido(PedidoTranslator.paraModelo(pedido)))
            .thenReturn(Optional.of(PedidoTranslator.paraModelo(pedido)));
        ResponseEntity<PedidoDTO> resultado = pedidoControllerTest.pesquisarPorId(id);

        assertEquals(resultadoEsperado.getStatusCode(), HttpStatus.OK);
    }
}
