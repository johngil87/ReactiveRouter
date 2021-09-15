package com.sofkatalks.pruebareactiva2.handler;

import com.sofkatalks.pruebareactiva2.model.Producto;
import com.sofkatalks.pruebareactiva2.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ProductoService implements CRUDService<Producto>{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Flux<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        producto.setFechaRegistro(new Date());
        return productoRepository.save(producto);
    }

    @Override
    public Mono<Producto> findById(String id) {
        return productoRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(Producto producto) {
        return productoRepository.delete(producto);
    }

    @Override
    public Mono<Producto> update(Producto producto) {
        return productoRepository.save(producto);
    }
}
