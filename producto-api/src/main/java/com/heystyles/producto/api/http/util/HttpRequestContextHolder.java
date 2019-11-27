package com.heystyles.producto.api.http.util;

public final class HttpRequestContextHolder {

    private HttpRequestContextHolder() {
        super();
    }

    private static final ThreadLocal<String> USUARIO_HOLDER = new ThreadLocal<>();

    public static void setUsuario(String usuario) {
        USUARIO_HOLDER.set(usuario);
    }

    public static String getUsuario() {
        return USUARIO_HOLDER.get();
    }

    public static void clearUsuario() {
        USUARIO_HOLDER.remove();
    }

}
