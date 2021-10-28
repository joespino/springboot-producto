package com.formacionti.springboot.app.productos.controllers;

import com.formacionti.springboot.app.productos.models.entity.Producto;
import com.formacionti.springboot.app.productos.models.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class ProductoController {

    @Autowired
    private Environment env;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private IProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> listar() {
        return productoService.findAll()
                .stream()
                .peek(p ->
                        p.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))))
                        //p.setPort(port)
                )
                .collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        producto.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
        //producto.setPort(port);
        /*try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return producto;
    }
}
