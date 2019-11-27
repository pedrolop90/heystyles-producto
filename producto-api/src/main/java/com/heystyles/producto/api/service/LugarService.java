package com.heystyles.producto.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.producto.core.domain.Lugar;

public interface LugarService extends Service<Lugar, Long> {

    Lugar getLugar(Long lugarId);
}
