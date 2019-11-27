package com.heystyles.producto.cliente.impl;

import com.heystyles.producto.cliente.MarcaProductoClient;
import com.heystyles.producto.core.dto.MarcaProductoDto;
import com.heystyles.producto.core.dto.MarcaProductoListResponse;
import com.heystyles.producto.core.dto.MarcaProductoResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MarcaProductoClientImpl implements MarcaProductoClient {

    private final RestTemplate client;

    private final String urlBase;

    private interface SegmentPaths {
        String MARCA_PRODUCTO = "marca-producto";
    }

    public MarcaProductoClientImpl(String urlBase, RestTemplate client) {
        this.client = client;
        this.urlBase = urlBase;
    }


    @Override
    public List<MarcaProductoDto> getMarcasProductos() {
        UriComponentsBuilder urlBuilder = getUriClase();
        List<MarcaProductoDto> listResponse = client.getForEntity(
                urlBuilder.toUriString(),
                MarcaProductoListResponse.class).getBody().getData();
        return Optional.ofNullable(listResponse).orElse(new ArrayList<>());
    }

    @Override
    public MarcaProductoDto findMarcaProductoById(Long marcaProductoId) {
        UriComponentsBuilder urlBuilder = getUriClase()
                .pathSegment(String.valueOf(marcaProductoId));
        return client.getForEntity(urlBuilder.toUriString(), MarcaProductoResponse.class).getBody().getData();
    }

    @Override
    public List<MarcaProductoDto> findMarcaProductosById(List<Long> marcaProductoId) {
        UriComponentsBuilder urlBuilder = getUriClase()
                .pathSegment("/batch")
                .queryParam("marcaProductosIds", marcaProductoId.toArray());
        return client.getForEntity(urlBuilder.toUriString(), MarcaProductoListResponse.class).getBody().getData();
    }


    private UriComponentsBuilder getUriClase() {
        return UriComponentsBuilder.fromHttpUrl(urlBase).pathSegment(SegmentPaths.MARCA_PRODUCTO);
    }


}
