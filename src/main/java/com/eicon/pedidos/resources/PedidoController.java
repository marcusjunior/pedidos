package com.eicon.pedidos.resources;

import com.eicon.pedidos.application.Consulta;
import com.eicon.pedidos.application.PedidoService;
import com.eicon.pedidos.resources.dto.PedidoDTO;
import com.eicon.pedidos.resources.dto.PedidoResource;
import com.eicon.pedidos.resources.translate.PedidoTranslator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/pedidos")
@Api(tags = "Pedidos")
public class PedidoController {

    public static final Integer dez = 10;

    @Autowired
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Criar pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pedido criado com sucesso.", response = PedidoResource.class),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 500, message = "Erro interno.")
    })
    public ResponseEntity criarPedido(@RequestBody @Validated PedidoResource pedidoResource) throws DatatypeConfigurationException {

        return pedidoService
                .criarPedido(PedidoTranslator.paraModelo(pedidoResource))
                .map(pedido -> ResponseEntity.ok().build())
                .orElseThrow(RuntimeException::new);
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Pesquisar pedido por id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna pedido com o id informado.", response = PedidoResource.class),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Pedido não existe."),
            @ApiResponse(code = 500, message = "Erro interno.")
    })
    public ResponseEntity<PedidoDTO> pesquisarPorId(@PathVariable Long id) {

        return pedidoService
                .pesquisarPorId(id)
                .map(PedidoTranslator::paraDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Pesquisar pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido retornados com sucesso.", response = PedidoDTO.class),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 500, message = "Erro interno.")
    })
    public ResponseEntity<List<PedidoDTO>> pesquisar(Consulta consulta) {

        List<PedidoDTO> pedidos =
            PedidoTranslator
                .paraListaDTO(pedidoService.pesquisar(consulta));

        return ResponseEntity.ok(pedidos);
    }

    @PostMapping(
            path="/lote",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Criar pedidos em lote")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pedido criados com sucesso.", response = PedidoResource.class),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 500, message = "Erro interno.")
    })
    public ResponseEntity criarPedidosLote(
            @RequestBody List<PedidoResource> pedidosResource) throws DatatypeConfigurationException {

        if(pedidosResource.size() > dez){
            throw new RuntimeException("O arquivo pode conter no máximo 10 pedidos");
        }

        pedidoService
            .criarPedidoLote(pedidosResource);

        return ResponseEntity.ok().build();
    }

}
