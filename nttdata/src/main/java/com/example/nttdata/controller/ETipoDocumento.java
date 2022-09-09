package com.example.nttdata.controller;

public enum ETipoDocumento {
    CEDULACIUDADANIA("C"),
    PASAPORTE("P");

    private final String codigo;

    ETipoDocumento(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static String findyByCodigo(String codigo) {
        for (ETipoDocumento tipoDocumento : ETipoDocumento.values()) {
            if (tipoDocumento.getCodigo().equals(codigo)) {
                return tipoDocumento.codigo;
            }
        }
        return "";
    }
}
