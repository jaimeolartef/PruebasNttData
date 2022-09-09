package com.example.nttdata.controller;

import com.example.nttdata.model.entity.Cliente;
import com.example.nttdata.model.service.IClienteService;
import com.example.nttdata.view.Dto.ConsultaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private IClienteService clienteService;

    ConsultaDto consultaDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        consultaDto = new ConsultaDto();
    }

    @Test
    void consultarDeberiaRetornarCliente() {
        consultaDto.setNumeroDocumento("23445322");
        consultaDto.setTipoDocumento("C");
        Cliente cliente = new Cliente();
        cliente.setDireccion("Calle falsa 123");
        cliente.setCiudadResidencia("Bucaramanga");
        cliente.setTelefono("1234578");
        cliente.setPrimerNombre("Jaime");
        cliente.setSegundoNombre("Andres Alberto");
        cliente.setPrimerApellido("Olarte");
        cliente.setSegundoApellido("Fuentes");

        Mockito.when(clienteService.findByNumeroDocumentoTipoDocumento(consultaDto.getNumeroDocumento(),
                consultaDto.getTipoDocumento())).thenReturn(Optional.of(cliente));
        assertNotNull(clienteController.consultarCliente(consultaDto));
    }

    @Test
    void consultarClienteNoExisteDeberiaRetornarError404() {
        consultaDto.setNumeroDocumento("123");
        consultaDto.setTipoDocumento("C");

        Mockito.when(clienteService.findByNumeroDocumentoTipoDocumento(consultaDto.getNumeroDocumento(),
                consultaDto.getTipoDocumento())).thenReturn(Optional.empty());
        assertEquals(clienteController.consultarCliente(consultaDto),
                new ResponseEntity<>("No se encontro ningún cliente con los datos espeficados",
                HttpStatus.NOT_FOUND));
    }

    @Test
    void consultarConTipoDocumentoNoExisteDeberiaRetornarError400() throws Exception {
        consultaDto.setNumeroDocumento("23445322");        ;
        assertEquals(clienteController.consultarCliente(consultaDto), new ResponseEntity<>("El tipo documento es obligatorio",
                HttpStatus.BAD_REQUEST));

    }

    @Test
    void consultarConNumDocumentoNoExisteDeberiaRetornarError400() throws Exception {
        consultaDto.setTipoDocumento("C");        ;
        assertEquals(clienteController.consultarCliente(consultaDto), new ResponseEntity<>("El número de documento es obligatorio",
                HttpStatus.BAD_REQUEST));

    }

    @Test
    void consultarConTipoDocumentoNoValidoDeberiaRetornarError400() throws Exception {
        consultaDto.setTipoDocumento("S");
        consultaDto.setNumeroDocumento("23445322");;
        assertEquals(clienteController.consultarCliente(consultaDto), new ResponseEntity<>("El tipo documento no es valido",
                HttpStatus.BAD_REQUEST));

    }
}
