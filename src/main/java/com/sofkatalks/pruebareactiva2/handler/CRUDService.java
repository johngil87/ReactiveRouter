package com.sofkatalks.pruebareactiva2.handler;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CRUDService<E> {

    Flux<E> findAll();
    Mono<E> save(E e);
    Mono<E> findById(String id);
    Mono<Void> delete(E e);
    Mono<E> update(E e);
}
