package com.eicon.pedidos.port.adapter.repository;

import com.eicon.pedidos.application.Consulta;
import com.eicon.pedidos.domain.model.Pedido;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PedidoSpecification implements Specification<Pedido> {

    private final Consulta consulta;

    public PedidoSpecification(Consulta query) {
        this.consulta = query;
    }

    private Consulta getConsulta(){
       return this.consulta;
    }

    @Override
    public Predicate toPredicate(Root<Pedido> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {

        final Consulta queryEnt = getConsulta();
        final List<Predicate> predicates = new ArrayList<>();

        if (queryEnt.getNumeroControle() != null) {
            predicates.add(root.get("numeroControle").in(queryEnt.getNumeroControle()));
        }

        if (queryEnt.getDataCadastro() != null) {
            predicates.add(root.get("dataCadastro").in(queryEnt.getDataCadastro()));
        }

        if (queryEnt.getCodigoCliente() != null) {
            predicates.add(root.get("codigoCliente").in(queryEnt.getCodigoCliente()));
        }

        if(predicates.isEmpty()){
            return (Predicate) criteriaBuilder;
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[1]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PedidoSpecification that = (PedidoSpecification) o;
        return Objects.equals(consulta, that.consulta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consulta);
    }

}
