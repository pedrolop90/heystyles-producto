package com.heystyles.producto.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.producto.api.service.MarcaProductoService;
import com.heystyles.producto.core.dto.MarcaProductoDto;
import com.heystyles.producto.core.dto.MarcaProductoListResponse;
import com.heystyles.producto.core.dto.MarcaProductoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping(value = "/marca-producto")
@RestController
@Api(value = "Marca Producto Controller",
        description = "Controller para el manejo de las Marcas Producto")
public class MarcaProductoController {

    @Autowired
    private MarcaProductoService marcaProductoService;


    @ApiOperation(value = "Permite Listar todas las Marcas Productos de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marcas Productos Encontradas."),
            @ApiResponse(code = 404, message = "Marcas Productos no encontradas.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarcaProductoListResponse> getMarcasProductos() {
        List<MarcaProductoDto> marcas = marcaProductoService.getMarcaProductos();
        return Responses.responseEntity(new MarcaProductoListResponse(marcas));
    }

    @ApiOperation(value = "Permite Obtener una Marca Producto dado un id de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marca Producto Encontrada."),
            @ApiResponse(code = 404, message = "Marca Producto no encontrada.")
    })
    @GetMapping(value = "/{marcaProductoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarcaProductoResponse> getMarcaProductoById(
            @NotNull @PathVariable(name = "marcaProductoId") Long marcaProductoId) {
        MarcaProductoDto marcaProductoDto = marcaProductoService.getMarcaProductoById(marcaProductoId);
        return Responses.responseEntity(new MarcaProductoResponse(marcaProductoDto));
    }

    @ApiOperation(value = "Permite Obtener una Lista de Marca Productos dado una lista de ids")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marca Producto Encontrada."),
            @ApiResponse(code = 404, message = "Marca Producto no encontrada.")
    })
    @GetMapping(value = "/batch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarcaProductoListResponse> getMarcaProductosById(
            @NotNull @RequestParam(name = "marcaProductosIds") List<Long> marcaProductosIds) {
        List<MarcaProductoDto> marcaProductosDto = marcaProductoService.getMarcaProductosById(marcaProductosIds);
        return Responses.responseEntity(new MarcaProductoListResponse(marcaProductosDto));
    }
}
