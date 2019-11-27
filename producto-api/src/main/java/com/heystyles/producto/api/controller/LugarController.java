package com.heystyles.producto.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.producto.api.service.LugarService;
import com.heystyles.producto.core.domain.Lugar;
import com.heystyles.producto.core.dto.LugarListResponse;
import com.heystyles.producto.core.dto.LugarResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping(value = "/lugar")
@RestController
@Api(value = "Lugar Controller",
        description = "Controller para el manejo de los Lugares")
public class LugarController {

    @Autowired
    private LugarService lugarService;

    @ApiOperation(value = "Permite Crear un Lugar en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lugar Creado.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(
            @NotNull @Valid @RequestBody Lugar bean) {
        return Responses.responseEntity(new IdResponse(lugarService.insert(bean)));
    }

    @ApiOperation(value = "Permite actualizar un Lugar en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lugar Actualizado."),
            @ApiResponse(code = 404, message = "Lugar no encontrado.")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@NotNull @Valid @RequestBody Lugar bean) {
        lugarService.update(bean);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar un Lugar de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lugar Eliminado."),
            @ApiResponse(code = 404, message = "Lugar no encontrado.")
    })
    @DeleteMapping(value = "/{lugarId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "lugarId") Long lugarId) {
        lugarService.delete(lugarId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar un Lugar de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lugar Encontrado."),
            @ApiResponse(code = 404, message = "Lugar no encontrado.")
    })
    @GetMapping(value = "/{lugarId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LugarResponse> getLugar(
            @NotNull @PathVariable(name = "lugarId") Long lugarId) {
        return Responses.responseEntity(new LugarResponse(lugarService.getLugar(lugarId)));
    }

    @ApiOperation(value = "Permite Listar todos los Lugares de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lugares Encontrados."),
            @ApiResponse(code = 404, message = "Lugares no encontrados.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LugarListResponse> getLugares() {
        List<Lugar> lugares = lugarService.findAll();
        return Responses.responseEntity(new LugarListResponse(lugares));
    }

}
