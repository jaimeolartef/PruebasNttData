package com.example.nttdata.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @Column(name = "numerodocumento")
    private String numeroDocumento;

    @Column(name = "tipodocumento")
    private String tipoDocumento;

    @Column(name = "primernombre")
    private String primerNombre;

    @Column(name = "segundonombre")
    private String segundoNombre;

    @Column(name = "primerapellido")
    private String primerApellido;

    @Column(name = "segundoapellido")
    private String segundoApellido;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "ciudadresidencia")
    private String ciudadResidencia;
}
