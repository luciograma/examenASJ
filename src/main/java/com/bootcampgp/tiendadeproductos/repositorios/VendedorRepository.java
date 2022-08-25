package com.bootcampgp.tiendadeproductos.repositorios;

import com.bootcampgp.tiendadeproductos.entidades.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
