package com.bootcampgp.tiendadeproductos.servicios;

import com.bootcampgp.tiendadeproductos.entidades.Vendedor;
import com.bootcampgp.tiendadeproductos.excepciones.NoExisteException;
import com.bootcampgp.tiendadeproductos.excepciones.ExisteException;

import java.util.Optional;

public interface VendedorService {

    Vendedor altaVendedor(Vendedor vendedor) throws ExisteException;
    Vendedor actualizarVendedor(Long id, Vendedor vendedor) throws NoExisteException;
    Iterable<Vendedor> listarVendedores();
    Optional<Vendedor> buscarPorId(Long id) throws NoExisteException;
}
