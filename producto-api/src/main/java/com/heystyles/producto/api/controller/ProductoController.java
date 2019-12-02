package com.heystyles.producto.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.producto.api.service.ProductoService;
import com.heystyles.producto.core.domain.Marca;
import com.heystyles.producto.core.dto.MarcaListResponse;
import com.heystyles.producto.core.dto.ProductoExtendedListResponse;
import com.heystyles.producto.core.dto.ProductoExtendedResponse;
import com.heystyles.producto.core.dto.ProductoRequest;
import com.heystyles.producto.core.filter.ProductoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

@RequestMapping(value = "/producto")
@RestController
@Api(value = "Producto Controller",
        description = "Controller para el manejo de los Productos")
@Validated
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @ApiOperation(value = "Permite Crear un Producto en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Producto Creado.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(
            @NotNull @Valid @RequestBody ProductoRequest request) {
        return Responses.responseEntity(new IdResponse(productoService.insert(request)));
    }

    @ApiOperation(value = "Permite actualizar un Producto en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Producto Actualizado."),
            @ApiResponse(code = 404, message = "Producto no encontrado.")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@NotNull @Valid @RequestBody ProductoRequest request) {
        productoService.update(request);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite activar un Producto en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Producto Activado."),
            @ApiResponse(code = 404, message = "Producto no encontrado.")
    })
    @PutMapping(value = "/{productoId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> activarProducto(@NotNull @PathVariable Long productoId) {
        productoService.activarProducto(productoId);
        return Responses.successEntity("Activacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar un Producto de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Producto Eliminado."),
            @ApiResponse(code = 404, message = "Producto no encontrado.")
    })
    @DeleteMapping(value = "/{productoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "productoId") Long productoId) {
        productoService.delete(productoId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar uno Producto de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Producto Encontrado."),
            @ApiResponse(code = 404, message = "Producto no encontrado.")
    })
    @GetMapping(value = "/{productoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductoExtendedResponse> getProductoExtended(
            @NotNull @PathVariable(name = "productoId") Long productoId) {
        return Responses.responseEntity(new ProductoExtendedResponse(productoService.getProductoExtended(productoId)));
    }

    @ApiOperation(value = "Permite Listar todos los Productos de la base de datos, dado un filtro")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Productos Encontrados."),
            @ApiResponse(code = 404, message = "Productos no encontrados.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductoExtendedListResponse> findProductoById(ProductoFilter filter) {
        return Responses.responseEntity(productoService.filter(filter));
    }

    @ApiOperation(value = "Permite Listar todas las marcas de un producto, dado el id de un producto.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marcas Encontradas."),
            @ApiResponse(code = 404, message = "Marcas no encontradas.")
    })
    @GetMapping(value = "/{productoId}/marcas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarcaListResponse> findAll(
            @NotNull @PathVariable(name = "productoId") Long productoId) {
        List<Marca> marcas = productoService.findMarcaByProductoId(productoId);
        return Responses.responseEntity(new MarcaListResponse(marcas));
    }
}
