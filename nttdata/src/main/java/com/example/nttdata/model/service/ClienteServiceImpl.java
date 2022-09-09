package com.example.nttdata.model.service;

import com.example.nttdata.model.dao.IClienteDao;
import com.example.nttdata.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteDao clienteDao;

    @Override
    public Optional<Cliente> findByNumeroDocumentoTipoDocumento(String numeroDocumento, String tipoDocumento) {
        return clienteDao.findByNumeroDocumentoTipoDocumento(numeroDocumento, tipoDocumento);
    }
}
