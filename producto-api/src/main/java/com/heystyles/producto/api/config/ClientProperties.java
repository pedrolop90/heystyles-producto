package com.heystyles.producto.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@Validated
@ConfigurationProperties("client")
public class ClientProperties {

    @NotNull
    private String seguridadUrlBase;

    private int connectionTimeout;

    private int readTimeout;

    public String getSeguridadUrlBase() {
        return seguridadUrlBase;
    }

    public void setSeguridadUrlBase(String seguridadUrlBase) {
        this.seguridadUrlBase = seguridadUrlBase;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
