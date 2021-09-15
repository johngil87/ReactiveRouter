package com.sofkatalks.pruebareactiva2.router;

import com.sofkatalks.pruebareactiva2.handler.HandlerProducto;
import com.sofkatalks.pruebareactiva2.handler.ProductoService;
import com.sofkatalks.pruebareactiva2.model.Producto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {


    @Bean
    public RouterFunction<ServerResponse> conHandler(HandlerProducto handler){
        return route(GET("/api3/productos").and(accept(MediaType.APPLICATION_JSON)), handler::listar)
                .andRoute(POST("/api3/productos").and(accept(MediaType.APPLICATION_JSON)), handler::crear)
                .andRoute(GET("/api3/productos/{id}"), handler::obtenerPorId);
    }


    //segunda forma
    @Bean
    public RouterFunction<ServerResponse> crearProducto(ProductoService service){
        return route(POST("/api2/productos").and(accept(MediaType.APPLICATION_JSON)), serverRequest -> serverRequest
                .bodyToMono(Producto.class)
                .flatMap(p-> service.save(p).flatMap( result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result))))
                .andRoute(GET("api2/productos"), serverRequest -> ServerResponse.ok()
                      .body(service.findAll(), Producto.class))
                .andRoute(GET("api2/producto/{id}"), serverRequest -> ServerResponse
                        .ok()
                        .body(service.findById(serverRequest.pathVariable("id")), Producto.class));
    }


    //primera forma
    @Bean
    public RouterFunction<ServerResponse> obtener(ProductoService service){
        return route(GET("api2/productos2"), serverRequest -> ServerResponse.ok()
                .body(service.findAll(), Producto.class));
    }


}
