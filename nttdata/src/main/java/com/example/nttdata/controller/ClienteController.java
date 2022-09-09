package com.example.nttdata.controller;

import com.example.nttdata.model.entity.Cliente;
import com.example.nttdata.model.service.IClienteService;
import com.example.nttdata.view.Dto.ClienteDto;
import com.example.nttdata.view.Dto.ConsultaDto;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    public ResponseEntity consultarCliente(ConsultaDto consultaDto) {

        String mensajevalidacion = validarParametros(consultaDto);
        if (Strings.isNotBlank(mensajevalidacion) || Strings.isNotEmpty(mensajevalidacion)) {
            return new ResponseEntity<>(mensajevalidacion, HttpStatus.BAD_REQUEST);
        }

        ClienteDto clienteDto = new ClienteDto();

        Optional<Cliente> cliente = clienteService.findByNumeroDocumentoTipoDocumento(consultaDto.getNumeroDocumento(),
                consultaDto.getTipoDocumento());

        if (!cliente.isPresent()) {
            return new ResponseEntity<>("No se encontro ningún cliente con los datos espeficados", HttpStatus.NOT_FOUND);
        }

        cliente.ifPresent(clienteRta -> {
                    clienteDto.setCiudadResidencia(clienteRta.getCiudadResidencia());
                    clienteDto.setDireccion(clienteRta.getDireccion());
                    clienteDto.setPrimerApellido(clienteRta.getPrimerApellido());
                    clienteDto.setSegundoApellido(clienteRta.getSegundoApellido());
                    clienteDto.setPrimerNombre(clienteRta.getPrimerNombre());
                    clienteDto.setSegundoNombre(clienteRta.getSegundoNombre());
                    clienteDto.setTelefono(clienteRta.getTelefono());

                });

        return new ResponseEntity<>(clienteDto, HttpStatus.OK);
    }

    private String validarParametros(ConsultaDto consultaDto) {

        if (Strings.isEmpty(consultaDto.getTipoDocumento()) || Strings.isBlank(consultaDto.getTipoDocumento())) {
            return "El tipo documento es obligatorio";
        }

        if (Strings.isEmpty(consultaDto.getNumeroDocumento()) || Strings.isBlank(consultaDto.getNumeroDocumento())) {
            return "El número de documento es obligatorio";
        }

        if (ETipoDocumento.findyByCodigo(consultaDto.getTipoDocumento()).equals(Strings.EMPTY)) {
            return "El tipo documento no es valido";
        }

        return "";
    }
}
