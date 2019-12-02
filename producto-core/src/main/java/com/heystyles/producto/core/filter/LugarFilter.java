package com.heystyles.producto.core.filter;

import com.heystyles.common.filter.Filter;
import com.heystyles.common.types.Estado;

public class LugarFilter extends Filter {

    private String nombre;
    private Estado estado;
    private Long posicionInicial;
    private Long posicionFinal;
    private Boolean nombreAscending;
    private Boolean estadoAscending;
    private Boolean posicionAscending;

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

    public Long getPosicionInicial() {
        return posicionInicial;
    }

    public void setPosicionInicial(Long posicionInicial) {
        this.posicionInicial = posicionInicial;
    }

    public Long getPosicionFinal() {
        return posicionFinal;
    }

    public void setPosicionFinal(Long posicionFinal) {
        this.posicionFinal = posicionFinal;
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

    public Boolean getPosicionAscending() {
        return posicionAscending;
    }

    public void setPosicionAscending(Boolean posicionAscending) {
        this.posicionAscending = posicionAscending;
    }
}
