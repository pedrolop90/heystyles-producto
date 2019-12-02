package com.heystyles.producto.core.filter;

import com.heystyles.common.filter.Filter;
import com.heystyles.common.types.Estado;

public class MarcaFilter extends Filter {

    private String nombre;
    private Estado estado;
    private Boolean nombreAscending;
    private Boolean estadoAscending;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Boolean getNombreAscending() {
        return nombreAscending;
    }

    public void setNombreAscending(Boolean nombreAscending) {
        this.nombreAscending = nombreAscending;
    }

    public Boolean getEstadoAscending() {
        return estadoAscending;
    }

    public void setEstadoAscending(Boolean estadoAscending) {
        this.estadoAscending = estadoAscending;
    }
}
