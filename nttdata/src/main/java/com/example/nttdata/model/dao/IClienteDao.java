package com.example.nttdata.model.dao;

import com.example.nttdata.model.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IClienteDao extends CrudRepository<Cliente, String> {

    @Query(value = "select c from Cliente c " +
            " where c.numeroDocumento = :numeroDocumento " +
            " and c.tipoDocumento = :tipoDocumento")
    Optional<Cliente> findByNumeroDocumentoTipoDocumento(String numeroDocumento, String tipoDocumento);
}
