package com.heystyles.producto.api.entity;

import com.heystyles.common.persistence.LocalDateTimeAttributeConverter;
import com.heystyles.common.types.AuditableWithAuthorEntity;
import com.heystyles.common.types.SoftDeletable;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "marca_producto_lugar")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "s_delete = 0")
public class MarcaProductoLugarEntity extends AuditableWithAuthorEntity<Long> implements SoftDeletable {

    public interface Attributes extends AuditableWithAuthorEntity.Attributes {
        String LUGAR = "lugar";
        String LUGAR_ID = LUGAR + "." + LugarEntity.Attributes.ID;
        String MARCA_PRODUCTO = "marcaProducto";
        String MARCA_PRODUCTO_ID = MARCA_PRODUCTO + "." + MarcaProductoEntity.Attributes.ID;
        String MARCA_PRODUCTO_MARCA = MARCA_PRODUCTO + "." + MarcaProductoEntity.Attributes.MARCA;
        String MARCA_PRODUCTO_MARCA_ID = MARCA_PRODUCTO + "." + MarcaProductoEntity.Attributes.MARCA_ID;
        String MARCA_PRODUCTO_PRODUCTO = MARCA_PRODUCTO + "." + MarcaProductoEntity.Attributes.PRODUCTO;
        String MARCA_PRODUCTO_PRODUCTO_ID = MARCA_PRODUCTO + "." + MarcaProductoEntity.Attributes.PRODUCTO_ID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_marca_producto", nullable = false)
    private MarcaProductoEntity marcaProducto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_lugar", nullable = false)
    private LugarEntity lugar;

    @Column(name = "s_delete", nullable = false)
    private boolean delete;

    @CreatedDate
    @Column(name = "created_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime createdDate;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime updatedDate;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public LugarEntity getLugar() {
        return lugar;
    }

    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }

    public MarcaProductoEntity getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(MarcaProductoEntity marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public void markAsDeleted() {
        delete = true;
    }
}
