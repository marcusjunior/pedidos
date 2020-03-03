package com.eicon.pedidos.domain.model;

import lombok.*;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@Entity
//@Table(name = "pedido", uniqueConstraints={@UniqueConstraint(columnNames ={"numeroControle"})})
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long numeroControle;

    @NotNull
    private LocalDate dataCadastro;

    @NotNull
    private String nomeProduto;

    @NotNull
    private Double valorUnitarioProduto;

    @NotNull
    private Integer quantidadeProduto;

    @NotNull
    private Long codigoCliente;

    @NotNull
    private Double valorTotalPedido;

}
