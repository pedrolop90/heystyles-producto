package com.heystyles.producto.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.producto.core.domain.Marca;
import com.heystyles.producto.core.dto.MarcaListResponse;
import com.heystyles.producto.core.filter.MarcaFilter;

public interface MarcaService extends Service<Marca, Long> {

    Marca getMarca(Long marcaId);

    MarcaListResponse filter(MarcaFilter filter);

    void activarMarca(Long marcaId);
}
