package com.bootcampgp.tiendadeproductos.repositorios;

import com.bootcampgp.tiendadeproductos.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    Iterable<Producto> findAllByCategoria(String categoria);

}
