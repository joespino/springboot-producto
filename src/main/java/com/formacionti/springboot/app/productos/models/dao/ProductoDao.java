package com.formacionti.springboot.app.productos.models.dao;

import com.formacionti.springboot.app.productos.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDao extends CrudRepository<Producto, Long> {
}
