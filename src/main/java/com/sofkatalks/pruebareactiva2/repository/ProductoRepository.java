package com.sofkatalks.pruebareactiva2.repository;

import com.sofkatalks.pruebareactiva2.model.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {
}
