package com.heystyles.producto.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.producto.api.dao.ProductoDao;
import com.heystyles.producto.api.entity.ProductoEntity;
import com.heystyles.producto.api.message.MessageKeys;
import com.heystyles.producto.api.service.MarcaProductoService;
import com.heystyles.producto.api.service.ProductoService;
import com.heystyles.producto.core.domain.Marca;
import com.heystyles.producto.core.domain.Producto;
import com.heystyles.producto.core.domain.ProductoExtended;
import com.heystyles.producto.core.dto.ProductoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class ProductoServiceImpl
        extends ServiceImpl<Producto, ProductoEntity, Long> implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private MarcaProductoService marcaProductoService;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<ProductoEntity, Long> getDao() {
        return productoDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long insert(ProductoRequest request) {
        Long id = super.insert(request.getProducto());
        marcaProductoService.upsert(id, request.getMarcas());
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(ProductoRequest request) {
        super.update(request.getProducto());
        marcaProductoService.upsert(request.getProducto().getId(), request.getMarcas());
    }

    @Override
    public ProductoExtended getProducto(Long productoId) {
        ProductoEntity productoEntity = Optional.ofNullable(productoDao.findOne(productoId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(
                                MessageKeys.PRODUCTO_NOT_FOUND,
                                new String[]{String.valueOf(productoId)},
                                getLocale()
                        )
                ));
        return getConverterService().convertTo(productoEntity, ProductoExtended.class);
    }

    @Override
    public List<ProductoExtended> findAllProductoExtended() {
        return getConverterService().convertTo(productoDao.findAll(), ProductoExtended.class);
    }

    @Override
    public List<Marca> findMarcaByProductoId(Long productoId) {
        return getConverterService().convertTo(marcaProductoService.findMarcaByProductoId(productoId), Marca.class);
    }
}
