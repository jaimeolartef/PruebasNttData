package com.example.nttdata.view;

import com.example.nttdata.controller.ClienteController;
import com.example.nttdata.view.Dto.ClienteDto;
import com.example.nttdata.view.Dto.ConsultaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cliente")
public class ClienteView {

    @Autowired
    private ClienteController clienteController;


    @GetMapping("/consultar")
    public ResponseEntity<ClienteDto> consultarCliente(@RequestBody ConsultaDto consultaDto) {
        return clienteController.consultarCliente(consultaDto);
    }

}
