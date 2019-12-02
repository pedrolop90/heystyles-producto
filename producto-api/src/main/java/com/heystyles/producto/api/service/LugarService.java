package com.heystyles.producto.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.producto.core.domain.Lugar;
import com.heystyles.producto.core.dto.LugarListResponse;
import com.heystyles.producto.core.filter.LugarFilter;

public interface LugarService extends Service<Lugar, Long> {

    Lugar getLugar(Long lugarId);

    void activarLugar(Long lugarId);

    LugarListResponse filter(LugarFilter filter);
}
