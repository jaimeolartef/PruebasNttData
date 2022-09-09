package com.example.nttdata.model.service;

import com.example.nttdata.model.entity.Cliente;

import java.util.Optional;

public interface IClienteService {

    Optional<Cliente> findByNumeroDocumentoTipoDocumento(String numeroDocumento, String tipoDocumento);
}
