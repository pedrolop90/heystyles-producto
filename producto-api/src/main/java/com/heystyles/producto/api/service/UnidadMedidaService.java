package com.heystyles.producto.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.producto.core.domain.UnidadMedida;

public interface UnidadMedidaService extends Service<UnidadMedida, Long> {

    UnidadMedida getUnidadMedida(Long unidadMedidaId);

    void activarUnidadMedida(Long unidadMedidaId);
}
