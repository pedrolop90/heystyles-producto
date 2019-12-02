package com.heystyles.producto.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.producto.core.domain.Marca;

public interface MarcaService extends Service<Marca, Long> {

    Marca getMarca(Long marcaId);

    void activarMarca(Long marcaId);
}
