package com.bootcampgp.tiendadeproductos.servicios;

import com.bootcampgp.tiendadeproductos.entidades.Producto;
import com.bootcampgp.tiendadeproductos.entidades.Venta;
import com.bootcampgp.tiendadeproductos.excepciones.ExisteException;
import com.bootcampgp.tiendadeproductos.excepciones.NoExisteException;

import java.util.Optional;

public interface VentaService {

    Venta altaVenta(Venta venta) throws ExisteException, NoExisteException;
    Iterable<Venta> listarVentas();
    Optional<Venta> buscarVentaPorId(Long id) throws NoExisteException;
}
