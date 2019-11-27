package com.heystyles.producto.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.producto.api.service.MarcaService;
import com.heystyles.producto.core.domain.Marca;
import com.heystyles.producto.core.dto.MarcaListResponse;
import com.heystyles.producto.core.dto.MarcaResponse;
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

@RequestMapping(value = "/marca")
@RestController
@Api(value = "Marca Controller",
        description = "Controller para el manejo de las Marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @ApiOperation(value = "Permite Crear una Marca en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marca Creada.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(
            @NotNull @Valid @RequestBody Marca bean) {
        return Responses.responseEntity(new IdResponse(marcaService.insert(bean)));
    }

    @ApiOperation(value = "Permite actualizar una Marca en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marca Actualizada."),
            @ApiResponse(code = 404, message = "Marca no encontrada.")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@NotNull @Valid @RequestBody Marca bean) {
        marcaService.update(bean);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar una Marca de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marca Eliminada."),
            @ApiResponse(code = 404, message = "Marca no encontrada.")
    })
    @DeleteMapping(value = "/{marcaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "marcaId") Long marcaId) {
        marcaService.delete(marcaId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar una Marca de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marca Encontrada."),
            @ApiResponse(code = 404, message = "Marca no encontrada.")
    })
    @GetMapping(value = "/{marcaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarcaResponse> getMarca(
            @NotNull @PathVariable(name = "marcaId") Long marcaId) {
        return Responses.responseEntity(new MarcaResponse(marcaService.getMarca(marcaId)));
    }

    @ApiOperation(value = "Permite Listar todas las Marcas de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marcas Encontradas."),
            @ApiResponse(code = 404, message = "Marcas no encontradas.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarcaListResponse> getMarcas() {
        List<Marca> marcas = marcaService.findAll();
        return Responses.responseEntity(new MarcaListResponse(marcas));
    }

}
