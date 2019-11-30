package com.heystyles.producto.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.producto.api.service.MarcaProductoLugarService;
import com.heystyles.producto.core.domain.Lugar;
import com.heystyles.producto.core.dto.LugarListResponse;
import com.heystyles.producto.core.dto.MarcaProductoLugarRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping(value = "/marca-prodcuto-lugar")
@RestController
@Api(value = "Marca Producto Lugar Controller",
        description = "Controller para el manejo de Lugares de las Marcas Productos.")
@Validated
public class MarcaProductoLugarController {

    @Autowired
    private MarcaProductoLugarService marcaProductoLugarService;

    @ApiOperation(value = "Permite Asignar o quitar de una Marca Producto, lugares")
    @ApiResponses({
            @ApiResponse(code = 200, message = "lugares Asignado."),
            @ApiResponse(code = 404, message = "lugares no Asignado.")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> upsert(@NotNull @Valid @RequestBody MarcaProductoLugarRequest request) {
        marcaProductoLugarService.upsert(request);
        return Responses.successEntity("Asginacion correcta");
    }


    @ApiOperation(value = "Permite Listar todos los lugares, dado un productoId y marcaId.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "lugares Encontrados."),
            @ApiResponse(code = 404, message = "lugares no encontros.")
    })
    @GetMapping(value = "/{productoId}/{marcaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LugarListResponse> findLugarByProductoIdAndMarcaId(
            @NotNull @PathVariable(name = "productoId") Long productoId,
            @NotNull @PathVariable(name = "marcaId") Long marcaId) {
        List<Lugar> lugares = marcaProductoLugarService.findLugaresByProductoIdAndMarcaId(productoId, marcaId);
        return Responses.responseEntity(new LugarListResponse(lugares));
    }

}
