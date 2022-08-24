package com.bootcampgp.tiendadeproductos.repositorios;

import com.bootcampgp.tiendadeproductos.entidades.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Optional<Vendedor> findByNombreApellido(String nombreApellido);
}
