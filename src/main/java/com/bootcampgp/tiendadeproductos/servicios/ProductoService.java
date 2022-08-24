package com.bootcampgp.tiendadeproductos.servicios;

import com.bootcampgp.tiendadeproductos.entidades.Producto;
import com.bootcampgp.tiendadeproductos.excepciones.ExisteException;
import com.bootcampgp.tiendadeproductos.excepciones.NoExisteException;

import java.util.Optional;


public interface ProductoService {

    Producto altaProducto(Producto producto) throws ExisteException;
    Iterable<Producto> listarProductos();
    Optional<Producto> buscarPorId(Long id) throws NoExisteException;
    Iterable<Producto> listarPorCategoria(String categoria);
}
