package com.bootcampgp.tiendadeproductos.repositorios;

import com.bootcampgp.tiendadeproductos.entidades.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query("select v from Venta v join fetch v.vendedor w where w.id = ?1")
    Iterable<Venta> buscarVentasPorIdVendedor(Long id);


}
