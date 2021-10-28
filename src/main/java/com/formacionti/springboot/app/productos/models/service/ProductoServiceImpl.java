package com.formacionti.springboot.app.productos.models.service;

import com.formacionti.springboot.app.productos.models.dao.ProductoDao;
import com.formacionti.springboot.app.productos.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductoServiceImpl implements IProductoService{

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return StreamSupport.stream(productoDao.findAll().spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return productoDao.findById(id).orElse(null);
    }
}
