package com.heystyles.producto.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.producto.api.service.UnidadMedidaService;
import com.heystyles.producto.core.domain.UnidadMedida;
import com.heystyles.producto.core.dto.UnidadMedidaListResponse;
import com.heystyles.producto.core.dto.UnidadMedidaResponse;
import com.heystyles.producto.core.filter.UnidadMedidaFilter;
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

@RequestMapping(value = "/unidad-medida")
@RestController
@Api(value = "Unidad Medida Controller",
        description = "Controller para el manejo de las unidades de medida")
public class UnidadMedidaController {

    @Autowired
    private UnidadMedidaService unidadMedidaService;

    @ApiOperation(value = "Permite Crear una Unidad de Medida en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Unidad de Medida Creada.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(
            @NotNull @Valid @RequestBody UnidadMedida bean) {
        return Responses.responseEntity(new IdResponse(unidadMedidaService.insert(bean)));
    }

    @ApiOperation(value = "Permite actualizar una Unidad de Medida en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Unidad de Medida  Actualizada."),
            @ApiResponse(code = 404, message = "Unidad de Medida no encontrada.")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@NotNull @Valid @RequestBody UnidadMedida bean) {
        unidadMedidaService.update(bean);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar una Unidad de Medida de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Unidad de Medida Eliminada."),
            @ApiResponse(code = 404, message = "Unidad de Medida no encontrada.")
    })
    @DeleteMapping(value = "/{unidadMedidaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "unidadMedidaId") Long unidadMedidaId) {
        unidadMedidaService.delete(unidadMedidaId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar una Unidad de Medida de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Unidad de Medida Encontrada."),
            @ApiResponse(code = 404, message = "Unidad de Medida no encontrada.")
    })
    @GetMapping(value = "/{unidadMedidaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnidadMedidaResponse> getUnidadMedida(
            @NotNull @PathVariable(name = "unidadMedidaId") Long unidadMedidaId) {
        return Responses.responseEntity(new UnidadMedidaResponse(unidadMedidaService.getUnidadMedida(unidadMedidaId)));
    }

    @ApiOperation(value = "Permite Listar todas las Unidad de Medida de la base de datos, dado un filtro")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Unidad de Medida Encontradas."),
            @ApiResponse(code = 404, message = "Unidad de Medida no encontradas.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnidadMedidaListResponse> getUnidadesMedida(UnidadMedidaFilter filter) {
        return Responses.responseEntity(unidadMedidaService.filter(filter));
    }

    @ApiOperation(value = "Permite Activar una Unidad de Medida en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Unidad de Medida Activada."),
            @ApiResponse(code = 404, message = "Unidad de Medida no encontrada.")
    })
    @PutMapping(value = "{unidadMedidaId}/activar",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@NotNull @PathVariable Long unidadMedidaId) {
        unidadMedidaService.activarUnidadMedida(unidadMedidaId);
        return Responses.successEntity("Activación correcta");
    }
}
